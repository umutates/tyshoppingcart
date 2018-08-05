package com.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingcart.entity.Coupon;

/**
 * @author umutates
 * created on 2018-08-05
 */
@Transactional
@Repository
public interface ICouponRepository extends JpaRepository<Coupon,Long> {

}
