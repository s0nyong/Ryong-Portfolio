package com.bowwow.customer.review;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.Review;
import com.bowwow.common.entity.User;
import com.bowwow.customer.product.ProductService;
import com.bowwow.customer.user.UserService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ProductService proService;
	
	@Autowired
	private UserService userService;
	
	//리뷰입력&별점업데이트
		@PostMapping("/product/reviewadd")
		public String reviewAdd(@ModelAttribute("review")Review review,@RequestParam("id") Integer id,@RequestParam("comment")String Comment,
				@RequestParam("score")float score, @RequestParam(name = "pageNum") int pageNum, Principal principal) {
			if(principal == null) {
				return "redirect:/login";
			}
			Product pro = proService.findById(id);
		    User user = userService.findByEmail(principal.getName());
		    Review reviewAdd = new Review(pro,user,Comment,score);
		    reviewService.save(reviewAdd);
		    
		    
		    float reviewScore = reviewService.getProductIdAvg(id);
		    pro.setScore(reviewScore);
		    proService.save(pro);

		    
		    return "redirect:/product/detail/" + pro.getId() +"/" + pageNum;
		}
		
		//리뷰수정
		@PostMapping("/product/reviewedit")
		public String reviewEdit(@RequestParam(name="id")Integer id,@RequestParam("comment")String comment, @RequestParam(name = "pageNum") int pageNum, Review review,Model model) {
			Review theReview = reviewService.findById(id);
			theReview.setComment(comment);
			theReview.setRegDate(LocalDateTime.now());
			reviewService.save(theReview);
			Product theProduct = proService.findById(theReview.getProduct().getId());
			return "redirect:/product/detail/" + theProduct.getId() +"/" + pageNum;
		}
		
		@RequestMapping(value ="/product/reviewdelete", method = {RequestMethod.GET, RequestMethod.POST})
		public String reviewDelete(@RequestParam(name="id")Integer id, @RequestParam(name = "pageNum") int pageNum) {
			Review theReview = reviewService.findById(id);
			Product theProduct = proService.findById(theReview.getProduct().getId());
			reviewService.deleteById(id);
			return "redirect:/product/detail/" + theProduct.getId() +"/" + pageNum;
		}
	
}
