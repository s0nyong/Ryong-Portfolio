package com.bowwow.customer.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bowwow.common.entity.User;
import com.bowwow.customer.user.UserService;

@RestController
public class OrderRestController {

	@Autowired
	private UserService userService;
	
	// 구매시 해당 유저의 주소 불러오기
	@PostMapping("/order/getAddress")
	public String checkUnique(Authentication auth) {
		String userEmail = auth.getName();
		User theUser = userService.findByEmail(userEmail);
		String theAddress = theUser.getAddress();
		return theAddress;
		}
}
