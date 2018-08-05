package com.shoppingcart.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.shoppingcart.util.DiscountType;

/**
 * @author umutates
 * created on 2018-08-04
 */
@Entity
@Table(name="CAMPAIGN")
public class Campaign extends BaseEntity {
    	
	private static final long serialVersionUID = -2813010097903121378L;
	
    public Campaign() {
		super();
	}
    
    public Campaign(Category category,double valueOfDiscount,DiscountType discountType,Integer lowerLimitOfApplyDiscount){
    	this.setLuc(0l);
    	this.category=category;
    	this.valueOfDiscount=valueOfDiscount;
    	this.discountType=discountType;
    	this.lowerLimitOfApplyDiscount=lowerLimitOfApplyDiscount;
    }
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER,cascade= CascadeType.MERGE)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@NotNull
	@PositiveOrZero
	private double valueOfDiscount;
	
	@NotNull
	@PositiveOrZero
	private Integer  lowerLimitOfApplyDiscount;
	
	@NotNull
	@Enumerated
	private DiscountType discountType;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getValueOfDiscount() {
		return valueOfDiscount;
	}

	public void setValueOfDiscount(double valueOfDiscount) {
		this.valueOfDiscount = valueOfDiscount;
	}

	public Integer getLowerLimitOfApplyDiscount() {
		return lowerLimitOfApplyDiscount;
	}

	public void setLowerLimitOfApplyDiscount(Integer lowerLimitOfApplyDiscount) {
		this.lowerLimitOfApplyDiscount = lowerLimitOfApplyDiscount;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}
	

}
