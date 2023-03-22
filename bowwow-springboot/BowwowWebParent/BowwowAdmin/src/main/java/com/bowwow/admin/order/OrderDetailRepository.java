package com.bowwow.admin.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.OrderDetail;

public interface OrderDetailRepository extends PagingAndSortingRepository<OrderDetail, Integer> {
	
	@Query("SELECT od FROM OrderDetail od WHERE CONCAT(od.order, ' ', od.order.user.name, ' ', od.product.mainName, ' ', od.product.subName, ' ', od.status) LIKE %?1%")
	public Page<OrderDetail> findAll(String keyword, Pageable pageable);
}
