package com.library.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@Component
public class OpenApiConfig  {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
					.components(new Components())
					.info(new Info()
							.title("Library Management Systtem")
							.description("\"Library Management System\""));
				
	}

}
