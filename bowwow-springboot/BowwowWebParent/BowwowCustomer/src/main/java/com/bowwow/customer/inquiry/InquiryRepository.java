package com.bowwow.customer.inquiry;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.Inquiry;
import com.bowwow.common.entity.User;

public interface InquiryRepository extends PagingAndSortingRepository<Inquiry, Integer> {

	// 해당 상품의 부모 문의가 null인 문의만 등록일 기준 내림차순으로 불러오기
	public Page<Inquiry> findByProduct_IdAndParentIsNullOrderByRegDateDesc(int productId, Pageable pageable);

	// 해당 문의의 유저 불러오기
	public List<Inquiry> findByUser(User user);

	// 해당 유저의 부모 문의가 null인 문의만 등록일 기준 내림차순으로 불러오기
	public Page<Inquiry> findByUser_IdAndParentIsNullOrderByRegDateDesc(Integer integer, Pageable pageable);


}
