package com.michir.projects.springresttemplate;

import java.lang.reflect.Field;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldFilter;

@Component
public class LogInjector implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String arg1) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
			@Override
			public void doWith(Field arg0) throws IllegalArgumentException, IllegalAccessException {
				arg0.setAccessible(true);
				arg0.set(bean, LoggerFactory.getLogger(bean.getClass()));
			}
		}, new FieldFilter() {
			
			@Override
			public boolean matches(Field arg0) {
				return arg0.isAnnotationPresent(Log.class);
			}
		});
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String arg1) throws BeansException {
		return bean;
	}

}
