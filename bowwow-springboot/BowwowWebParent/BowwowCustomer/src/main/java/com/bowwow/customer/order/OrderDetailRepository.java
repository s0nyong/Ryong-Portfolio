package com.bowwow.customer.order;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.OrderDetail;

public interface OrderDetailRepository extends PagingAndSortingRepository<OrderDetail, Integer> {

}
