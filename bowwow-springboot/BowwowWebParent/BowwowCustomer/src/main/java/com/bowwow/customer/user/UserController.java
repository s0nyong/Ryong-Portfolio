package com.bowwow.customer.user;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bowwow.common.entity.Category;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;
import com.bowwow.customer.category.CategoryService;
import com.bowwow.customer.product.ProductService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService proService;
	
	@Autowired
    private CategoryService categoryService;

	
	@ModelAttribute("parentCategories")
    public List<Category> getParentCategories() {
        return categoryService.getParentCategories();
    }

	@GetMapping("/users")
	public String MyInfo(Principal p, Model model) {
		if(p == null) {
			User user = new User();
			model.addAttribute("user",user);
			model.addAttribute("usermessage","회원가입");
			return "users/users-form";
		}else {
			User user = userService.findByEmail(p.getName());
			model.addAttribute("user",user);
			model.addAttribute("usermessage","내정보 변경");
			return "users/users-form";
		}
		
	}
	
	@PostMapping("/users/edit")
	public String editMyInfo(@ModelAttribute("user") User user) {
		if(user.getId() == null) {
			user.setRole("일반");
			user.setEnabled(true);
		}
		userService.save(user);
		return "redirect:/";
	}
	
	@GetMapping("/likeit/list")
	public String likeItList(Model model,Principal p) {
		if(p ==null) {
			model.addAttribute("list",null);
		}else {
			User theUser = userService.findByEmail(p.getName());

			List<Product> listLikes = proService.listAll();

			List<Product> userLikes = new ArrayList<>();
			for(int i=0; i<listLikes.size(); i++) {
				Product product = listLikes.get(i);
				if(product.getLikes().contains(theUser)) {
					float discount = product.getDiscount();
					int discountPercent = (int) Math.round(discount * 100);
					float score = product.getScore();
					float roundedScore = Math.round(score * 10) / 10.0f;
			        product.setDiscount(discountPercent);
			        product.setScore(roundedScore);
					userLikes.add(product);
				}
			}
			
			model.addAttribute("list",userLikes);
		}		
		
		
		model.addAttribute("pageTitle","Like It List");				
		return "users/like-it";
	}
	
	@GetMapping("/likeit/delete/{id}")
	public String likeItDelete(@PathVariable("id") int id, Principal p) {
		User user = userService.findByEmail(p.getName());// 로그인 정보 가져오기	
		Product pro = proService.findById(id);
		pro.deleteLike(user);
		
		proService.save(pro);
		return "redirect:/likeit/list";
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {
		userService.kakaoLogin(code);
		
		return "redirect:/";
	}

}
