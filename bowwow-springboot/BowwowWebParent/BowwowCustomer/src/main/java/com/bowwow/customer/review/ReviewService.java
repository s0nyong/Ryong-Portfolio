package com.bowwow.customer.review;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.Review;
import com.bowwow.customer.product.ProductRepository;

@Service
@Transactional
public class ReviewService {
	
	public static final int REVIEWS_PER_PAGE = 4;
	public static final int PRODUCTS_PER_PAGE = 10;

	@Autowired
	private ProductRepository proRepo;
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	// 모든 리뷰 리스트 불러오기
	public List<Review> listAll() {
		return (List<Review>) reviewRepo.findAll();
	}
	
	// 리뷰 저장
	public void save(Review review) {
		reviewRepo.save(review);
	}

	// 리뷰 삭제
	public void deleteById(Integer id) {
		reviewRepo.deleteById(id);
	}
	
	// 해당 제품의 리뷰 불러오기
	public Page<Review> findByProductId(int id,int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1,REVIEWS_PER_PAGE);
		return reviewRepo.findByProductId(id,pageable);
	}
	
	// 해당 제품의 별점 저장
	public Product save(Product reviewScore) {
		return proRepo.save(reviewScore);
	}
	
	// 해당 리뷰 불러오기
	public Review findById(Integer id) {
		return reviewRepo.findById(id).get();
	}
	
	// 해당 제품의 평균 별점 불러오기
	public float getProductIdAvg(Integer id) {
		return reviewRepo.getProductIdAvg(id);
	}
	
	// 모든 리뷰 페이지 불러오기
	public Page<Review> listByPage(int pageNum){
		Pageable pageable = PageRequest.of(pageNum - 1,REVIEWS_PER_PAGE);
		return reviewRepo.findAll(pageable);
	}
	
	// 모든 리뷰 최신순 불러오기
	public Page<Review> listByPage(int pageNum,String sortField, String sortDir){
		Sort sort =Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum -1, REVIEWS_PER_PAGE, sort);

		return reviewRepo.findAll(pageable);
	}

	// 해당 제품의 리뷰 갯수 불러오기
	public Integer getfindById(int id) {
		return reviewRepo.getCountReviewfindyById(id);
	}
}





