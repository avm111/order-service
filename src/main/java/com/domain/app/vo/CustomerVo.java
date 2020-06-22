package com.domain.app.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerVo {
	private String id;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Date dateOfBirth;
	private List<AddressVo> addresses;
	private List<PaymentDetailsVo> paymentDetails;
}
