package com.bowwow.admin.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.Notice;

public interface NoticeRepository extends PagingAndSortingRepository<Notice, Integer> {

	@Query("SELECT n FROM Notice n WHERE n.title LIKE %:keyword%")
	Page<Notice> findAll(String keyword, Pageable pageable);


}
