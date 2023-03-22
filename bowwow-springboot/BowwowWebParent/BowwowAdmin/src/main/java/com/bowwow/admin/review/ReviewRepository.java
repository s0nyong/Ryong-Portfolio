package com.bowwow.admin.review;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.Review;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Integer> {

}
