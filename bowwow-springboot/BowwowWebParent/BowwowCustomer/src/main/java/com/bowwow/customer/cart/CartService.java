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
	
	// 장바구니 제품 중복 체크
	public boolean checkDuplicate(String userEmail, Integer productId) {
		User theUser = userRepo.findByEmail(userEmail);
		Product theProduct = proRepo.findById(productId).get();
		if(cartRepo.checkUserProduct(theUser, theProduct) == null) return true;
		else return false;
	}
	
	// 해당 유저 장바구니 리스트 불러오기
	public List<Cart> getUserCart(String userEmail) {
		Integer userId = userRepo.findByEmail(userEmail).getId();
		List<Cart> userCart = cartRepo.findByUserId(userId);
		return userCart;
	}
	
	// 장바구니 제품 추가
	public Cart save(Cart theCart) {
		return cartRepo.save(theCart);
	}

	// 장바구니 제품 삭제
	public void deleteById(List<Integer> cartId) {
		for(Integer theId : cartId) {
			cartRepo.deleteById(theId);
		}
	}

	// 해당 장바구니 불러오기
	public Cart findById(Integer integer) {
		return cartRepo.findById(integer).get();
	}

}
