package com.bowwow.admin.category;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bowwow.common.entity.Category;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository cateRepo;
	
//	@Test 
//	public void testCreateCategory() {
//		Category category = new Category("간식");
//		Category savedCategory = cateRepo.save(category);
//		
//		assertThat(savedCategory.getId()).isGreaterThan(0);
//	}
//	
//	@Test
//	public void testCreateSubCategory() {
//		
//		Category parent = new Category(1);
//		Category subCategory = new Category("건식사료", parent);
//		Category savedCategory = cateRepo.save(subCategory);
//		
//		assertThat(savedCategory.getId()).isGreaterThan(0);
//	}
//	
//	@Test
//	public void testGetCategory() {
//		Category cate = cateRepo.findById(1).get();
//		Set<Category> children = cate.getChildren();
//		System.out.println(cate.getCategoryName());
//		
//		for (Category subCategory : children) {
//			System.out.println("---" + subCategory.getCategoryName());
//		}
//		assertThat(children.size()).isGreaterThan(0);
//	}
	
}
