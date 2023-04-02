package com.bowwow.customer.order;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {

	@Query("SELECT o FROM Order o WHERE o.user.id = :id")
	public List<Order> findByUserId(int id);

}
