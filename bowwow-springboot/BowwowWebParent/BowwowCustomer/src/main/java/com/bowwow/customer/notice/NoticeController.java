package com.bowwow.customer.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.bowwow.common.entity.Category;
import com.bowwow.common.entity.Notice;
import com.bowwow.customer.category.CategoryService;


@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;

	@Autowired
    private CategoryService categoryService;
	
	// 카테고리 불러오기
	@ModelAttribute("parentCategories")
    public List<Category> getParentCategories() {
        return categoryService.getParentCategories();
    }
	
	// 공지 불러오기
	@GetMapping("/notice")
	public String listNotice(Model model) {
//		List<Notice> listnotice = noticeService.listAll();
//		model.addAttribute("listnotice", listnotice);
		return listByPage(model, 1, null);
	}
	
	// 공지 페이지로 불러오기
	@GetMapping("/notice/page/{pageNum}")
	public String listByPage(Model model, @PathVariable(name = "pageNum") int pageNum,
			@Param("keyword") String keyword) {
		Page<Notice> page = noticeService.listByPage(pageNum, keyword);
		List<Notice> listnotice = page.getContent();
		
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("listnotice", listnotice);
		
		return "notice/notice";
	}
	
	// 해당 공지 불러오기
	@GetMapping("/notice/detail/{id}")
	public String deatilNotice(Model model, @PathVariable("id") int id) {
		Notice notice = noticeService.findById(id);
		model.addAttribute("notice", notice);
		return "notice/notice_detail";
	}
}
