package com.bowwow.customer.product;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.bowwow.common.entity.Category;
import com.bowwow.common.entity.Inquiry;
import com.bowwow.common.entity.Order;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.Review;
import com.bowwow.common.entity.User;
import com.bowwow.customer.category.CategoryService;
import com.bowwow.customer.inquiry.InquiryService;
import com.bowwow.customer.order.OrderService;
import com.bowwow.customer.review.ReviewService;
import com.bowwow.customer.user.UserService;

@Controller
public class ProductController {
	
	private static final int MAX_RECENTLY_PRODUCTS = 5;
	
	@Autowired
	private ProductService proService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private CategoryService categoryService;
	
	@Autowired
	private InquiryService inquiryService;
	
	@Autowired
	private OrderService orderService;
	
	// 카테고리 불러오기
	@ModelAttribute("parentCategories")
    public List<Category> getParentCategories() {
        return categoryService.getParentCategories();
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
	
	// 제품 불러오기
	@GetMapping("/product")
	public String listProduct(Model model, Principal p) {
		return listByPage(model, 1, null, p);
	}
	
	// 제품 페이지로 불러오기
	@GetMapping("/product/page/{pageNum}")
	public String listByPage(Model model, @PathVariable(name = "pageNum") int pageNum,
			@Param("keyword") String keyword, Principal p) {
		Page<Product> page = proService.listByPage(pageNum, keyword);
		List<Product> listproduct = page.getContent();
		
		long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
	    long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
	    if (endCount > page.getTotalElements()) {
	    	endCount = page.getTotalElements();
	    }
		
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("startCount", startCount);
	    model.addAttribute("endCount", endCount);
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("listproduct", listproduct);
		
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
	    	model.addAttribute("listproduct", listproduct);
	    }
		return "product/product";
	}
	
	// 해당 상품의 디테일 페이지 불러오기
	@GetMapping("/product/detail/{id}/{pageNum}")
	public String detailProduct(Model model, @PathVariable(name = "pageNum") int pageNum, @PathVariable("id") int id, Order order, Inquiry inquiry, Review review, Principal p, HttpSession session, HttpServletRequest request) {
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
	    	model.addAttribute("user", user);
	    }
		
		Product pro = proService.findById(id);
		float discount = pro.getDiscount();
		int discountPercent = (int) Math.round(discount * 100);
		float score = pro.getScore();
		float roundedScore = Math.round(score * 10) / 10.0f;
		
		List<Product> mainName1= proService.findByMainName(pro.getMainName());
	    model.addAttribute("productMainName",mainName1);
		
		if(p != null) {
			User user = userService.findByEmail(p.getName());
			int userId = user.getId();
			
			// 해당 상품을 구매했는지 확인
		    boolean hasPurchased = orderService.checkProductPurchase(userId, id);
	
		    // 구매한 경우에만 처리
		    if (hasPurchased) {
		        model.addAttribute("checkBuyer", "OK");
		    } else {
		        model.addAttribute("checkBuyer", "No");
		    }
		}
		
		Page<Review> page = reviewService.findByProductId(id,pageNum);
		List<Review> riview = page.getContent();
		Integer countReview = reviewService.getfindById(id);
		model.addAttribute("countReview", countReview);
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listreview", riview);
		
		Pageable pageable = PageRequest.of(0, 3, Sort.by("RegDate").descending());
		Page<Inquiry> parentInquiries = inquiryService.findParentInquiriesByProduct(id, pageable);

		model.addAttribute("parentInquiries", parentInquiries);
		
		model.addAttribute("product", pro);
		model.addAttribute("scorePercent", roundedScore);
		model.addAttribute("discountPercent", discountPercent);
		
		// 최근 본 상품 세션에 저장
	    List<Integer> recentProducts = (List<Integer>) session.getAttribute("recentProducts");
	    if (recentProducts == null) {
	        recentProducts = new ArrayList<>();
	    }
	    if (!recentProducts.contains(id)) {
	    	if (recentProducts.size() >= MAX_RECENTLY_PRODUCTS) {
	    		recentProducts.remove(0);
	        }
	    	recentProducts.add(id);
        }
	    session.setAttribute("recentProducts", recentProducts);
	
		return "product/detail";
	}
	
	// 할인중인 제품 페이지 불러오기
	@GetMapping("/product/sale/{pageNum}")
	public String saleProduct(Model model, @PathVariable(name = "pageNum") int pageNum, Principal p) {
		
		Page<Product> page = proService.listAllBySale(pageNum);
		List<Product> productsBySale = page.getContent();
		
		long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
	    long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
	    if (endCount > page.getTotalElements()) {
	    	endCount = page.getTotalElements();
	    }
		
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("startCount", startCount);
	    model.addAttribute("endCount", endCount);
	    model.addAttribute("totalItems", page.getTotalElements());
	    
	    model.addAttribute("listproduct", productsBySale);
	    
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
	    return "product/sale";
	}
	
	// 최근 제품 페이지 불러오기
	@GetMapping("/product/recent/{pageNum}")
	public String newProduct(Model model, @PathVariable(name = "pageNum") int pageNum, Principal p) {
		
		Page<Product> page = proService.listAllByDate(pageNum);
		List<Product> productsByDate = page.getContent();
		
		long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
	    long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
	    if (endCount > page.getTotalElements()) {
	    	endCount = page.getTotalElements();
	    }
		
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("startCount", startCount);
	    model.addAttribute("endCount", endCount);
	    model.addAttribute("totalItems", page.getTotalElements());
	    
	    model.addAttribute("listproduct", productsByDate);
	    
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
	    return "product/recent";
	}
	
	// 제품 랭킹 페이지 불러오기
	@GetMapping("/product/rank/{pageNum}")
	public String rankProduct(Model model, @PathVariable(name = "pageNum") int pageNum, Principal p) {
		
		Page<Object[]> page = proService.findAllByOrderByRank(pageNum);
		List<Product> productsByRank = new ArrayList<>();
		for (Object[] objects : page.getContent()) {
		    Product product = (Product) objects[0];
		    productsByRank.add(product);
		}
		long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
	    long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
	    if (endCount > page.getTotalElements()) {
	    	endCount = page.getTotalElements();
	    }
		
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("startCount", startCount);
	    model.addAttribute("endCount", endCount);
	    model.addAttribute("totalItems", page.getTotalElements());
		
	    model.addAttribute("listproduct", productsByRank);
	    
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
	    return "product/rank";
	}
	
	// 제품 브랜드 불러오기
	@GetMapping("/product/brand")
	public String listBrand(Product product, Model model, @Param("keyword") String keyword) {
	    List<Product> list = proService.listBrand(keyword);
	    
	    Set<String> uniqueBrands = new HashSet<>(); // Set을 이용해 브랜드 중복 제거
	    List<Product> listbrand = new ArrayList<>();
	    
	    for (Product p : list) {
	        if (uniqueBrands.add(p.getBrand())) {
	        	listbrand.add(p);
	        }
	    }
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("listbrand", listbrand);
	    
	    return "product/brand";
	}
	
	// 해당 브랜드 제품 페이지 불러오기
	@GetMapping("/product/brand/{brand}/{pageNum}")
	public String brandProduct(Model model, @PathVariable("brand") String brand,
			@PathVariable(name = "pageNum") int pageNum, Principal p) {

		Page<Product> page = proService.listByBrand(brand, pageNum);
		List<Product> listbrand = page.getContent();
		
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listbrand", listbrand);
		
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
	    return "product/brand_list";
	}
	
}
