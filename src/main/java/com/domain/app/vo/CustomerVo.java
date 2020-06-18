package com.domain.app.vo;

import java.util.Date;

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
	private Date dataOfBirth;
}
