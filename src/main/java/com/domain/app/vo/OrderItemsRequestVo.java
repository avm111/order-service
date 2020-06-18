package com.domain.app.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemsRequestVo {
	private String pizzaBaseId;
	private List<String> toppings;
	private Integer quantity;
}
