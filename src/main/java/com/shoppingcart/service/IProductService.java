package com.shoppingcart.service;

import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Product;

@Service
public interface IProductService {
	
	public Product findById(Long id);
	
	public Product addProduct(Product product);
	
	public void updateProduct(Product product);
	
	public void removeProduct(Long id);
	
	

}
