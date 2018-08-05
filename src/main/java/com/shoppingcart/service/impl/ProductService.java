package com.shoppingcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Product;
import com.shoppingcart.repository.IProductRepository;
import com.shoppingcart.service.IProductService;
import com.shoppingcart.util.GenericException;

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
	   Product product=productRepository.findById(id).orElseThrow(() -> new GenericException(this.getClass(), "this object not found!"));
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
	public void deleteProduct(Long id) {
		Product product=findById(id);
		productRepository.delete(product);
	}
    
	@Override
	public List<Product> findAllProduct() {
         return productRepository.findAll();		
	}

}
