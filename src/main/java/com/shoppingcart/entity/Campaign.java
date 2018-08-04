package com.shoppingcart.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
    
    public Campaign(Category category,BigDecimal valueOfDiscount,DiscountType discountType,Integer lowerLimitOfApplyDiscount){
    	this.setLuc(0l);
    	this.category=category;
    	this.valueOfDiscount=valueOfDiscount;
    	this.discountType=discountType;
    	this.lowerLimitOfApplyDiscount=lowerLimitOfApplyDiscount;
    }
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER,cascade= {CascadeType.MERGE,CascadeType.PERSIST})
	private Category category;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal valueOfDiscount;
	
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

	public BigDecimal getValueOfDiscount() {
		return valueOfDiscount;
	}

	public void setValueOfDiscount(BigDecimal valueOfDiscount) {
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
