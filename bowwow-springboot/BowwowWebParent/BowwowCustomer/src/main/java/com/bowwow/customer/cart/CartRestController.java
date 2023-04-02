package com.bowwow.customer.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CartRestController {
	
	@Autowired
	private CartService service;
	
	@PostMapping("/cart/checkDup")
	public String checkDuplicate(Authentication auth, @RequestParam("productId") Integer productId) {
		if(auth == null) {
			return "NotUser";
		}
		return service.checkDuplicate(auth.getName(), productId)? "OK" : "Duplicated" ;
	}
	
}
