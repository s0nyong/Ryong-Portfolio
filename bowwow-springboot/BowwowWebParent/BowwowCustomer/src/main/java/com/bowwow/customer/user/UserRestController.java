package com.bowwow.customer.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;
import com.bowwow.customer.product.ProductService;


@RestController
public class UserRestController {

	@Autowired
	private UserService service;
	
	@Autowired
	private ProductService proService;
	
	@PostMapping("/users/check_nick")
	public String checkUniqueNick(@Param("id") Integer id, @Param("nickName") String nickName, @Param("email")String email) {
		return service.isNickNameUnique(id, nickName,email);
	}

	@PostMapping("/product/likeit/{id}")
	public String likeit(Model model, @PathVariable("id")int id, Principal p) {
		
		if(p == null) {
			return "NotUser";
		}
		User user = service.findByEmail(p.getName());// 로그인 정보 가져오기	
		
		Product pro = proService.findById(id);
		pro.addLike(user);
		service.save(user);
		return "OK";
	}
	
	@PostMapping("/product/dislike/{id}")
	public String dislikeit(Model model, @PathVariable("id")int id, Principal p) {
		
		if(p == null) {
			return "NotUser";
		}
		User user = service.findByEmail(p.getName());// 로그인 정보 가져오기	
		Product pro = proService.findById(id);
		pro.deleteLike(user);
		
		proService.save(pro);
		return "OK";
	}
	


}
