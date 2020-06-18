package com.domain.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.app.dao.MenuDaoImpl;
import com.domain.app.vo.PizzaBaseVo;
import com.domain.app.vo.PizzaToppingsVo;

@Service
public class MenuService {

	/*
	 * @Autowired private PizzaBaseMasterRepository pizzaBaseMasterRepository;
	 * 
	 * @Autowired private PizzaToppingsMasterRepository
	 * pizzaToppingsMasterRepository;
	 */
	
	@Autowired
	private MenuDaoImpl menuDao;
	/*
	@Autowired
	private MapperFacade mapperFacade;
	
	public List<PizzaBaseVo> getPizzaOptions() {
		List<PizzaBaseMaster> pizzaBaseList = pizzaBaseMasterRepository.findAll();
		return mapperFacade.mapAsList(pizzaBaseList, PizzaBaseVo.class);
	}
	
	public List<PizzaToppingsVo> getPizzaToppings() {
		List<PizzaToppingsMaster> pizzaToppingsList = pizzaToppingsMasterRepository.findAll();
		return mapperFacade.mapAsList(pizzaToppingsList, PizzaToppingsVo.class);
	}*/
	
	public List<PizzaBaseVo> getPizzaOptions() {
		return menuDao.getPizzaOptions();
	}
	
	public List<PizzaToppingsVo> getPizzaToppings() {
		return menuDao.getPizzaToppings();
	}
}
