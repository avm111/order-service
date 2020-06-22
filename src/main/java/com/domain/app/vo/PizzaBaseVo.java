package com.domain.app.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaBaseVo {
	private String baseId;
	private String baseSize;
	private String crustType;
	private BigDecimal price;
}
