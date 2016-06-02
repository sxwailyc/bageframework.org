package com.bageframework.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bageframework.annotation.Key;

public class BeanHelper {

	protected static Log logger = LogFactory.getLog(BeanHelper.class);

	/**
	 * 读取bean唯一ID
	 * 
	 * @param obj
	 * @return
	 */
	public static String getId(Object obj) {

		Field[] fields = obj.getClass().getDeclaredFields();

		String id = null;

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(Key.class)) {
				id = (String) getValueByField(obj, field.getName());
			}
		}

		return id;
	}

	/**
	 * 读取对象字段
	 * 
	 * @param obj
	 * @param field
	 * @return
	 */
	public static Object getValueByField(Object obj, String field) {
		Object value = null;
		try {
			String methodName = fieldName2GetMethod(field);
			Method method = obj.getClass().getMethod(methodName, new Class[0]);
			value = method.invoke(obj, new Object[0]);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
		return value;
	}

	public static <T extends Annotation> T getAnnotation(Object obj, String name, Class<T> annotationClass) {
		T t = null;
		try {
			Field field = obj.getClass().getDeclaredField(name);
			t = field.getAnnotation(annotationClass);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
		return t;
	}

	/**
	 * 字段名转get方法
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String fieldName2GetMethod(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
	}

}
