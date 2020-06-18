package com.domain.app.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRequestVo {

	private String orderId;
	private CustomerVo customer;
	private String orderDeliveryType;
	private AddressVo address;
	private Date orderDeliveryDate;
}	
