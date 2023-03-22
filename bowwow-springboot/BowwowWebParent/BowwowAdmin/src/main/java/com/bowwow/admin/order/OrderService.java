package com.bowwow.admin.order;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.OrderDetail;



@Service
@Transactional
public class OrderService {
	
	public static final int ORDER_PER_PAGE = 10;
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	

	public List<OrderDetail> findAll() {
		return (List<OrderDetail>) orderDetailRepo.findAll();
	}


	public Page<OrderDetail> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum - 1, ORDER_PER_PAGE, sort);
		if (keyword != null && !keyword.trim().isEmpty()) {
			return orderDetailRepo.findAll(keyword, pageable);
		}
		return orderDetailRepo.findAll(pageable);
		
	}


	public OrderDetail findById(int theId) {
		return orderDetailRepo.findById(theId).get();
	}


	public void save(OrderDetail theOrderDetail) {
		orderDetailRepo.save(theOrderDetail);
	}
	
	
}
