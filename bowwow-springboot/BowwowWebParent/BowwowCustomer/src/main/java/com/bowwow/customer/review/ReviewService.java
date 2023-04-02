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
	
	public List<Review> listAll() {
		return (List<Review>) reviewRepo.findAll();
	}
	

	public void save(Review review) {
		reviewRepo.save(review);
	}

	public void deleteById(Integer id) {
		reviewRepo.deleteById(id);
	}
	
	public Page<Review> findByProductId(int id,int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1,REVIEWS_PER_PAGE);
		return reviewRepo.findByProductId(id,pageable);
	}
		
	public Product save(Product reviewScore) {
		return proRepo.save(reviewScore);
	}
	
	public Review findById(Integer id) {
		return reviewRepo.findById(id).get();
	}
	
	public float getProductIdAvg(Integer id) {
		return reviewRepo.getProductIdAvg(id);
		
	}
	
	public Page<Review> listByPage(int pageNum){
		Pageable pageable = PageRequest.of(pageNum - 1,REVIEWS_PER_PAGE);
		return reviewRepo.findAll(pageable);
	}
	
	public Page<Review> listByPage(int pageNum,String sortField, String sortDir){
		Sort sort =Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum -1, REVIEWS_PER_PAGE, sort);

			return reviewRepo.findAll(pageable);
	}

	public Integer getfindById(int id) {
		return reviewRepo.getCountReviewfindyById(id);
	}
}





