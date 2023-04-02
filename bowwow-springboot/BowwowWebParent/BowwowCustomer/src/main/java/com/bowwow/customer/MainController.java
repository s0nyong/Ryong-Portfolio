package com.bowwow.customer;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bowwow.common.entity.Category;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;
import com.bowwow.customer.category.CategoryService;
import com.bowwow.customer.product.ProductService;
import com.bowwow.customer.user.UserService;

@Controller
public class MainController {
	
	@Autowired
    private CategoryService categoryService;
	
	@Autowired
	private ProductService proService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public String viewdefaultPage(Model model, Principal p) {

		// 카테고리 드랍다운 메뉴 리스트
		List<Category> categoryList= categoryService.getParentCategories();
		model.addAttribute("parentCategories", categoryList);
		
		// 구매된 개수 count 기준 베스트 Top3 가져오기 (완료)
		List<Product> top3Products = proService.findTop3ByOrderByRank();
		if(top3Products.size() > 3) {
		    top3Products = top3Products.subList(0, 3);
		}
		model.addAttribute("bestproduct", top3Products);
		
		// 신상품 가장 최신 상품 3개 불러오기 (완료)
		List<Product> productsByDate = proService.list3ByDate();
		model.addAttribute("recentproduct", productsByDate);
		
		// 할인율 높은 순 핫딜 Top3 가져오기 (완료)
		List<Product> hotdealProducts = proService.findTop3ByOrderByDiscountDesc().stream().limit(3).collect(Collectors.toList());
		model.addAttribute("hotdealproduct", hotdealProducts);
		
		
		//Like 표시
		if(p != null) {
	    	List <Product> listpro = proService.listAll();
	    	List<Integer> userLikes =  new ArrayList<>();
	    	List<Integer> userUnLikes =  new ArrayList<>();
	    	User user = userService.findByEmail(p.getName());
	    	
	    	for(int i=0; i<listpro.size(); i++) {
	    		System.err.println(listpro.get(i).getLikes().contains(user));
    						
	    		if(listpro.get(i).getLikes().contains(user)) {	    
	    			userLikes.add(listpro.get(i).getId());	    			
	    		}
	    		if(!listpro.get(i).getLikes().contains(user)) {
    				userUnLikes.add(listpro.get(i).getId());
    			}
	    	}	    
	    	System.err.println(userLikes);
	    	System.err.println(userUnLikes);
	    	model.addAttribute("userUnLikes",userUnLikes);
	    	model.addAttribute("userLikes",userLikes);	  
	    }
		
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	
}
