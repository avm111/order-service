package com.domain.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.app.dao.OrderDao;
import com.domain.app.vo.OrderItemsRequestVo;
import com.domain.app.vo.OrderRequestVo;
import com.domain.app.vo.OrderVo;

@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	public OrderVo createNewOrder(OrderRequestVo requestVo) {
		List<OrderItemsRequestVo> items = null;
		if (requestVo != null) {
			items = requestVo.getOrderItems();
			return orderDao.createOrUpdateOrder(items, null);
		}
		return null;
	}

	public OrderVo updateOrder(OrderRequestVo requestVo, String orderId) {
		List<OrderItemsRequestVo> items = null;
		if (requestVo != null) {
			items = requestVo.getOrderItems();
			return orderDao.createOrUpdateOrder(items, orderId);
		}
		return null;
	}
}
