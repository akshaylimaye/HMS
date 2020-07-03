package com.adlier.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringContext implements ApplicationContextAware {
	
	private static ApplicationContext context = null;

	public static ApplicationContext getApplicationContext() {
        return context;
    }
	
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		SpringContext.context = ctx;
	}
	
	public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

}
