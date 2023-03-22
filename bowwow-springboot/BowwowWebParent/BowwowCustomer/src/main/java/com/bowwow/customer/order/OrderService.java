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

	// 구매 저장
	public void save(Order orderNow) {
		orderRepo.save(orderNow);
	}

	// 해당 유저의 구매 불러오기
	public List<Order> findByUserId(Integer userId) {
		return orderRepo.findByUserId(userId);
	}

	// 해당 유저의 구매한 제품 체크
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
