package com.domain.app;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.domain.app.entity.Address;
import com.domain.app.entity.Customer;
import com.domain.app.entity.OrderDetail;
import com.domain.app.vo.AddressVo;
import com.domain.app.vo.CustomerRequestVo;
import com.domain.app.vo.CustomerVo;
import com.domain.app.vo.OrderVo;

public class CommonMockUtils {

	public CustomerRequestVo mockCustomerRequestVo() throws ParseException {
		CustomerRequestVo vo = new CustomerRequestVo();
		vo.setOrderId("436a2063-9ad0-4247-9950-95214fc05dda");
		vo.setOrderDeliveryType("Delivery");
		vo.setOrderDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-24"));
		vo.setCustomer(mockCustomer());
		vo.setAddress(mockAddress());
		return vo;
	}

	private AddressVo mockAddress() {
		AddressVo addressVo = new AddressVo();
		addressVo.setDeliveryAddress("Los Angeles");
		addressVo.setZipcode("12345");
		return addressVo;
	}

	private CustomerVo mockCustomer() throws ParseException {
		CustomerVo custVo = new CustomerVo();
		custVo.setFirstName("Ankita");
		custVo.setLastName("Agrawal");
		custVo.setPhoneNumber("9999999999");
		custVo.setEmail("abc@gmail.com");
		custVo.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1980-07-13"));
		return custVo;
	}
	
	public OrderVo mockOrderVo() throws ParseException {
		OrderVo vo = new OrderVo();
		vo.setId("436a2063-9ad0-4247-9950-95214fc05dda");
		vo.setDeliveryType("Delivery");
		vo.setOrderPrice(new BigDecimal(85.00));
		vo.setOrderQuantity(3);
		vo.setOrderStatus("DRAFT");
		vo.setServiceTax(new BigDecimal(4.26));
		vo.setTotalPrice(new BigDecimal(89.26));
		vo.setCreatedOn(new Date());
		vo.setCustomer(mockCustomer());
		return vo;
	}

	public Customer mockCustomerEntity() throws ParseException {
		Customer cust = new Customer();
		cust.setId("Cust123");
		cust.setFirstName("Ankita");
		cust.setLastName("Agrawal");
		cust.setPhoneNumber("9999999999");
		cust.setEmail("abc@gmail.com");
		cust.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1980-07-13"));
		return cust;
	}

	public OrderDetail mockOrderDetailEntity() {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setId("436a2063-9ad0-4247-9950-95214fc05dda");
		orderDetail.setDeliveryType("Delivery");
		orderDetail.setOrderPrice(new BigDecimal(85.00));
		orderDetail.setOrderQuantity(3);
		orderDetail.setOrderStatus("DRAFT");
		orderDetail.setServiceTax(new BigDecimal(4.26));
		orderDetail.setTotalPrice(new BigDecimal(89.26));
		orderDetail.setCreatedOn(new Date());
		return orderDetail;
	}

	public Address mockAddressEntity() throws ParseException {
		Address addr = new Address();
		addr.setId("Addr123");
		addr.setDeliveryAddress("Los Angeles");
		addr.setZipcode("12345");
		addr.setCustomer(mockCustomerEntity());
		return addr;
	}
}
