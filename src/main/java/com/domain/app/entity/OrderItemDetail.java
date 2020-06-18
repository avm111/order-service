package com.domain.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the order_item_details database table.
 * 
 */
@Entity
@Getter
@Setter
@Table(name="order_item_details")
public class OrderItemDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name = "order_id",nullable=true)
	private OrderDetail orderDetail;
	
	@OneToOne
	@JoinColumn(name="pizza_base_id", nullable=true)
	private PizzaBaseMaster pizzaBaseMaster;

	@Column(name="toppings_array")
	private String toppingsArray;
	
	private Integer quantity;

	private BigDecimal price;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_on")
	private Date createdOn;

	public OrderItemDetail() {
	}
}