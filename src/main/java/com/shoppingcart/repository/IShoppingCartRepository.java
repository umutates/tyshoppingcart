package com.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.entity.ShoppingCart;

/**
 * @author umutates
 * created on 2018-08-04
 */
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{

}
