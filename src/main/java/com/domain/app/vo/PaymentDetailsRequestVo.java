package com.domain.app.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDetailsRequestVo {
	private String orderId;
	private PaymentDetailsVo paymentDetails;
}
