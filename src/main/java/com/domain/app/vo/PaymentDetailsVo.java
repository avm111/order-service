package com.domain.app.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDetailsVo {
	private String firstName;
	private String lastName;
	private String paymentType;
	private String cardNumber;
	private String cardExpiryDate;
}
