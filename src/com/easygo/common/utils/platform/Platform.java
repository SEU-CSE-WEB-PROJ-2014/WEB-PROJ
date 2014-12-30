package com.easygo.common.utils.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;

import com.easygo.common.utils.BusinessException;

public class Platform {
	/**
	 * 平台invoke调用,有返回参数
	 */
	public static <T> T invoke(String beanName, String methodName, Class<T> clazz, Object... params){
		Object bean = SpringContextUtils.getBean(beanName);
		if(bean == null){
			throw new BusinessException("bean：" + beanName + " 不存在");
		}
		Class type = bean.getClass();
		Method[] methods = type.getMethods();
		if(ArrayUtils.isEmpty(methods)){
			throw new BusinessException("invoke调用出错：" + beanName + "不存在方法" + methodName);
		}
		for(int i = 0; i < methods.length; i++){
			if(methods[i].getName().equals(methodName)){
				try {
					return (T) methods[i].invoke(bean, params);
				} catch (IllegalArgumentException e) {
					throw new BusinessException("invoke调用参数不匹配", e);
				} catch (IllegalAccessException e) {
					throw new BusinessException("invoke调用权限不匹配", e);
				} catch (InvocationTargetException e) {
					throw new BusinessException("invoke调用目标类型错误", e);
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 平台invoke调用,无返回参数
	 */
	public static void invoke(String beanName, String methodName, Object... params){
		invoke(beanName, methodName, Object.class, params);
	}
}
