package com.shoppingcart.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author umutates
 * created on 2018-08-04
 */
@Entity
@Table(name="PRODUCT")
public class Product extends BaseEntity {
   
	static final long serialVersionUID = 829294521717037011L;
	
    public Product() {
      super();
	}
    
    public Product(String title,double price,Category category) {
    	this.setLuc(0L);
    	this.title=title;
    	this.category=category;
    	this.price=price;
    }
    
    @NotBlank
    @NotNull
    private String title;

	@Positive 
    @NotNull 
    private double price;
	
    @Transient
    private double discountedPrice;
    
    @NotNull
	@ManyToOne(fetch=FetchType.EAGER,cascade= CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
