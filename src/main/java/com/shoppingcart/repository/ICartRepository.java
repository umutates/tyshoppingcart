package com.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingcart.entity.ShoppingCart;

/**
 * @author umutates
 * created on 2018-08-04
 */
@Transactional
@Repository
public interface ICartRepository extends JpaRepository<ShoppingCart,Long>{

}
