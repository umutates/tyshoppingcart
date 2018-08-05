package com.shoppingcart.entity;

import com.shoppingcart.util.DiscountType;

/**
 * @author umutates
 * created on 2018-08-05
 */
public class Coupon extends BaseEntity  {
	
    static final long serialVersionUID = -5674878865928767202L;

	private double valueOfDiscount;
	
	private double minCartAmount;
	
	private DiscountType discountType;

	public double getValueOfDiscount() {
		return valueOfDiscount;
	}

	public void setValueOfDiscount(double valueOfDiscount) {
		this.valueOfDiscount = valueOfDiscount;
	}

	public double getMinCartAmount() {
		return minCartAmount;
	}

	public void setMinCartAmount(double minCartAmount) {
		this.minCartAmount = minCartAmount;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}
	
	

}
