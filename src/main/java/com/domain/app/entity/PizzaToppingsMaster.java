package com.domain.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the pizza_toppings_master database table.
 * 
 */
@Entity
@Table(name="pizza_toppings_master")
@Getter
@Setter
public class PizzaToppingsMaster extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8738379993449127147L;

	@Column(name="topping_name")
	private String toppingName;

	@Column(name="topping_price")
	private BigDecimal toppingPrice;

	@Column(name="topping_type")
	private String toppingType;
	
	public PizzaToppingsMaster() {
	}
}