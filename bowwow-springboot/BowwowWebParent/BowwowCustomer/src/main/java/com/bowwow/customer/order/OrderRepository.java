package com.bowwow.customer.order;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {

	// 해당 유저의 구매 불러오기
	@Query("SELECT o FROM Order o WHERE o.user.id = :id")
	public List<Order> findByUserId(int id);

}
