package com.shoppingcart.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author umutates
 * created on 2018-08-04
 */
@Entity
@Table(name="CATEGORY")
public class Category extends BaseEntity implements  Serializable {
	
	private static final long serialVersionUID = 1699794911783867650L;
	
	public Category() {
		super();
	}
    
	public Category(String title) {
		this.setLuc(0L);
		this.title=title;
	}
	
	@NotNull
	@NotBlank
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
