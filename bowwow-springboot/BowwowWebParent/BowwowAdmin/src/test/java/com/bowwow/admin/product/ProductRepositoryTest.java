package com.bowwow.admin.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.bowwow.common.entity.Category;
import com.bowwow.common.entity.Product;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {

//	@Autowired
//	private ProductRepository proRepo;
//	
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Test 
//	public void testCreateProduct() {
//		Category firstCate = entityManager.find(Category.class,2);
//		
//		Product firstProduct = new Product("아주", "맛있는사료~~~", 100, true);
//		firstProduct.setCategoryId(firstCate);
//		
//		Product savedProduct = proRepo.save(firstProduct);
//		assertThat(savedProduct.getId()).isGreaterThan(0);
//	}
}
