package com.shoppingcart.service;

import org.springframework.stereotype.Service;

import com.shoppingcart.entity.ShoppingCart;

/**
 * @author umutates
 * created on 2018-08-04
 */
@Service
public interface IShoppingCartService {
	
	public ShoppingCart createShoppingCart(ShoppingCart shoppingCart);

	public ShoppingCart findById(Long id);

}
