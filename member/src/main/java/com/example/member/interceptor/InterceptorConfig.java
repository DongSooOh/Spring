package com.example.member.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ViewNameInterceptor())
				.addPathPatterns("/*.do")
				.addPathPatterns("/*/*.do")
				.excludePathPatterns("/users/login");
	}
}
