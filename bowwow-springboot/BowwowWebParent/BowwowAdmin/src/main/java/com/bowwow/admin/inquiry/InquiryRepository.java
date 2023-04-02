package com.bowwow.admin.inquiry;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bowwow.common.entity.Inquiry;

@Repository
public interface InquiryRepository extends PagingAndSortingRepository<Inquiry, Integer> {
	
	//모든 문의 글 불러오기
	public Page<Inquiry> findByParentIsNullOrderByRegDateDesc(Pageable pageable);
	
	
	//문의 검색
	@Query("SELECT i FROM Inquiry i WHERE i.parent IS NULL AND CONCAT(IFNULL(i.comment,''),' ',IFNULL(i.product.mainName,''), ' ', IFNULL(i.product.subName,''),' ',IFNULL(i.user.email,''),' ',IFNULL(i.user.nickName,'')) LIKE %:keyword% ORDER BY i.regDate DESC")
	public Page<Inquiry> findByParentIsNullOrderByRegDateDesc1(Pageable pageable, String keyword);
	
	
	// 답변 없는 문의글 불러올 때
	public Page<Inquiry> findByParentIsNullAndChildrenIsEmptyOrderByRegDateDesc(Pageable pageable);
	
}
