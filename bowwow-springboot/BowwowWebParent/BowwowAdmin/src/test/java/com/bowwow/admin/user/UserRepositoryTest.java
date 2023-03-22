package com.bowwow.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.bowwow.admin.product.ProductRepository;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

//	@Autowired
//	private UserRepository userRepo;
//	
//	@Autowired
//	private ProductRepository proRepo;
//	
//	@Test
//	public void testCreateFirstUser() {
//		
//		User firstUser = new User("seoul","sss@email.com",true ,"subin","sup","1111","00009999",0 ,"A");
//		User saveUser = userRepo.save(firstUser);
//		assertThat(saveUser.getId()).isGreaterThan(0);
//	}
//
//	@Test
//	public void testUpdateUserLike() {
//		User firstUser = userRepo.findById(1).get();
//		Product firstPro = proRepo.findById(1).get();
//		
//		firstUser.addLike(firstPro);
//		
//		userRepo.save(firstUser);
//		
//	}
//	
//	@Test
//	   public void testCreateSecondUser() {
//	      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	      String rawPassword = "1";
//	      String EncodedPW = passwordEncoder.encode(rawPassword);
//	      User secondUser = new User("seoul","admin@email.com",true ,"admin","admin",EncodedPW,"00009999",0 ,"ADMIN");
//	      User saveUser = userRepo.save(secondUser);
//	      assertThat(saveUser.getId()).isGreaterThan(0);
//	   }
}