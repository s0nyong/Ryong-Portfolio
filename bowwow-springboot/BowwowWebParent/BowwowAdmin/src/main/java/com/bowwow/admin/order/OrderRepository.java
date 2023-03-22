package com.bowwow.admin.order;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {

}
