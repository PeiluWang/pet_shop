package com.upa.pet_shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.upa.pet_shop.interceptor.LogInterceptor;
import com.upa.pet_shop.interceptor.SecurityInterceptor;
import com.upa.pet_shop.service.UserService;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	UserService userService;
	
	/**
	 * 指定接口跳转
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		//将跟目录跳转到指定目录
		//registry.addViewController("/").setViewName("forward:/back/sysinfo/is_alive");
	}
	
	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 设置不检查token的路径
		String[] securityExcludePatterns = new String[]{
				"/back/users/login",
				"/back/users/register",
				"/back/sysinfo/*"};
		// 添加拦截器
		registry.addInterceptor(new SecurityInterceptor(userService))
				.excludePathPatterns(securityExcludePatterns);
		registry.addInterceptor(new LogInterceptor());
	}
	
}
