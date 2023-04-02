package com.bowwow.customer.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;
import com.bowwow.customer.product.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository proRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private final String KAKAO_KEY_VALUE = "b56fc54652eee24b05ff45ef46f5c893";
	
	public List<User> listAll() {
		return (List<User>) userRepo.findAll();
	}
	
	public List<Product> ProductAll(){
		return (List<Product>) proRepo.findAll();
	}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public User findById(int id) {
		return userRepo.findById(id).get();
	}
	
	public User save(User user) {
	      boolean isUpdatingUser = (user.getId() != null);
	      
	      if(isUpdatingUser) {
	         User existingUser = userRepo.findById(user.getId()).get();
	         if(user.getPassword().isEmpty()) {   //유저폼에서 유저 업데이트시 비밀번호를 변경하지 않았을때
	            user.setPassword(existingUser.getPassword());
	         }else {
	            if(user.getPassword() == existingUser.getPassword()) {  //유저폼 아닌데서 point or like 가 업데이트 될때
	               user.setPassword(existingUser.getPassword());
	            }else {                          //유저폼에서 유저 업데이트시 비밀번호를 변경했을때
	            encodePassword(user);
	            }
	         }
	      }else {
	         encodePassword(user);
	         System.err.println("신규등록");
	      }
	      return userRepo.save(user);
	   }

	private void encodePassword(User user) {
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		
	}

	public String isNickNameUnique(Integer id, String nickName, String email) {
		User userByNickName = userRepo.findByNickName(nickName);
		User userByEmail = userRepo.findByEmail(email);
		
		boolean isCreatingNew = (id == null);	//
		
		if(isCreatingNew) {	//회원가입
			if(userByEmail == null && userByNickName == null) {
				return "OK";
			}else if(userByEmail != null && userByNickName == null) {
				return "DuplicateEmail";
			}else if(userByNickName != null && userByEmail == null) {
				return "DuplicateNickName";
			}else if(userByEmail != null && userByNickName != null) {
				return "Duplicate";
			}
		}else {	//내정보수정
			if(userByNickName == null) {
				return "OK";
			}else if(userByNickName != null && userByNickName.getId() == id) {
				return "OK";	
			}else if(userByNickName != null && userByNickName.getId() != id ) {
				return "DuplicateNickName";
			}
		}
		return "OK";
	}

	public void kakaoLogin(String code) {
RestTemplate rt = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", KAKAO_KEY_VALUE);
		params.add("redirect_uri", "http://localhost:3333/bowwow/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader 와 HttpBody 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
		
		//Http 요청하기 -Post 방식으로, response변수의 응답 받음
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token", 
				HttpMethod.POST, 
				kakaoTokenRequest, 
				String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		RestTemplate rt2 = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpHeader 와 HttpBody 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);
		
		//Http 요청하기 -Post 방식으로, response변수의 응답 받음
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me", 
				HttpMethod.POST, 
				kakaoProfileRequest, 
				String.class);
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProperties kakaoProperties = null;
		try {
			kakaoProperties = objectMapper2.readValue(response2.getBody(), KakaoProperties.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		String k_email = kakaoProperties.getKakao_account().getEmail();
		String k_password = "bowwowForKakao";
		String k_name = kakaoProperties.getKakao_account().getEmail() + "_" + kakaoProperties.getId();
		String k_nickName = kakaoProperties.getProperties().getNickname()+"_kakao";
		String k_role = "일반";

		System.err.println("유저닉네임 :::::::::::: "+kakaoProperties.getProperties().getNickname());
		System.err.println("유저이메일 :::::::::::: "+kakaoProperties.getKakao_account().getEmail());
		
		
		User k_theUser = null;
		//최초 카카오 로그인시 회원가입
		if(findByEmail(k_email) == null) {
			k_theUser = new User(k_email, k_password, k_name, k_nickName, "일반", 0, true);
			save(k_theUser);
		}
		
		//기존회원 카카오 로그인시
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(k_email, k_password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}


}
