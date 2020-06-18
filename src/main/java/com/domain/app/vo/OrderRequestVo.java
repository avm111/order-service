package com.domain.app.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequestVo {
	private List<OrderItemsRequestVo> orderItems = new ArrayList<OrderItemsRequestVo>();
}
