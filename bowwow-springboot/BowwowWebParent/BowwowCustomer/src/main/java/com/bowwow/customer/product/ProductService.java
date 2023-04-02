package com.bowwow.customer.product;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Category;
import com.bowwow.common.entity.Product;

@Service
@Transactional
public class ProductService {
	
	public static final int PRODUCTS_PER_PAGE = 9;

	@Autowired
	private ProductRepository proRepo;
	
	public List<Product> listAll() {
		return (List<Product>) proRepo.findAll();
	}

	public Page<Product> listByPage(int pageNum, String keyword) {
	    Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
	    if (keyword != null && !keyword.trim().isEmpty()) {
	        return proRepo.findAll(keyword.trim(), pageable);
	    }
	    return proRepo.findAll(pageable);
	}

	public Product findById(int id) {
		return proRepo.findById(id).get();
	}
	
	public Page<Product> listAllBySale(int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		return proRepo.findAllByProductBySale(pageable);
	}
	
	public Page<Product> listAllByDate(int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		return proRepo.findAllByOrderByRegDateDesc(pageable);
    }

	public Page<Object[]> findAllByOrderByRank(int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		return proRepo.findAllByOrderByRank(pageable);
	}

	public Page<Product> listByBrand(String brand, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		return proRepo.findProductByBrand(brand, pageable);
	}

	public List<Product> listBrand(String keyword) {
		if (keyword != null && !keyword.trim().isEmpty()) {
	        return proRepo.findBrand(keyword.trim());
	    }
	    return (List<Product>) proRepo.findAll();
	}

	public Page<Product> listByCategory(Category category, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		return proRepo.findProductByCategory(category, pageable);
	}

	public Page<Product> listByChildCategories(List<Category> categoryIds, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		return proRepo.findByCategoryIdIn(categoryIds, pageable);
	}

	public void save(Product pro) {
		proRepo.save(pro);
		
	}

	//메인페이지용 핫딜 Top3 보여주기
	public List<Product> findTop3ByOrderByDiscountDesc() {
		return proRepo.findTop3ByOrderByDiscountDesc();
	}
	
	//메인페이지용 신상품 Top3 보여주기
	public List<Product> list3ByDate() {
		return proRepo.findTop3ByOrderByRegDateDesc();
	}
	
	//메인페이지용 베스트 Top3 보여주기
	public List<Product> findTop3ByOrderByRank() {
		return proRepo.findTop3ByOrderByRank();
	}

	public List<Product> findByMainName(String mainName) {
      return proRepo.findByMainName(mainName);
   }
}
