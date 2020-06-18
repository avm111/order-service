package com.domain.app.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaToppingsVo {
	private String id;
	private String toppingName;
	private String toppingType;
	private BigDecimal toppingPrice;
}
