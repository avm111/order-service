package com.domain.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
	  public static final String AUTHORIZATION_HEADER = "Authorization";
	
    @Bean
    public Docket api() {
        return new Docket ( DocumentationType.SWAGGER_2 )
                .apiInfo ( apiInfo () )
                .select ()
                .apis ( RequestHandlerSelectors.basePackage ( "com.domain.app" ) )
                .paths ( PathSelectors.any () )
                .build ();
    }

    // Describe your apis
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder ()
                .title ( "Order-Service APIs" )
                .description ( "This page lists all the rest apis for Order Services." )
                .version ( "0.0.1-SNAPSHOT" )
                .build ();
    }
    

	private ApiKey apiKey() {
	    return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	
}


