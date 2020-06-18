package com.domain.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.app.entity.Address;
import com.domain.app.entity.Customer;
import com.domain.app.entity.OrderDetail;
import com.domain.app.repository.CustomerRepository;
import com.domain.app.repository.OrderRepository;
import com.domain.app.vo.AddressVo;
import com.domain.app.vo.CustomerRequestVo;
import com.domain.app.vo.CustomerVo;

import ma.glasnost.orika.MapperFacade;

@Service
public class CustomerService {
	@Autowired
	private MapperFacade mapperFacade;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//Create new Customer, Address and update Order table
	public CustomerVo createNewCustomer(CustomerRequestVo customerRequestVo) {
		Optional<OrderDetail> orderOptional = orderRepository.findById(customerRequestVo.getOrderId());
		
		if(orderOptional.isPresent()) {
			OrderDetail orderEntity = orderOptional.get();
			CustomerVo customerVo = null;
			if(customerRequestVo.getCustomer() != null) {
				customerVo = customerRequestVo.getCustomer();
				Customer customerEntity = mapperFacade.map(customerVo, Customer.class);
				AddressVo addressVo = customerRequestVo.getAddress();
				Address addressEntity = mapperFacade.map(addressVo, Address.class);
				addressEntity.setCustomer(customerEntity);
				customerEntity.getAddresses().add(addressEntity);
				
				customerEntity = customerRepository.save(customerEntity);
				orderEntity.setCustomer(customerEntity);
			}
			orderEntity.setDeliveryType(customerRequestVo.getOrderDeliveryType());
			orderEntity.setOrderDeliveryDate(customerRequestVo.getOrderDeliveryDate());
			
			OrderDetail savedOrder = orderRepository.save(orderEntity);
			return mapperFacade.map(savedOrder.getCustomer(), CustomerVo.class);
		}
		return null;
	}

}
