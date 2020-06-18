package com.domain.app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Getter
@Setter
@Table(name="address")
public class Address extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 8697353869771629817L;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable=true)
	private Customer customer;

	@Column(name="delivery_address")
	private String deliveryAddress;

	@Column(name="zipcode")
	private String zipcode;
	
	//bi-directional many-to-one association to OrderDetail
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="address")
	private List<OrderDetail> orderDetails;
	
	public Address() {
	}
}