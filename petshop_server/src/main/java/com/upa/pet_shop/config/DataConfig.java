package com.upa.pet_shop.config;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置可访问hibernate sessionFactory
 * @author peilu.wang
 *
 */
@Configuration
public class DataConfig {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public SessionFactory getSessionFactory() {
	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
	        throw new NullPointerException("Not a hibernate factory exception");
	    }
	    return entityManagerFactory.unwrap(SessionFactory.class);
	}
}
