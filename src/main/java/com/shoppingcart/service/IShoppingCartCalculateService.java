package com.shoppingcart.service;

import org.springframework.stereotype.Service;

import com.shoppingcart.entity.ResponseShoppingBill;
import com.shoppingcart.entity.ShoppingCart;

/**
 * @author umutates
 * created on 2018-08-05
 */
@Service
public interface IShoppingCartCalculateService {
	
	public ResponseShoppingBill calculateCartWithinDiscount(ShoppingCart shoppingCart);

}
