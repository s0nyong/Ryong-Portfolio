package com.bowwow.customer.order;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.OrderDetail;

@Service
@Transactional
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository detailRepo;

	public void save(OrderDetail orderDetail) {
		detailRepo.save(orderDetail);
	}

	public OrderDetail findById(int orderDetailId) {
		return detailRepo.findById(orderDetailId).get();
	}
}
