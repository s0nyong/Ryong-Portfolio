package com.bowwow.customer.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.Category;
import com.bowwow.common.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

	@Query("SELECT p FROM Product p LEFT JOIN Category c ON p.categoryId = c.id WHERE CONCAT(IFNULL(p.mainName,''), ' ', IFNULL(p.subName,''), ' ', IFNULL(p.brand,''), ' ', IFNULL(c.categoryName,''), ' ') LIKE %:keyword% OR REPLACE(CONCAT(IFNULL(p.mainName,''), ' ', IFNULL(p.subName,''), ' ', IFNULL(p.brand,''), ' ', IFNULL(c.categoryName,'')), ' ', '') LIKE %:keyword%")
	public Page<Product> findAll(String keyword, Pageable pageable);
	
	public Page<Product> findAllByOrderByRegDateDesc(Pageable pageable);
	
	//메인페이지용 신상품 Top3 보여주기
	public List<Product> findTop3ByOrderByRegDateDesc();

	@Query("SELECT p FROM Product p WHERE p.discount >= 0.3")
	public Page<Product> findAllByProductBySale(Pageable pageable);
	
	//메인페이지용 할인률 높은 Top3 보여주기
	@Query("SELECT p FROM Product p ORDER BY p.discount DESC")
    List<Product> findTop3ByOrderByDiscountDesc();
	
	@Query("SELECT p, SUM(od.count) AS total FROM Product p LEFT JOIN OrderDetail od on p.id = od.product GROUP BY p.id ORDER BY total DESC")
	public Page<Object[]> findAllByOrderByRank(Pageable pageable);

	//메인페이지용 베스트 Top3 보여주기
	@Query("SELECT p, SUM(od.count) AS total FROM Product p LEFT JOIN OrderDetail od on p.id = od.product GROUP BY p.id ORDER BY total DESC")
	public List<Product> findTop3ByOrderByRank();
	
	@Query("SELECT p FROM Product p WHERE p.brand = ?1")
	public Page<Product> findProductByBrand(String brand, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE CONCAT(IFNULL(p.brand,''), ' ') LIKE %:keyword% OR REPLACE(CONCAT(IFNULL(p.brand,'')), ' ', '') LIKE %:keyword%")
	public List<Product> findBrand(String keyword);

	@Query("SELECT p FROM Product p WHERE p.categoryId = ?1")
	public Page<Product> findProductByCategory(Category category, Pageable pageable);

	public Page<Product> findByCategoryIdIn(List<Category> categoryIds, Pageable pageable);

	public List<Product> findByMainName(String mainName);


	
}
 