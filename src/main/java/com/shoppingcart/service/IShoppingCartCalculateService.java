package com.shoppingcart.service;

import com.shoppingcart.entity.ShoppingCart;
import com.shoppingcart.util.ResponseShopping;

/**
 * @author umutates
 * created on 2018-08-05
 */
public interface IShoppingCartCalculateService {
	
	public ResponseShopping calculate(ShoppingCart shoppingCart);

}
