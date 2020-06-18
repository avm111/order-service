package com.domain.app.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemsVo {
	private PizzaBaseVo base;
	private List<PizzaToppingsVo> toppings;
	private Integer quantity;
}
