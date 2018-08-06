package com.shoppingcart.entity;


import java.util.List;
import java.util.Map;

/**
 * @author umutates
 * created on 2018-08-04
 */
public class ResponseShoppingBill {
	
	private double totalAmount=0;
	
	private double totalAmountAfterDiscounts=0;
	
	private double campaignDiscount=0;
	
	private double couponDiscount=0;
	
	private double deliveryAmount;
	
	private Map<Long,List<Product>> productsByCategory;
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTotalAmountAfterDiscounts() {
		return totalAmountAfterDiscounts;
	}

	public void setTotalAmountAfterDiscounts(double totalAmountAfterDiscounts) {
		this.totalAmountAfterDiscounts = totalAmountAfterDiscounts;
	}

	public double getCampaignDiscount() {
		return campaignDiscount;
	}

	public void setCampaignDiscount(double campaignDiscount) {
		this.campaignDiscount = campaignDiscount;
	}

	public double getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(double couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public double getDeliveryAmount() {
		return deliveryAmount;
	}

	public void setDeliveryAmount(double deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}

	public Map<Long,List<Product>> getProductByCategory() {
		return productsByCategory;
	}

	public void setProductByCategory(Map<Long,List<Product>> productByCategory) {
		this.productsByCategory = productByCategory;
	}

	

}
