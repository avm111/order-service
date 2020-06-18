package com.domain.app.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressVo {
	private String id;
	private String deliveryAddress;
	private String zipcode;
}
