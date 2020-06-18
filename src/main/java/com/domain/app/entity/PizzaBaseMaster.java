package com.domain.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the pizza_base_master database table.
 * 
 */
@Entity
@Table(name="pizza_base_master")
@Getter
@Setter
public class PizzaBaseMaster extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -3761142566885546521L;

	@Column(name="base_size")
	private String baseSize;

	@Column(name="crust_type")
	private String crustType;

	@Column(name="price")
	private BigDecimal price;

	public PizzaBaseMaster() {
	}
}