package com.bowwow.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRestController { 
	
	@Autowired
	private UserService service;
	
	@PostMapping("/users/chk_dup")
	public String checkDuplicate(@Param("id") Integer id, @Param("email") String email, @Param("nickname") String nickname) {
		return service.isUniqueDup(id, email, nickname);
	}
	

}
