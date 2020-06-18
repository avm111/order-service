package com.domain.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the payment_details database table.
 * 
 */
@Entity
@Getter
@Setter
@Table(name="payment_details")
public class PaymentDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name = "customer_id",nullable=true)
	private Customer customer;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="card_expiry_date")
	private String cardExpiryDate;

	@Temporal(TemporalType.DATE)
	@Column(name="created_on")
	private Date createdOn;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="payment_type")
	private String paymentType;

	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="paymentDetail")
	private List<OrderDetail> orderDetails;
	
	public PaymentDetail() {
	}
}