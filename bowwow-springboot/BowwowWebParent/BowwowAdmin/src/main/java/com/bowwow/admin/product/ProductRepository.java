package com.bowwow.admin.product;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bowwow.common.entity.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

	public Long countById(Integer id);

	public Product findBySubName(String name);
	
	@Query("SELECT p FROM Product p INNER JOIN Category c ON p.categoryId = c.id WHERE CONCAT(p.mainName, ' ', p.subName, ' ', p.brand, ' ', c.categoryName) LIKE %?1%")
	public Page<Product> findAll(String keyword, Pageable pageable);
}
