package com.bowwow.admin.inquiry;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bowwow.admin.product.ProductNotFoundException;
import com.bowwow.admin.product.ProductService;
import com.bowwow.admin.user.UserService;
import com.bowwow.common.entity.Inquiry;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;

@Controller
public class InquiryController {

	@Autowired
	private InquiryService inService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService proService;
	
	//============================================전체문의글 볼 때 ↓ all 페이지==========================================
	@GetMapping("/inquiry")
	public String listProduct(Model model) {

		return listInquiry(model, 1, null);
	}
	
	@GetMapping("/inquiry/{pageNum}")
	public String listInquiry(Model model, @PathVariable(name = "pageNum") int pageNum,
			@RequestParam(name="keyword",required=false)String keyword) {
				
		Page<Inquiry> page = inService.findParentInquiriesByProduct(pageNum,keyword);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		
		List<Inquiry> listInquiry = page.getContent();
		model.addAttribute("listInquiry",listInquiry);
		
		model.addAttribute("keyword", keyword);
		
		model.addAttribute("type","all");
		return "inquiry/inquiry";
	}
	
	@PostMapping("/inquiry/answer")
	public String answerComment(@RequestParam("id")int parentId, 
								@RequestParam("comment")String comment,
								@RequestParam("productId")int productId,
								@RequestParam(name="type",required=false)String type, Principal p ) throws ProductNotFoundException {
		
		User user = userService.findByEmail(p.getName());
		Product product = proService.findById(productId);
		Inquiry inquiry = inService.findById(parentId);
		
		Inquiry theInquiry = new Inquiry(product,user,comment,inquiry);
		inService.save(theInquiry);
		
		if(type.equals("all")) {
			return "redirect:/inquiry";	
		}else {
			return "redirect:/inquiry/new";
		}
			
	}
	
	@PostMapping("/inquiry/edit")
	public String answerEdit(@RequestParam("childId")int childId, 
							 @RequestParam("commentedit")String commentedit) throws ProductNotFoundException {	
		Inquiry inquiry = inService.findById(childId);
		inquiry.setComment(commentedit);
		inquiry.setRegDate(LocalDateTime.now());			
		inService.save(inquiry);		
		return "redirect:/inquiry";
	}
	
	@PostMapping("/inquiry/delete")
	public String answerDelete(@RequestParam("childId")int childId) {
		
		inService.deleteById(childId);
		
		return "redirect:/inquiry";
	}
	
	//==============================새로운 문의글만 볼 때 ↓ new 페이지==================================================
	
	@GetMapping("/inquiry/new")
	public String listNewProduct(Model model) {

		return listNewInquiry(model, 1);
	}
	
	@GetMapping("/inquiry/new/{pageNum}")
	public String listNewInquiry(Model model, @PathVariable(name = "pageNum") int pageNum) {
				
		Page<Inquiry> page = inService.findChildrenInquiriesByProduct(pageNum);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		
		List<Inquiry> listInquiry = page.getContent();
		model.addAttribute("listInquiry",listInquiry);
		
		model.addAttribute("type","new");
		
		return "inquiry/inquiry-new";
	}
}
