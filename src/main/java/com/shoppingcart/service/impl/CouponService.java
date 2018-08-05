package com.shoppingcart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Coupon;
import com.shoppingcart.repository.ICouponRepository;
import com.shoppingcart.service.ICouponService;

/**
 * @author umutates
 * created on 2018-08-05
 */
@Service
public class CouponService implements ICouponService {
	
	@Autowired
	ICouponRepository couponRepository;
	
	@Override
	public Coupon findById(Long id) {
		return couponRepository.findById(id).get();
	}

}
