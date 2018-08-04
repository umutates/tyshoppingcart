package com.shoppingcart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Category;
import com.shoppingcart.repository.ICategoryRepository;
import com.shoppingcart.service.ICategoryService;

/**
 * @author umutates
 * created on 2018-08-04
 */
@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	ICategoryRepository categoryRepository;

	@Override
	public Category findByTitle(String title) {
		return categoryRepository.findByTitle(title);
	}
	
	

}
