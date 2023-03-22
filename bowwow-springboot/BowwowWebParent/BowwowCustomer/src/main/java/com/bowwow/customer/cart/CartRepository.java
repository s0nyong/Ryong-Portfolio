package com.bowwow.customer.cart;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.Cart;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;

public interface CartRepository extends PagingAndSortingRepository<Cart, Integer> {
	
	// 해당 유저의 장바구니 해당 제품 불러오기
	@Query("SELECT c FROM Cart c WHERE c.user =?1 AND c.product =?2")
	public Cart checkUserProduct(User user, Product product);

	// 해당 유저의 장바구니 제품 리스트 불러오기
	public List<Cart> findByUserId(Integer userId);
}
