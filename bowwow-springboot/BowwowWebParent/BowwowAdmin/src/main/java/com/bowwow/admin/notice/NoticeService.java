package com.bowwow.admin.notice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Notice;

@Service
@Transactional
public class NoticeService {
	
	public static final int NOTICE_PER_PAGE = 10;
	
	@Autowired
	private NoticeRepository noticeRepo;

	public List<Notice> listAll() {
		
		Sort sort = Sort.by(Direction.DESC, "date");
		return (List<Notice>) noticeRepo.findAll(sort);
	}

	public Page<Notice> listByPage(int pageNum, String keyword) {
		Sort sort = Sort.by(Direction.DESC, "id");
		Pageable pageable = PageRequest.of(pageNum - 1, NOTICE_PER_PAGE, sort);
	    if (keyword != null && !keyword.trim().isEmpty()) {
	        return noticeRepo.findAll(keyword.trim(), pageable);
	    }
	    return noticeRepo.findAll(pageable);
	}

	public Notice save(Notice notice) {

		return noticeRepo.save(notice);
		
	}

	public Notice findById(int id) {
		return noticeRepo.findById(id).get();
	}

	public void deleteById(int id) {
		
		noticeRepo.deleteById(id);
	}
	

}
