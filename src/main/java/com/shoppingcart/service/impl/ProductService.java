package com.shoppingcart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Product;
import com.shoppingcart.repository.IProductRepository;
import com.shoppingcart.service.IProductService;

/**
 * @author umutates
 * created on 2018-08-04
 */
@Service
public class ProductService implements IProductService{
	
	@Autowired
	IProductRepository productRepository;
	
	@Override
	public Product findById(Long id) {
	   Product product=productRepository.findById(id).get();
	   return product;
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProduct(Long id) {
		// TODO Auto-generated method stub
		
	}

}
