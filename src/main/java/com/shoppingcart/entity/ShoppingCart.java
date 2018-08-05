package com.shoppingcart.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author umutates
 * created on 2018-08-04
 */

@Entity
@Table(name="ShoppingCart")
public class ShoppingCart extends BaseEntity {
	
	private static final long serialVersionUID = -8684492314198270936L;
	
	@NotEmpty
	@ManyToMany(fetch=FetchType.EAGER,cascade= CascadeType.MERGE)
	private List<Product> products;
	
	public ShoppingCart() {
		products=new ArrayList<>();
		setProducts(products);
	 }
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
