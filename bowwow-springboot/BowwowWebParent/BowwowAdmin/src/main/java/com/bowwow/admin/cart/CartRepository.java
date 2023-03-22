package com.bowwow.admin.cart;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.Cart;

public interface CartRepository extends PagingAndSortingRepository<Cart, Integer> {

}
