package com.shoppingcart.service;

import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Category;

@Service
public interface ICategoryService {
	
	public Category findByTitle(String title);

	public Category addCategory(Category category);

}
