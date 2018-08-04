package com.shoppingcart.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

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
	@ManyToMany(fetch=FetchType.EAGER,cascade= {CascadeType.MERGE,CascadeType.PERSIST})
	private Set<Product> products;
	 
	 
	public ShoppingCart() {
		products=new HashSet<>(); 
		setItems(products);
	 }
	
	public void addItem(Product product,Integer count) {
		IntStream.range(0,count).mapToObj(i ->product).forEach(products::add);
	}
	
	public void setItems(Set<Product> items) {
		this.products = items;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
