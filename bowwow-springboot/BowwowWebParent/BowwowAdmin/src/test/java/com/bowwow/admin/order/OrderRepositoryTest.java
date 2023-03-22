package com.bowwow.admin.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bowwow.common.entity.Order;
import com.bowwow.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository repo;
	
//	@Test
//	public void testCreateFirstOrder() {
//		
//		User userOrder = new User(1);
//		
//		Order firstOrder = new Order();
//		firstOrder.setUser(userOrder);
//		
//		Order savedOrder = repo.save(firstOrder);
//		assertThat(savedOrder.getId()).isGreaterThan(0);
//	}
	
}
