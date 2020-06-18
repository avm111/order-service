package com.domain.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.domain.app.entity.PizzaBaseMaster;
import com.domain.app.entity.PizzaToppingsMaster;
import com.domain.app.repository.PizzaBaseMasterRepository;
import com.domain.app.repository.PizzaToppingsMasterRepository;
import com.domain.app.vo.PizzaBaseVo;
import com.domain.app.vo.PizzaToppingsVo;

import ma.glasnost.orika.MapperFacade;

@Repository
public class MenuDaoImpl {

	@Autowired
	private PizzaBaseMasterRepository pizzaBaseMasterRepository;
	
	@Autowired
	private PizzaToppingsMasterRepository pizzaToppingsMasterRepository;
	
	@Autowired
	private MapperFacade mapperFacade;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private static final String PIZZA_OPTIONS_KEY = "PIZZA_OPTIONS_KEY";
	private static final String PIZZA_TOPPINGS_KEY = "PIZZA_TOPPINGS_KEY";
	
	@SuppressWarnings("unchecked")
	public List<PizzaBaseVo> getPizzaOptions() {
		List<PizzaBaseMaster> pizzaBaseList = (List<PizzaBaseMaster>) redisTemplate.opsForHash().get(PIZZA_OPTIONS_KEY, PIZZA_OPTIONS_KEY);
		if(pizzaBaseList == null) {
			pizzaBaseList = pizzaBaseMasterRepository.findAll(); 
			redisTemplate.opsForHash().put(PIZZA_OPTIONS_KEY, PIZZA_OPTIONS_KEY, pizzaBaseList);
		}	
		return mapperFacade.mapAsList(pizzaBaseList, PizzaBaseVo.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<PizzaToppingsVo> getPizzaToppings() {
		List<PizzaToppingsMaster> pizzaToppingsList = (List<PizzaToppingsMaster>) redisTemplate.opsForHash().get(PIZZA_TOPPINGS_KEY, PIZZA_TOPPINGS_KEY);
		if(pizzaToppingsList == null) {
			pizzaToppingsList = pizzaToppingsMasterRepository.findAll();
			redisTemplate.opsForHash().put(PIZZA_TOPPINGS_KEY, PIZZA_TOPPINGS_KEY, pizzaToppingsList);
		}	
		return mapperFacade.mapAsList(pizzaToppingsList, PizzaToppingsVo.class);
	}
}
