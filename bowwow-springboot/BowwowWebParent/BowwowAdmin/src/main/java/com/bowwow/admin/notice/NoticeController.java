package com.bowwow.admin.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bowwow.common.entity.Notice;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/notice")
	public String listNotice(Model model) {
//		List<Notice> listnotice = noticeService.listAll();
//		model.addAttribute("listnotice", listnotice);
		return listByPage(model, 1, null);
	}
	
	@GetMapping("/notice/page/{pageNum}")
	public String listByPage(Model model, @PathVariable(name = "pageNum") int pageNum,
			@Param("keyword") String keyword) {
		Page<Notice> page = noticeService.listByPage(pageNum, keyword);
		List<Notice> listnotice = page.getContent();
		
		long startCount = (pageNum - 1) * NoticeService.NOTICE_PER_PAGE + 1;
	    long endCount = startCount + NoticeService.NOTICE_PER_PAGE - 1;
	    if (endCount > page.getTotalElements()) {
	    	endCount = page.getTotalElements();
	    }
		
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("startCount", startCount);
	    model.addAttribute("endCount", endCount);
	    model.addAttribute("totalItems", page.getTotalElements());
	    
	    model.addAttribute("keyword", keyword);
	    
	    model.addAttribute("listnotice", listnotice);
		
		return "notice/notice";
	}
	
	@GetMapping("/notice/new")
	public String addNotice(Model model) {
		Notice notice = new Notice();
		model.addAttribute("notice", notice);
		return "notice/notice_new";
	}
	
	@PostMapping("/notice/save")
	public String saveNotice(@ModelAttribute("notice") Notice notice) {
		noticeService.save(notice);
		return "redirect:/notice";
	}
	
	@GetMapping("/notice/detail/{id}")
	public String deatilNotice(Model model, @PathVariable("id") int id) {
		Notice notice = noticeService.findById(id);
		model.addAttribute("notice", notice);
		return "notice/notice_detail";
	}
	
	@RequestMapping(value="/notice/edit/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editNotice(@PathVariable("id") int id, Model model) {
		Notice notice = noticeService.findById(id);
		model.addAttribute("notice",notice);
					
		return "notice/notice_new";
	}
	
	@RequestMapping(value="/notice/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteNotice(@PathVariable("id") int id) {
			noticeService.deleteById(id);
			
		return "redirect:/notice";	
	}
}
