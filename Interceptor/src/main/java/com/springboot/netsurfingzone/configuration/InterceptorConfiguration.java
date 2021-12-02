package com.springboot.netsurfingzone.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.springboot.netsurfingzone.interceptors.RequestHandlerInterceptor;

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurationSupport{

	@Autowired
	RequestHandlerInterceptor requestHandlerInterceptor;
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestHandlerInterceptor);
	}
}
