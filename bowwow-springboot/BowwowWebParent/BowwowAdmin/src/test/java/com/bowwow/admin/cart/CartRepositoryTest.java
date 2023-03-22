package com.bowwow.admin.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bowwow.common.entity.Cart;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CartRepositoryTest {

   @Autowired
      private CartRepository repo;
   
//   @Test
//   public void testCreateFirstCart() {
//      User firstuser = new User(1);
//      Product firstproduct = new Product(1);
//      
//      Cart firstcart = new Cart(1);
//      
//      firstcart.setUser(firstuser);
//      firstcart.setProduct(firstproduct);
//      
//      repo.save(firstcart);
//   }
}