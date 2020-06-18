package com.domain.app.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class BeanOrikaMapper extends ConfigurableMapper {

    @Override
    public void configure(MapperFactory factory) {
        new EntityToVoMapper (factory);
    }
}
