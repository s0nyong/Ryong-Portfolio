package com.bowwow.admin.category;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {


}
