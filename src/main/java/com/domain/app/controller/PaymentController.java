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
import com.domain.app.service.PaymentService;
import com.domain.app.vo.PaymentDetailsRequestVo;
import com.domain.app.vo.PaymentDetailsVo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping ("/pizza/payment")
@Slf4j
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@ApiOperation(value = "Save Payment Details", nickname = "Save Payment Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = PaymentDetailsVo.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorTo.class)})
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentDetailsVo> savePaymentDetails(@RequestBody PaymentDetailsRequestVo paymentRequestVo) {
		log.info("PaymentController: savePaymentDetails API invoked");
		PaymentDetailsVo paymentDetailsVo = paymentService.createPayment(paymentRequestVo);
		if(paymentDetailsVo == null) {
			log.error("Invalid Order");
			return new ResponseEntity<PaymentDetailsVo>(new PaymentDetailsVo(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<PaymentDetailsVo>(paymentDetailsVo, HttpStatus.OK);
	}
}
