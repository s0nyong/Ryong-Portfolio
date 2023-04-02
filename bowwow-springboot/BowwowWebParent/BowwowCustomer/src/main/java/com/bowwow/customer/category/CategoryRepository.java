package com.bowwow.customer.category;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bowwow.common.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	// Spring Data JPA에서 자동으로 생성되는 쿼리 메서드
	public List<Category> findCategoriesByParentIsNull();

}
