package com.bowwow.admin.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.User;

@Service
@Transactional
public class UserService {
	
	public static final int USERS_PER_PAGE = 4;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;
	
	public List<User> listAll() {
		return (List<User>) userRepo.findAll();
	}

	public User save(User user) {
		boolean isUpdatingUser = (user.getId() != null);
		if(isUpdatingUser) {
			User existingUser = userRepo.findById(user.getId()).get();
			if(user.getPassword().isEmpty()) {
				user.setPassword(existingUser.getPassword());
			}else {
				encodePassword(user);
			}
		}else {
			encodePassword(user);
		}
		return userRepo.save(user);	
	}
	
	public void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}

	public String isUniqueDup(Integer id, String email, String nickname) {
	      User userByEmail = userRepo.findByEmail(email);
	      User userBynickname = userRepo.findByNickName(nickname);
	      
	      if(userByEmail == null && userBynickname == null) return "OK";
	      
	      boolean isCreatingNew = (id == null || id == 0); //신규생성은 id == null -> true 
	      
	      if(isCreatingNew) {                        
	         if(userByEmail != null && userBynickname != null) { 
	            return "dupBoth";
	         }else if(userByEmail != null && userBynickname == null) {
	            return "dupEmail";
	         }else if(userByEmail == null && userBynickname != null) {
	            return "dupNickName";
	         }
	      }else {
	         if (userBynickname == null) {  //업데이트할떄 
	            return "OK";
	         }else if(userBynickname != null && userBynickname.getId() != id) {
	            return "dupNickName";
	         }else if(userBynickname != null && userBynickname.getId() == id) {
	            return "OK";
	         }else return "error";
	      }
	      return "error";
	   }
	public Page<User> listByPage(int pageNum){
		Pageable pageable = PageRequest.of(pageNum - 1,USERS_PER_PAGE);
		return userRepo.findAll(pageable);
	}
	public Page<User> listByPage(int pageNum,String sortField, String sortDir,String keyword){
		Sort sort =Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum -1, USERS_PER_PAGE, sort);
		if (keyword != null) {
			return userRepo.findAll(keyword,pageable);
		}
		return userRepo.findAll(pageable);
	}
	
	public User findById(Integer id) {
		return userRepo.findById(id).get();
		
	}
	public void findBydelete(Integer id) {
		userRepo.deleteById(id);
	}

	public User findByEmail(String name) {
		return userRepo.findByEmail(name);
	}
}
