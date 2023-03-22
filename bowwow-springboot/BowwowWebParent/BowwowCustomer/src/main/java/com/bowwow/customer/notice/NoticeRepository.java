package com.bowwow.customer.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.Notice;

public interface NoticeRepository extends PagingAndSortingRepository<Notice, Integer> {

	// 제목이 키워드와 같은 공지 불러오기
	@Query("SELECT n FROM Notice n WHERE n.title LIKE %:keyword%")
	Page<Notice> findAll(String keyword, Pageable pageable);
}
