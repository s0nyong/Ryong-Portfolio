package com.bowwow.admin.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bowwow.common.entity.Order;
import com.bowwow.common.entity.OrderDetail;
import com.bowwow.common.entity.OrderStatus;
import com.bowwow.common.entity.Product;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class OrderDetailRepositoryTest {

	@Autowired
	private OrderDetailRepository repo;
	
//	@Test
//	public void testCreateOrderDetail() {
//		Order firstOrder = new Order(1);
//		Product pro = new Product(3);
//		
//		OrderDetail firstOrderDetail = new OrderDetail(1, 100, OrderStatus.ORDER);
//		firstOrderDetail.setOrder(firstOrder);
//		firstOrderDetail.setProduct(pro);
//		
//		OrderDetail savedOrderDetail = repo.save(firstOrderDetail);
//
//		assertThat(savedOrderDetail.getId()).isGreaterThan(0);
//		
//	}
}
