package com.domain.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Getter
@Setter
public class Customer extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="email")
	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="phone_number")
	private String phoneNumber;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_of_birth")
	private Date dateOfBirth;
	
	//bi-directional many-to-one association to Order
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="customer")
	private List<Address> addresses = new ArrayList<>();

	//bi-directional many-to-one association to Order
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="customer")
	private List<OrderDetail> orderDetails;

	//bi-directional many-to-one association to PaymentDetail
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="customer")
	private List<PaymentDetail> paymentDetails;

	public Customer() {
	}

	public OrderDetail addOrder(OrderDetail order) {
		getOrderDetails().add(order);
		order.setCustomer(this);

		return order;
	}

	public OrderDetail removeOrder(OrderDetail order) {
		getOrderDetails().remove(order);
		order.setCustomer(null);

		return order;
	}

	public PaymentDetail addPaymentDetail(PaymentDetail paymentDetail) {
		getPaymentDetails().add(paymentDetail);
		paymentDetail.setCustomer(this);

		return paymentDetail;
	}

	public PaymentDetail removePaymentDetail(PaymentDetail paymentDetail) {
		getPaymentDetails().remove(paymentDetail);
		paymentDetail.setCustomer(null);

		return paymentDetail;
	}
	
	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setCustomer(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setCustomer(null);

		return address;
	}
}