package com.bowwow.customer.inquiry;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bowwow.common.entity.Category;
import com.bowwow.common.entity.Inquiry;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;
import com.bowwow.customer.category.CategoryService;
import com.bowwow.customer.product.ProductService;
import com.bowwow.customer.user.UserService;


@Controller
public class InquiryController {
	
	@Autowired
	private InquiryService inquiryService;
	
	@Autowired
	private ProductService proService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private CategoryService categoryService;
	
	// 카테고리 불러오기
	@ModelAttribute("parentCategories")
    public List<Category> getParentCategories() {
        return categoryService.getParentCategories();
    }
	
	// 상품에 대한 문의 추가
	@PostMapping("/inquiry/add")
	public String inquiryAdd(@ModelAttribute("inquiry")Inquiry inquiry, @RequestParam("id") Integer id, @RequestParam("comment")String comment, Principal principal) {
		if(principal == null) {
			return "redirect:/login";
		}
		Product pro = proService.findById(id);
	    User user = userService.findByEmail(principal.getName());
	    Inquiry inquiryAdd = new Inquiry(pro, user, comment);
	    inquiryService.save(inquiryAdd);
	    
	    return "redirect:/product/detail/" + pro.getId() + "/1";
	}
	
	// 상품에 대한 내 문의 불러오기
	@GetMapping("/inquiry/my/{pageNum}")
	public String myInquiry(Model model, Principal p, @PathVariable(name = "pageNum") int pageNum) {
		if(p == null) {
			return "redirect:/login";
		}
		
		User user = userService.findByEmail(p.getName());
		
		Pageable pageable = PageRequest.of(pageNum - 1, InquiryService.INQUIRY_PER_PAGE);
		Page<Inquiry> page = inquiryService.findParentInquiriesByUser(user.getId(), pageable); // 해당 사용자의 부모 문의 가져옴
		List<Inquiry> parentInquiries = page.getContent();
		
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("parentInquiries", parentInquiries);
	
	    return "users/myinquiry";
	}
	
	// 해당 삼품에 대한 전체 문의 볼러오기
	@GetMapping("/inquiry/list/{productId}/{pageNum}")
	public String inquiryList(Model model, @PathVariable("productId") int productId, @PathVariable(name = "pageNum") int pageNum, Inquiry inquiry, Principal p) {
		if(p != null) {
		User user = userService.findByEmail(p.getName());
		model.addAttribute("user", user);
		}
		Pageable pageable = PageRequest.of(pageNum - 1, InquiryService.INQUIRY_PER_PAGE);
		Page<Inquiry> page = inquiryService.findParentInquiriesByProduct(productId, pageable); // 해당 삼품의 부모 문의 가져옴
		List<Inquiry> parentInquiries = page.getContent();
		
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("parentInquiries", parentInquiries);
	
		return "users/inquiry";
	}
	
	// 해담 상품의 내 문의 수정
	@PostMapping("/inquiry/edit")
	public String inquiryEdit(@RequestParam(name = "pageNum") String pageNum, @RequestParam(name="id")Integer id,@RequestParam("comment")String comment, Inquiry inquiry, Model model) {
		
		Inquiry inq = inquiryService.findById(id);
		inq.setComment(comment);
		inq.setRegDate(LocalDateTime.now());
		inquiryService.save(inq);
		
		Product theProduct = proService.findById(inq.getProduct().getId()); // Inquiry 객체의 해당 상품 객체 가져옴
		
		if (pageNum == null || pageNum.trim().isEmpty()) {
			  return "redirect:/product/detail/" + theProduct.getId();
			}
		return "redirect:/inquiry/list/" + theProduct.getId() + "/" + pageNum;
	}
	
	// 해당 상품의 내 문의 삭제
	@RequestMapping(value ="/inquiry/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String inquiryDelete(@RequestParam(name = "pageNum") String pageNum, @RequestParam(name="id")Integer id) {
		
		Inquiry inq = inquiryService.findById(id);
		inquiryService.deleteById(id);
		
		Product theProduct = proService.findById(inq.getProduct().getId()); // Inquiry 객체의 해당 상품 객체 가져옴
		
		if (pageNum == null || pageNum.trim().isEmpty()) {
			  return "redirect:/product/detail/" + theProduct.getId();
			}
		return "redirect:/inquiry/list/" + theProduct.getId() + "/" + pageNum;
	}
}
