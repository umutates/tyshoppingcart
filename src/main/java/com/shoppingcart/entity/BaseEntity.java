package com.shoppingcart.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * @author umutates
 * created on 2018-08-04
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 2054795705031006356L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Version
	private Long luc;
	
	public Long getLuc() {
		return luc;
	}

	public void setLuc(Long luc) {
		this.luc = luc;
	}

}
