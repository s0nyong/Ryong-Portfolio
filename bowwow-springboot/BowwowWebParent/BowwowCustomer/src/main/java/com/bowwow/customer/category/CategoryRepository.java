package com.bowwow.customer.category;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bowwow.common.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	// 부모 카테고리가 null인 카테고리 목록 조회
	public List<Category> findCategoriesByParentIsNull();

}
