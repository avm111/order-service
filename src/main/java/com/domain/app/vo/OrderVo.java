package com.domain.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVo {
	private String id;
	private String deliveryType;
	private Date orderDeliveryDate;
	private BigDecimal orderPrice;
	private Integer orderQuantity;
	private String orderStatus;
	private int paymentDetailsId;
	private BigDecimal serviceTax;
	private BigDecimal totalPrice;
	private Date createdOn;
	private String createdBy;
	private CustomerVo customer;
}
