package com.shoppingcart.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    
    public Product(String title,BigDecimal price,Category category) {
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
    private BigDecimal price;
	
    @Positive
    private BigDecimal reducedPrice;
    
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getReducedPrice() {
		return reducedPrice;
	}

	public void setReducedPrice(BigDecimal reducedPrice) {
		this.reducedPrice = reducedPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
