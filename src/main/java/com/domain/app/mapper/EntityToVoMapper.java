package com.domain.app.mapper;

import ma.glasnost.orika.MapperFactory;

public class EntityToVoMapper {
	private MapperFactory factory;
	
	EntityToVoMapper(MapperFactory factory) {
		this.factory = factory;
		this.init();
	}

	private void init(){
	}
}
