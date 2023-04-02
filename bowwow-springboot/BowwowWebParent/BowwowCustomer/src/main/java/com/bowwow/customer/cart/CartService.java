package com.bowwow.customer.cart;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Cart;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;
import com.bowwow.customer.product.ProductRepository;
import com.bowwow.customer.user.UserRepository;

@Service
@Transactional
public class CartService {
	
	public static final int PRODUCTS_PER_PAGE = 10;

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository proRepo;
	
	public boolean checkDuplicate(String userEmail, Integer productId) {
		User theUser = userRepo.findByEmail(userEmail);
		Product theProduct = proRepo.findById(productId).get();
		System.err.println(">>>>>>>>>>>>>>>>>>>>"+cartRepo.checkUserProduct(theUser, theProduct));
		if(cartRepo.checkUserProduct(theUser, theProduct) == null) return true;
		else return false;
	}
	
	public List<Cart> getUserCart(String userEmail) {
		Integer userId = userRepo.findByEmail(userEmail).getId();
		List<Cart> userCart = cartRepo.findByUserId(userId);
		return userCart;
	}
	
	public Cart save(Cart theCart) {
		return cartRepo.save(theCart);
	}

	public void deleteById(List<Integer> cartId) {
		for(Integer theId : cartId) {
			cartRepo.deleteById(theId);
		}
	}

	public Cart findById(Integer integer) {
		return cartRepo.findById(integer).get();
	}

}
