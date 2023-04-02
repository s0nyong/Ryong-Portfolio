package com.bowwow.admin.product;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bowwow.admin.FileUploadUtil;
import com.bowwow.admin.category.CategoryService;
import com.bowwow.common.entity.Category;
import com.bowwow.common.entity.Product;

@Controller
public class ProductController {

	@Autowired
	private ProductService proService;
	
	@Autowired
	private CategoryService caService;
	
	@GetMapping("/product")
	public String listProduct(Model model) {
		return listByPage(model, 1, "mainName", "asc", null);
	}
	
	@GetMapping("/product/page/{pageNum}")
	public String listByPage(Model model, @PathVariable(name = "pageNum") int pageNum,
			@Param("sortField") String sortField, 
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword) {
		Page<Product> page = proService.listByPage(pageNum, sortField, sortDir, keyword);
		List<Product> listproduct = page.getContent();
		
		long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
	    long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
	    if (endCount > page.getTotalElements()) {
	    	endCount = page.getTotalElements();
	    }
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("currentPage", pageNum);	//현재페이지
	    model.addAttribute("totalPages", page.getTotalPages());	// 전체페이지 수
	    model.addAttribute("startCount", startCount); 
	    model.addAttribute("endCount", endCount);
	    model.addAttribute("totalItems", page.getTotalElements());
	    
	    model.addAttribute("sortField", sortField);	//정렬 기준
	    model.addAttribute("sortDir", sortDir); // 정렬방향
	    model.addAttribute("reverseSortDir", reverseSortDir); //정렬 방향 변경
	    
	    model.addAttribute("keyword", keyword);	//검색어
	    
	    model.addAttribute("listproduct", listproduct);	//제품목록
		
		return "product/product";
	}
	
	@GetMapping("/product/new")
	public String addProduct(Model model) {
		List<Category> listCategory = caService.listProductUsedInForm();
		Product listProduct = new Product();
		listProduct.setEnabled(true);
		
		model.addAttribute("listCategory",listCategory);	//카테고리목록
		model.addAttribute("product", listProduct);	//제품객체
		
		return "product/product_new";
	}
	
	@PostMapping("/product/save")
	public String saveProduct(@ModelAttribute("product") Product pro,
	                          @RequestParam("mainimage") MultipartFile mainImageFile,
	                          @RequestParam("descimage") MultipartFile descImageFile,
	                          RedirectAttributes redirectAttributes) throws IOException {
	    
	    if (!mainImageFile.isEmpty()) {	//mainImageFile이 있을 때 
	        // main image 업로드
	        String mainImageFileName = StringUtils.cleanPath(mainImageFile.getOriginalFilename());
	        pro.setMainImage(mainImageFileName);
	        
	        Product savedProduct = proService.save(pro);
	        String uploadDir = "../product-main-images/" + savedProduct.getId();
	        
	        FileUploadUtil.cleanDir(uploadDir);
	        FileUploadUtil.saveFile(uploadDir, mainImageFileName, mainImageFile);
	    } else { //mainImageFile이 없을 때 
	        if (pro.getMainImage().isEmpty()) {
	            pro.setMainImage(null);
	        }
	        proService.save(pro);
	    }
	    
	    if (!descImageFile.isEmpty()) {	//descImageFile이 있을 때 
	        // desc image 업로드
	        String descImageFileName = StringUtils.cleanPath(descImageFile.getOriginalFilename());
	        pro.setDescImage(descImageFileName);
	        
	        Product savedProduct = proService.save(pro);
	        String uploadDir = "../product-desc-images/" + savedProduct.getId();
	        
	        FileUploadUtil.cleanDir(uploadDir);
	        FileUploadUtil.saveFile(uploadDir, descImageFileName, descImageFile);
	    } else {	//descImageFile이 없을 때 
	        if (pro.getDescImage().isEmpty()) {
	            pro.setDescImage(null);
	        }
	        proService.save(pro);
	    }
	    
	    redirectAttributes.addFlashAttribute("message", "상품이 저장되었습니다.");
	    return "redirect:/product";
	}
	
	@RequestMapping(value="/product/edit/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editproduct(@PathVariable("id")int id, Model model, RedirectAttributes redirectAttributes) {
		Product product;
		try {
			product = proService.findById(id);
			model.addAttribute("product",product);	//해당 제품 가져오기
			model.addAttribute("pageTitle","Edit product(ID: "+id+")");
			
			List<Category> listCategory = caService.listProductUsedInForm();
			model.addAttribute("listCategory",listCategory);	//카테고리 리스트 뽑아오기
			return "product/product_new";
		}catch(ProductNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/product";
		}
	}
	
	@RequestMapping(value="/product/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteProduct(@PathVariable("id")int id,RedirectAttributes redirectAttributes) {
		try{
			proService.deleteById(id);
			redirectAttributes.addFlashAttribute("message", "The user ID "+id+"has been deleted successfully");
		}catch(ProductNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
		}		
		return "redirect:/product";	
	}
}
