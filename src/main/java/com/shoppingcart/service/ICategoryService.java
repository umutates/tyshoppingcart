package com.shoppingcart.service;

import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Category;

@Service
public interface ICategoryService {
	
	Category findByTitle(String title);

}
