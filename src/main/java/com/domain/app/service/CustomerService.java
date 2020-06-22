package com.domain.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.app.entity.Address;
import com.domain.app.entity.Customer;
import com.domain.app.entity.OrderDetail;
import com.domain.app.repository.AddressRepository;
import com.domain.app.repository.CustomerRepository;
import com.domain.app.repository.OrderRepository;
import com.domain.app.vo.AddressVo;
import com.domain.app.vo.CustomerRequestVo;
import com.domain.app.vo.CustomerVo;
import com.domain.app.vo.OrderVo;

import ma.glasnost.orika.MapperFacade;

@Service
public class CustomerService {
	@Autowired
	private MapperFacade mapperFacade;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	//Create new Customer, Address and update Order table
	public OrderVo createNewCustomer(CustomerRequestVo customerRequestVo) {
		Optional<OrderDetail> orderOptional = orderRepository.findById(customerRequestVo.getOrderId());
		
		if(orderOptional.isPresent()) {
			OrderDetail orderEntity = orderOptional.get();
			CustomerVo customerVo = null;
			Customer customerEntity = null;
			Address addressEntity = null;
			if(customerRequestVo.getCustomer() != null) {
				customerVo = customerRequestVo.getCustomer();
				
				//Check if Customer Exists
				Optional<Customer> existingCustomer = customerRepository.findByEmailAndPhoneNumber(customerVo.getEmail(), customerVo.getPhoneNumber());
				if(existingCustomer.isPresent()) {
					customerEntity = existingCustomer.get();
					//Check if Address Exists
					if (customerEntity.getAddresses() != null) {
						for (Address address : customerEntity.getAddresses()) {
							String trimAddress = address.getDeliveryAddress().replaceAll("\\s", "");
							if(!trimAddress.equalsIgnoreCase(customerRequestVo.getAddress().getDeliveryAddress().replaceAll("\\s", ""))) {
								addressEntity = mapperFacade.map(customerRequestVo.getAddress(), Address.class);
								addressEntity.setCustomer(customerEntity);
								addressEntity = addressRepository.save(addressEntity);
							}
						}
					}
				} else {
					customerEntity = mapperFacade.map(customerVo, Customer.class);
					AddressVo addressVo = customerRequestVo.getAddress();
					addressEntity = mapperFacade.map(addressVo, Address.class);
					addressEntity.setCustomer(customerEntity);
					List<Address> addressList = new ArrayList<>();
					addressList.add(addressEntity);
					customerEntity.setAddresses(addressList);
					customerEntity = customerRepository.save(customerEntity);
				}
				orderEntity.setCustomer(customerEntity);
				orderEntity.setAddress(addressEntity);
			}
			orderEntity.setDeliveryType(customerRequestVo.getOrderDeliveryType());
			orderEntity.setOrderDeliveryDate(customerRequestVo.getOrderDeliveryDate());
			
			OrderDetail savedOrder = orderRepository.save(orderEntity);
			//return mapperFacade.map(savedOrder.getCustomer(), CustomerVo.class);
			return mapperFacade.map(savedOrder, OrderVo.class);
		}
		return null;
	}

	public CustomerVo getExistingCustomerInfo(CustomerVo requestVo) {
		Customer customerEntity = null;
		Optional<Customer> customerOptional = customerRepository.findByEmailAndPhoneNumber(requestVo.getEmail(), requestVo.getPhoneNumber());
		if(customerOptional.isPresent()) {
			customerEntity = customerOptional.get();
			return mapperFacade.map(customerEntity, CustomerVo.class);
		}
		return null;
	}

}
