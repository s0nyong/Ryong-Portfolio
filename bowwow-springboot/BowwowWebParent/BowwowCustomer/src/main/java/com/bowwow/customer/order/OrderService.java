package com.bowwow.customer.order;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Order;
import com.bowwow.common.entity.OrderDetail;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;

	public void save(Order orderNow) {
		orderRepo.save(orderNow);
	}

	public List<Order> findByUserId(Integer userId) {
		return orderRepo.findByUserId(userId);
	}

	public boolean checkProductPurchase(int userId, int productId) {
        List<Order> orders = orderRepo.findByUserId(userId);
        for (Order order : orders) {
            for (OrderDetail orderDetail : order.getOrder_detail()) {
                if (orderDetail.getProduct().getId().equals(productId)) {
                    return true;
                }
            }
        }
        return false;
    }

	
}
