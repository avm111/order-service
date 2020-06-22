package com.domain.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@Getter
@Setter
@Table(name="order_details")
public class OrderDetail extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -8502825486785718523L;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name = "customer_id",nullable=true)
	private Customer customer;

	@Column(name="delivery_type")
	private String deliveryType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_delivery_date")
	private Date orderDeliveryDate;

	@Column(name="order_price")
	private BigDecimal orderPrice;

	@Column(name="order_quantity")
	private Integer orderQuantity;

	@Column(name="order_status")
	private String orderStatus;

	//bi-directional many-to-one association to PaymentDetail
	@ManyToOne
	@JoinColumn(name="payment_details_id", nullable=true)
	private PaymentDetail paymentDetail;
	
	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="address_id", nullable=true)
	private Address address;

	@Column(name="service_tax")
	private BigDecimal serviceTax;

	@Column(name="total_price")
	private BigDecimal totalPrice;

	//bi-directional many-to-one association to OrderItemDetail
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="orderDetail")
	private List<OrderItemDetail> orderItemDetails = new ArrayList<>();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn;

	public OrderDetail() {
	}

	public OrderItemDetail addOrderItemDetail(OrderItemDetail orderItemDetail) {
		getOrderItemDetails().add(orderItemDetail);
		orderItemDetail.setOrderDetail(this);

		return orderItemDetail;
	}

	public OrderItemDetail removeOrderItemDetail(OrderItemDetail orderItemDetail) {
		getOrderItemDetails().remove(orderItemDetail);
		orderItemDetail.setOrderDetail(null);

		return orderItemDetail;
	}
}