package com.domain.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.app.dao.MenuDao;
import com.domain.app.vo.PizzaBaseVo;
import com.domain.app.vo.PizzaToppingsVo;

@Service
public class MenuService {
	
	@Autowired
	private MenuDao menuDao;

	public List<PizzaBaseVo> getPizzaOptions() {
		return menuDao.getPizzaOptions();
	}
	
	public List<PizzaToppingsVo> getPizzaToppings() {
		return menuDao.getPizzaToppings();
	}
}
