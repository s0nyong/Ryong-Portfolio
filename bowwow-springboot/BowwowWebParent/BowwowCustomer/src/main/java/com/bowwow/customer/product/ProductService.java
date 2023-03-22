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
	
	// 모든 제품 불러오기
	public List<Product> listAll() {
		return (List<Product>) proRepo.findAll();
	}

	// 모든 제품 페이지 불러오기
	public Page<Product> listByPage(int pageNum, String keyword) {
	    Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
	    if (keyword != null && !keyword.trim().isEmpty()) {
	        return proRepo.findAll(keyword.trim(), pageable);
	    }
	    return proRepo.findAll(pageable);
	}

	// 해당 제품 불러오기
	public Product findById(int id) {
		return proRepo.findById(id).get();
	}
	
	// 할인 제품 불러오기
	public Page<Product> listAllBySale(int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		return proRepo.findAllByProductBySale(pageable);
	}
	
	// 최신 제품 불러오기
	public Page<Product> listAllByDate(int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		return proRepo.findAllByOrderByRegDateDesc(pageable);
    }

	// 랭킹 제품 불러오기
	public Page<Object[]> findAllByOrderByRank(int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		return proRepo.findAllByOrderByRank(pageable);
	}

	// 브랜드별 제품 불러오기
	public Page<Product> listByBrand(String brand, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		return proRepo.findProductByBrand(brand, pageable);
	}

	// 모든 브랜드 불러오기
	public List<Product> listBrand(String keyword) {
		if (keyword != null && !keyword.trim().isEmpty()) {
	        return proRepo.findBrand(keyword.trim());
	    }
	    return (List<Product>) proRepo.findAll();
	}

	// 카테고리별 제품 불러오기
	public Page<Product> listByCategory(Category category, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		return proRepo.findProductByCategory(category, pageable);
	}

	// 자식 카테고리별 제품 불러오기
	public Page<Product> listByChildCategories(List<Category> categoryIds, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		return proRepo.findByCategoryIdIn(categoryIds, pageable);
	}

	// 제품 저장
	public void save(Product pro) {
		proRepo.save(pro);
	}

	// 메인페이지용 핫딜 Top3 불러오기
	public List<Product> findTop3ByOrderByDiscountDesc() {
		return proRepo.findTop3ByOrderByDiscountDesc();
	}
	
	// 메인페이지용 신상품 Top3 불러오기
	public List<Product> list3ByDate() {
		return proRepo.findTop3ByOrderByRegDateDesc();
	}
	
	// 메인페이지용 베스트 Top3 불러오기
	public List<Product> findTop3ByOrderByRank() {
		return proRepo.findTop3ByOrderByRank();
	}

	// 제품 메인이름 불러오기
	public List<Product> findByMainName(String mainName) {
      return proRepo.findByMainName(mainName);
   }
}
