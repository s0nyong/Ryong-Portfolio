package com.bowwow.customer.cart;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.bowwow.common.entity.Cart;
import com.bowwow.common.entity.Category;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;
import com.bowwow.customer.category.CategoryService;
import com.bowwow.customer.product.ProductService;
import com.bowwow.customer.user.UserService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	
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
	
	@GetMapping("/cart")
	public String listUsersCart(Principal p, Model model) {
		List<Cart> cartList = new ArrayList<>();
		if(p == null) {
			cartList = null;
		}else {
			cartList = cartService.getUserCart(p.getName());
			
	    }
		model.addAttribute("cartList", cartList);
		return "users/cart";
	}
	
	@GetMapping("/cart/add")
	public String addCart(@RequestParam(name="productId") Integer productId, Principal p, @RequestParam("quantity") int count) {
		User theUser = userService.findByEmail(p.getName());
		Product theProduct = proService.findById(productId);
		Cart theCart = new Cart(theUser, theProduct, count);
		cartService.save(theCart);
		
		return "redirect:/cart";
	}
	
	
	@GetMapping("/cart/delete")
	public String deleteCartItem(@RequestParam(name="selectdItem") ArrayList<Integer> theCartId) {
		if(!theCartId.isEmpty()) {
			cartService.deleteById(theCartId);
		}
		
		return "redirect:/cart";
	}
}
