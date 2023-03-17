package com.code.bowwow.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.bowwow.entity.product;
import com.code.bowwow.entity.user;
import com.code.bowwow.service.bowwowService;

@Controller
public class loginController {
	
	@Autowired
	private bowwowService bowwowService;
	
	//================================================================== 사용자 페이지 =========================================================================
	
	//로그인 페이지 이동
	@GetMapping("/login")
	public String LoginPage(Model theModel) {
		return "user/login_form";
	}
	
	//security 권한 외 접근 페이지
	@GetMapping("/access-denide")
	public String accessdenided(user user, Principal p, Model theModel, HttpServletRequest request) {
		String userName = bowwowService.getUser(p.getName()).get(0).getUserName(); //사용자 닉네임
		theModel.addAttribute("username", userName);
		return "access-denide";
	}

	//회원가입 페이지 이동
	@GetMapping("/signup_form")	
	public String signupForm() {
		return"user/signup_form";
	}
	
	//회원가입 정보 입력
	@PostMapping("/signup")
	public String signup(user u, Model theModel) {
		if(bowwowService.getUser(u.getEmail()).isEmpty()) { //id(email)중복체크 후 중복 아니면
			bowwowService.saveUser(u);        //새로운 유저 회원가입
			List<product> theProducts = bowwowService.getProductsDESC();
	         theModel.addAttribute("products",theProducts);
			return "home";
		}else {
			theModel.addAttribute("error", "error"); //회원가입 중복체크 후 중복이면 에러알림 발생
			return "user/signup_form";
		}
	}
	
	//내정보 변경
	@PostMapping("/Mypage")
	public String Mypage(user modifyUser,Principal p, Model theModel,HttpServletRequest request) {
		if(request.getParameter("ddd")!=null) {
			bowwowService.saveUser(modifyUser);   //변경한 유저정보 업데이트
			List<product> theProducts = bowwowService.getProductsDESC(); //평점 높은 제품 노출
			theModel.addAttribute("products",theProducts);
			return"home";
		}
		List<user> theUser = bowwowService.getUser(p.getName());  //유저정보 출력
		theModel.addAttribute("edituser", theUser.get(0));
		
		return "user/Mypage";
	}
	
	//================================================================== 관리자 페이지 =========================================================================
	
	//관리자: 유저정보 조회
	@GetMapping("/master_form")   
	   public String master(Model theModel) {
	      List<user> theuser = bowwowService.masteruser();  //전체 이용자 정보 받아오기
	      for(int i=0; i<theuser.size(); i++) { //유저정보 내 security 권한 출력시 "ROLE_" 글자 삭제
	         String usergrade = theuser.get(i).getGrade();
	         String removerole=usergrade.substring(5);
	         theuser.get(i).setGrade(removerole);
	      }
	      theModel.addAttribute("user",theuser);
	      return "user/master_form";
	   }
	   
	//관리자: 이용자 권한 변경
	@GetMapping("/user_btn")
	public String usergrade_update(Principal p, @RequestParam("grade")String grade,@RequestParam("useremail")String useremail,user user,Model theModel) {
		List<user> theUser = bowwowService.getUser(p.getName());
		bowwowService.usergrade_update(grade,useremail);
		return "redirect:/master_form";
	}
}
