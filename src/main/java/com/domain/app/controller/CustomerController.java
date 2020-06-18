package com.domain.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.app.exception.ErrorTo;
import com.domain.app.service.CustomerService;
import com.domain.app.vo.CustomerRequestVo;
import com.domain.app.vo.CustomerVo;
import com.domain.app.vo.PizzaBaseVo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping ("/pizza/customer")
@Slf4j
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@ApiOperation(value = "Save Customer Details", nickname = "Save Customer Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = PizzaBaseVo.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorTo.class)})
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerVo> saveCustomerDetails(@RequestBody CustomerRequestVo customerRequestVo) {
		log.info("CustomerController: saveCustomerDetails API invoked");
		CustomerVo customerVo = customerService.createNewCustomer(customerRequestVo);
		if(customerVo == null) {
			log.error("Invalid Order");
			return new ResponseEntity<CustomerVo>(new CustomerVo(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CustomerVo>(customerVo, HttpStatus.OK);
	}
}
