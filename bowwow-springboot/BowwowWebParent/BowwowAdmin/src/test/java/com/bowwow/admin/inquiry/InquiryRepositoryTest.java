package com.bowwow.admin.inquiry;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bowwow.common.entity.Inquiry;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class InquiryRepositoryTest {
	
	@Autowired
	private InquiryRepository inqRepo;
	
//	@Test
//	public void testCreateInquiry() {
//		User user = new User(1);
//		Product pro = new Product(1);
//		Inquiry inquiry = new Inquiry("맛이없어용");
//		inquiry.setUser(user);
//		inquiry.setProduct(pro);
//		
//		inqRepo.save(inquiry);
//	}
//
//	@Test
//	public void testCreateSubInquiry() {
//		Inquiry parent = new Inquiry(1);
//		User user = new User(1);
//		Inquiry inquiry = new Inquiry("맛이없어용", parent);
//		inquiry.setUser(user);
//		
//		inqRepo.save(inquiry);
//	}
//	
//	@Test
//	public void testGetInquiry() {
//		Inquiry inq = inqRepo.findById(1).get();
//		Set<Inquiry> children = inq.getChildren();
//		
//		for (Inquiry subInquiry : children) {
//			System.out.println("----" + subInquiry.getComment());
//		}
//	}
}
