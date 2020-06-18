package com.domain.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.app.exception.ErrorTo;
import com.domain.app.service.MenuService;
import com.domain.app.vo.PizzaBaseVo;
import com.domain.app.vo.PizzaToppingsVo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping ("/pizza/menu")
@Slf4j
public class MenuController {

	@Autowired
	private MenuService menuService;

	@ApiOperation(value = "Get Available Pizza Base Options", nickname = "createYourOwnPizzaBase")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = PizzaBaseVo.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorTo.class)})
	@GetMapping(value = "/pizzaBase", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PizzaBaseVo>> getPizzaBase() {
		log.info("ProductsController: getPizzaOptions API invoked");
		return new ResponseEntity<List<PizzaBaseVo>>(menuService.getPizzaOptions(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get Available Pizza Toppings", nickname = "getToppings")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = PizzaBaseVo.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorTo.class)})
	@GetMapping(value = "/toppings", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PizzaToppingsVo>> getPizzaToppings() {
		log.info("ProductsController: getPizzaToppings API invoked");
		return new ResponseEntity<List<PizzaToppingsVo>>(menuService.getPizzaToppings(), HttpStatus.OK);
	}
}