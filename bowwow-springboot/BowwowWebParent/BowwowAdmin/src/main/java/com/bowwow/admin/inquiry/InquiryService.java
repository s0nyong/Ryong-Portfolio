package com.bowwow.admin.inquiry;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Inquiry;

@Service
@Transactional
public class InquiryService {

	
	public static final int PRODUCTS_PER_PAGE = 5;
	
	@Autowired
	private InquiryRepository inRepo;
	
	public Page<Inquiry> findParentInquiriesByProduct(int pageNum,String keyword) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		if (keyword != null && !keyword.trim().isEmpty()) {
	       return inRepo.findByParentIsNullOrderByRegDateDesc1(pageable,keyword);
	   }
		return inRepo.findByParentIsNullOrderByRegDateDesc(pageable);
	}
	
	public Page<Inquiry> findChildrenInquiriesByProduct(int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
	       return inRepo.findByParentIsNullAndChildrenIsEmptyOrderByRegDateDesc(pageable);
	   }

	public Inquiry findById(int parentId) {		
		return inRepo.findById(parentId).get();
	}

	public void save(Inquiry theInquiry) {
		inRepo.save(theInquiry);
		
	}

	public void deleteById(int parentId) {
		inRepo.deleteById(parentId);
		
	}



	

	

	



	
}
