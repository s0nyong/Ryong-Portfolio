package com.bowwow.customer.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bowwow.common.entity.Review;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Integer> {

	public Page<Review> findByProductId(int id, Pageable pageable);

	@Query("SELECT avg(score)FROM Review r WHERE r.product.id = :id")
	public float getProductIdAvg(@Param("id")Integer id);

	@Query("SELECT r FROM Review r WHERE CONCAT(r.id,' ', ' ', r.comment, ' ') LIKE %?1%")
	public Page<Review> findAll(String keyword , Pageable pageable);
	
	@Query("SELECT COUNT(*)FROM Review r WHERE r.product.id = :id")
	public Integer getCountReviewfindyById(@Param("id") int id);

}
