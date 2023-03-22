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
	
	
	public Page<Inquiry> findByParentIsNullOrderByRegDateDesc(Pageable pageable);
	
	@Query("SELECT i FROM Inquiry i WHERE i.parent IS NULL AND CONCAT(IFNULL(i.comment,''),' ',IFNULL(i.product.mainName,''), ' ', IFNULL(i.product.subName,''),' ',IFNULL(i.user.email,''),' ',IFNULL(i.user.nickName,'')) LIKE %:keyword% ORDER BY i.regDate DESC")
	public Page<Inquiry> findByParentIsNullOrderByRegDateDesc1(Pageable pageable, String keyword);
	
	public Page<Inquiry> findByParentIsNullAndChildrenIsEmptyOrderByRegDateDesc(Pageable pageable);
	
}
