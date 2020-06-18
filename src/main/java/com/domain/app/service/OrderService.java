package com.domain.app.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.domain.app.entity.OrderDetail;
import com.domain.app.entity.OrderItemDetail;
import com.domain.app.entity.PizzaBaseMaster;
import com.domain.app.entity.PizzaToppingsMaster;
import com.domain.app.repository.OrderItemDetailsRepository;
import com.domain.app.repository.OrderRepository;
import com.domain.app.repository.PizzaBaseMasterRepository;
import com.domain.app.repository.PizzaToppingsMasterRepository;
import com.domain.app.vo.OrderItemsRequestVo;
import com.domain.app.vo.OrderRequestVo;
import com.domain.app.vo.OrderVo;

import ma.glasnost.orika.MapperFacade;

@Service
public class OrderService {
	
	@Autowired
	private PizzaBaseMasterRepository pizzaBaseMasterRepository;
	
	@Autowired
	private PizzaToppingsMasterRepository pizzaToppingsMasterRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemDetailsRepository orderItemDetailsRepository;
	
	@Autowired
	private MapperFacade mapperFacade;
	
	public OrderVo createNewOrder(OrderRequestVo requestVo) {
		List<OrderItemsRequestVo> items = null;
		if (requestVo != null) {
			items = requestVo.getOrderItems();

			OrderDetail order = new OrderDetail();
			List<OrderItemDetail> orderItemDetailsList = new ArrayList<OrderItemDetail>();
			BigDecimal orderPrice = new BigDecimal(0);
			Integer orderQuantity = 0;
			for (OrderItemsRequestVo item : items) {
				OrderItemDetail itemDetail = new OrderItemDetail();
				// Can use Redis here
				Optional<PizzaBaseMaster> baseMaster = pizzaBaseMasterRepository.findById(item.getPizzaBaseId());
				itemDetail.setPizzaBaseMaster(baseMaster.get());
				itemDetail.setToppingsArray(
						item.getToppings().stream().map(t -> String.valueOf(t)).collect(Collectors.joining(",")));
				itemDetail.setPrice(computePrice(baseMaster.get(), item.getToppings(), item.getQuantity()));
				orderPrice = orderPrice.add(itemDetail.getPrice());
				itemDetail.setQuantity(item.getQuantity());
				orderQuantity = orderQuantity + item.getQuantity();
				String createdByFromJwt = SecurityContextHolder.getContext().getAuthentication().getName();
				itemDetail.setCreatedBy(createdByFromJwt);
				itemDetail.setCreatedOn(new Date());
				orderItemDetailsList.add(itemDetail);
			}
			order.getOrderItemDetails().clear();
			order.getOrderItemDetails().addAll(orderItemDetailsList);
			order.setOrderStatus("DRAFT");
			order.setOrderPrice(orderPrice);
			order.setOrderQuantity(orderQuantity);
			BigDecimal serviceTax = orderPrice.multiply(new BigDecimal(0.05)).setScale(2, RoundingMode.CEILING);
			order.setServiceTax(serviceTax);
			order.setTotalPrice(orderPrice.add(serviceTax).setScale(2, RoundingMode.CEILING));
			order.setCreatedOn(new Date());

			OrderDetail savedOrder = orderRepository.save(order);
			List<OrderItemDetail> list = savedOrder.getOrderItemDetails();
			for(OrderItemDetail item : list) {
				item.setOrderDetail(savedOrder);
				orderItemDetailsRepository.save(item);
			}
			return mapperFacade.map(savedOrder, OrderVo.class);
		}
		return null;
	}
	

	private BigDecimal computePrice(PizzaBaseMaster pizzaBaseMaster, List<String> toppings, Integer quantity) {
		BigDecimal pricePerPizza = new BigDecimal(0);
		pricePerPizza = pizzaBaseMaster.getPrice();
		for(String toppingId : toppings) {
			//Can use Redis here
			Optional<PizzaToppingsMaster> toppingMaster = pizzaToppingsMasterRepository.findById(toppingId);
			pricePerPizza = pricePerPizza.add(toppingMaster.get().getToppingPrice());
		}
		return pricePerPizza.multiply(new BigDecimal(quantity));
	}

}
