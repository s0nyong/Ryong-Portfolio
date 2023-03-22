package com.bowwow.customer.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Category;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // 모든 카테고리 조회
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    // 해당 카테고리 조회
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null); // 조회 결과가 있을 경우 해당 객체 반환 , 없을경우 null 반환
    }
    
    // 부모 카테고리가 null인 카테고리 목록 조회
    public List<Category> getParentCategories() {
        return categoryRepository.findCategoriesByParentIsNull();
    }

    // 해당 카테고리의 자식 카테고리 목록 조회
    public List<Category> getChildrenByParentCategory(Integer categoryId) {
        Optional<Category> optionalParentCategory = categoryRepository.findById(categoryId);
        if (optionalParentCategory.isPresent()) { // Optional 객체로 isPresent() 메서드를 사용해 값이 존재하는지 확인
            Category parentCategory = optionalParentCategory.get();
            return new ArrayList<>(parentCategory.getChildren()); // 자식 카테고리 객체들을 ArrayList로 복사하고 반환
        }
        return Collections.emptyList(); // 객체가 없을경우 빈 목록 반환
    }
}
