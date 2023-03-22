package com.bowwow.customer.inquiry;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Inquiry;
import com.bowwow.common.entity.User;

@Service
@Transactional
public class InquiryService {

	public static final int INQUIRY_PER_PAGE = 5;
	
	@Autowired
	private InquiryRepository inquiryRepo;

	// 해당 상품의 부모 문의 불러오기
	public Page<Inquiry> findParentInquiriesByProduct(int productId, Pageable pageable) {
	    return inquiryRepo.findByProduct_IdAndParentIsNullOrderByRegDateDesc(productId, pageable);
	}

	// 문의 저장
	public void save(Inquiry inquiry) {
		inquiryRepo.save(inquiry);
	}

	// 해당 문의 불러오기
	public Inquiry findById(Integer id) {
		return inquiryRepo.findById(id).get();
	}

	// 해당 문의 삭제
	public void deleteById(Integer id) {
		inquiryRepo.deleteById(id);
	}

	// 해당 유저의 문의 불러오기
	public List<Inquiry> findByUser(User user) {
		return inquiryRepo.findByUser(user);
	}

	// 해당 유저의 부모 문의 불러오기
	public Page<Inquiry> findParentInquiriesByUser(Integer integer, Pageable pageable) {
		return inquiryRepo.findByUser_IdAndParentIsNullOrderByRegDateDesc(integer, pageable);
	}
}
