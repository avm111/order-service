package com.domain.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.app.exception.ErrorTo;
import com.domain.app.service.OrderService;
import com.domain.app.vo.OrderRequestVo;
import com.domain.app.vo.OrderVo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping ("/pizza/order")
@Slf4j
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value = "Add to Cart", nickname = "Add Order to Cart")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = OrderVo.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorTo.class)})
	@PostMapping(value = "/addToCart", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderVo> addToCart(@RequestBody OrderRequestVo requestVo) {
		log.info("OrderController: addToCart API invoked");
		OrderVo order = orderService.createNewOrder(requestVo);
		if(order == null) {
			return new ResponseEntity<OrderVo>(new OrderVo(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<OrderVo>(order, HttpStatus.OK);
	}

	//This API will take care of updating/deleting Items from Cart
	
	@ApiOperation(value = "Update Cart", nickname = "Update Cart Items")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = OrderVo.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorTo.class)})
	@PutMapping(value = "/updateCart/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderVo> updateCart(@RequestBody OrderRequestVo requestVo, @PathVariable String orderId) {
		log.info("OrderController: updateCart API invoked");
		if(StringUtils.isEmpty(orderId)) {
			return new ResponseEntity<OrderVo>(new OrderVo(), HttpStatus.BAD_REQUEST);
		}
		OrderVo order = orderService.updateOrder(requestVo, orderId);
		if(order == null) {
			return new ResponseEntity<OrderVo>(new OrderVo(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<OrderVo>(order, HttpStatus.OK);
	}
}
