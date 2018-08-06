package com.shoppingcart.service;

import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Coupon;

/**
 * @author umutates
 * created on 2018-08-05
 */
@Service
public interface ICouponService {

	public Coupon findById(Long id);

}
