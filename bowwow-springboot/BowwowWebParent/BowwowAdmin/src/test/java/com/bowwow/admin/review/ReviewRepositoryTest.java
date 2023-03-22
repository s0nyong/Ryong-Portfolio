package com.bowwow.admin.review;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.Review;
import com.bowwow.common.entity.User;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ReviewRepositoryTest {
	
//	@Autowired
//	private ReviewRepository repo;
//	
//	@Test
//	public void testCreateReview() {
//		User firstUser = new User(1);
//		Product firstPro = new Product(1);
//		
//		Review firstReview = new Review("좋아용1", 3.0f);
//		firstReview.setUser(firstUser);
//		firstReview.setProduct(firstPro);
//		
//		Review savedReview = repo.save(firstReview);
//		
//		assertThat(savedReview.getId()).isGreaterThan(0);
//	}

}
