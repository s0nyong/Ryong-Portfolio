package com.bowwow.admin.product;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Product;

@Service
@Transactional
public class ProductService {
	
	public static final int PRODUCTS_PER_PAGE = 10;

	@Autowired
	private ProductRepository proRepo;
	
	public List<Product> listAll() {
		return (List<Product>) proRepo.findAll();
	}

	public Page<Product> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE, sort);
		if (keyword != null) {
			return proRepo.findAll(keyword, pageable);
		}
		return proRepo.findAll(pageable);
	}

	public Product save(Product product) {
		return proRepo.save(product);
	}
	
	public Product findById(int id) throws ProductNotFoundException {
		try {
			return proRepo.findById(id).get();
		}catch(NoSuchElementException e) {
			throw new ProductNotFoundException("Could not find any user with ID "+id);
		}	
	}

	public void deleteById(int id) throws ProductNotFoundException {
		Long countById = proRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new ProductNotFoundException("Could not find any user with ID " + id);
		}
		proRepo.deleteById(id);
	}
	
	public boolean isSubNameUnique(Integer id, String name) {
		Product productByName = proRepo.findBySubName(name);
		if(productByName == null)return true;
		
		boolean isCreateingNew = (id == null);
		
		if(isCreateingNew) {
			if(productByName != null)return false;
		}else {
			if(productByName.getId() != id) {
				return false;
			}
		}
		return true;
	}
}
