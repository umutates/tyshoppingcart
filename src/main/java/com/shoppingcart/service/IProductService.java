package com.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Product;

@Service
public interface IProductService {
	
	public Product findById(Long id);
	
	public Product addProduct(Product product);
	
	public void updateProduct(Product product);
	
	public List<Product> findAllProduct();

	public void deleteProduct(Long id);
	

}
