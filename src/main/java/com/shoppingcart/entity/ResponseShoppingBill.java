package com.shoppingcart.entity;

public class ResponseShoppingBill {
	
	private double totalAmount=0;
	
	private double totalAmountAfterDiscounts=0;
	
	private double campaignDiscount=0;
	
	private double couponDiscount=0;
	
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

	private double deliveryAmount;

}
