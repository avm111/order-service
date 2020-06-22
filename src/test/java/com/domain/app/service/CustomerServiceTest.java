package com.domain.app.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.domain.app.CommonMockUtils;
import com.domain.app.entity.Address;
import com.domain.app.entity.Customer;
import com.domain.app.entity.OrderDetail;
import com.domain.app.repository.AddressRepository;
import com.domain.app.repository.CustomerRepository;
import com.domain.app.repository.OrderRepository;
import com.domain.app.vo.CustomerRequestVo;
import com.domain.app.vo.OrderVo;

import ma.glasnost.orika.MapperFacade;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	@InjectMocks
	private CustomerService customerService;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private AddressRepository addressRepository;
	
	@Mock
	private MapperFacade mapperFacade;
	
	CommonMockUtils mockUtils = new CommonMockUtils();
	
	@Test
	@DisplayName("Test Create New Customer")
	public void createNewOrderTest() throws ParseException {
		CustomerRequestVo mockCustomerVo = mockUtils.mockCustomerRequestVo();
		OrderVo orderVo = mockUtils.mockOrderVo();
		OrderDetail orderEntity = mockUtils.mockOrderDetailEntity();
		Customer custEntity = mockUtils.mockCustomerEntity();
		Address addrEntity = mockUtils.mockAddressEntity();
		
		Mockito.doReturn(Optional.of(orderEntity)).when(orderRepository).findById(mockCustomerVo.getOrderId());
		Mockito.doReturn(Optional.empty()).when(customerRepository).findByEmailAndPhoneNumber(mockCustomerVo.getCustomer().getEmail(), mockCustomerVo.getCustomer().getPhoneNumber());
		Mockito.doReturn(custEntity).when(mapperFacade).map(mockCustomerVo.getCustomer(), Customer.class);
		Mockito.doReturn(addrEntity).when(mapperFacade).map(mockCustomerVo.getAddress(), Address.class);
		Mockito.doReturn(custEntity).when(customerRepository).save(custEntity);
		Mockito.doReturn(orderEntity).when(orderRepository).save(orderEntity);
		Mockito.doReturn(orderVo).when(mapperFacade).map(orderEntity, OrderVo.class);
		
		OrderVo savedOrder = customerService.createNewCustomer(mockCustomerVo);
		
		assertNotNull(savedOrder);
		assertTrue(savedOrder.getId().equals(orderEntity.getId()));
	}
}
