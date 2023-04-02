package com.bowwow.customer.category;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.bowwow.common.entity.Category;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;
import com.bowwow.customer.product.ProductService;
import com.bowwow.customer.user.UserService;

@Controller
public class CategoryController {

    @Autowired
	private ProductService proService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private UserService userService;
    
    // 카테고리 불러오기
	@ModelAttribute("parentCategories")
    public List<Category> getParentCategories() { // 메서드의 반환 값을 모델에 추가
        return categoryService.getParentCategories(); // 모델 객체 뷰에서 참조 가능
    }

	// 최근 본 상품 불러오기
	@ModelAttribute("recentProducts")
    public List<Product> getRecentProducts(HttpSession session, HttpServletRequest request) {
		// 세션에서 사용자의 최근 본 상품 정보를 조회
	    List<Integer> recentProducts = (List<Integer>) session.getAttribute("recentProducts");
	    List<Product> listRecentProducts = new ArrayList<>();
	    if(recentProducts != null) {
	    	for(Integer recentProductsId :recentProducts) {
		    	Product theRecentProduct = proService.findById(recentProductsId);
		    	listRecentProducts.add(theRecentProduct);
		    }
	        return listRecentProducts;
	    }
	    return null;
    }
    
	// 카테고리 목록 불러오기
    @GetMapping("/category")
	public String showCategory(Model model) {
		List<Category> parentCategories = categoryService.getParentCategories();
		model.addAttribute("parentCategories", parentCategories);
		return "navigation";
	}
    
    // 해당 카테고리에 속한 상품 리스트 불러오기
    @GetMapping("/category/{categoryId}/{pageNum}")
    public String showCategory(@PathVariable("categoryId") Integer categoryId, Model model, Principal p, @PathVariable(name = "pageNum") int pageNum) {
    	
    	Category category = categoryService.getCategoryById(categoryId); // 해당 카테고리 불러옴
        Page<Product> productList = null; // Page 객체를 생성하고 초기값을 null로 설정
        if (category != null) {
            List<Category> childCategories = categoryService.getChildrenByParentCategory(categoryId); // categoryId에 해당하는 Category 객체 가져옴
            if (childCategories.isEmpty()) {
                productList = proService.listByCategory(category, pageNum); // 해당 카테고리 제품 리스트 가져옴
            } else {
                productList = proService.listByChildCategories(childCategories, pageNum); // 자식 카테고리 제품 리스트 가져옴
            }
            model.addAttribute("currentPage", pageNum);
            model.addAttribute("totalPages", productList.getTotalPages());
            model.addAttribute("listproduct", productList.getContent());
        }
        
        if(p != null) {
        	
	    	List <Product> listpro = proService.listAll();
	    	List<Integer> userLikes =  new ArrayList<>();
	    	List<Integer> userUnLikes =  new ArrayList<>();
	    	User user = userService.findByEmail(p.getName());
	    	
	    	for(int i=0; i<listpro.size(); i++) {
	    		if(listpro.get(i).getLikes().contains(user)) {	    
	    			userLikes.add(listpro.get(i).getId());	    			
	    		}
	    		if(!listpro.get(i).getLikes().contains(user)) {
    				userUnLikes.add(listpro.get(i).getId());
    			}
	    	}	    
	    	model.addAttribute("userUnLikes",userUnLikes);
	    	model.addAttribute("userLikes",userLikes);
	    }
        
        return "product/category";
    }
}