package com.domain.app.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String DEFAULT_INCLUDE_PATTERN = "/pizza/.*";
	
    @Bean
    public Docket api() {
        return new Docket ( DocumentationType.SWAGGER_2 )
                .apiInfo ( apiInfo () )
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
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
	
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN)).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}
}


