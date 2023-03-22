package com.bowwow.admin.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Category;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryRepository caRepo;
	
	public List<Category> listProductUsedInForm(){
		List<Category> categoriesUserInForm = new ArrayList<>();
		
        Iterable<Category> categoriesInDB = caRepo.findAll();
		
		for(Category category : categoriesInDB) {	
			if(category.getParent() == null) {		
				categoriesUserInForm.add(category);			
				
				Set<Category> children = category.getChildren();
				
				for(Category subCategory : children) {
					String name = subCategory.getCategoryName();
					categoriesUserInForm.add(Category.copyIdAndName(subCategory.getId(),name));
					
					
				}				
			}
		}
		return categoriesUserInForm;
	}

}
