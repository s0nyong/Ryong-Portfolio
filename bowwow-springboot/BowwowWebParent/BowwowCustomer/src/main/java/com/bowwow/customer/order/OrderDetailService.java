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

	// 구매 세부 내용 저장
	public void save(OrderDetail orderDetail) {
		detailRepo.save(orderDetail);
	}

	// 해당 구매 세부 내용 불러오기
	public OrderDetail findById(int orderDetailId) {
		return detailRepo.findById(orderDetailId).get();
	}
}
