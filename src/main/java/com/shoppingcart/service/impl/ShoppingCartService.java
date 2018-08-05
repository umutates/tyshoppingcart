package com.shoppingcart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.entity.ShoppingCart;
import com.shoppingcart.repository.IShoppingCartRepository;
import com.shoppingcart.service.IShoppingCartService;

@Service
public class ShoppingCartService implements IShoppingCartService {
	
	@Autowired
	IShoppingCartRepository shoppingCartRepository;

	@Override
	public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
		return shoppingCartRepository.save(shoppingCart);
	}

	@Override
	public ShoppingCart findById(Long id) {
		return shoppingCartRepository.findById(id).get();
	}

}
