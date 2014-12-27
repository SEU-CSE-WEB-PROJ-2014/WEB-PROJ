package com.easygo.common.utils.userManager;

import java.util.HashMap;
import java.util.Map;


public class UserManager {
	private static final ThreadLocal SESSION_MAP = new ThreadLocal();
	
	public static final String CORE_USER_KEY = "coreUser";
	public static final String CORE_USER_DETAIL_KEY = "coreUserDetail";
	
	protected UserManager(){
		
	}
	
	public static Object get(String attribute){
		Map map = (Map) SESSION_MAP.get();
		if(map != null){
			return map.get(attribute);
		}else{
			return null;
		}
	}
	
	public static <T> T get(String attribute, Class<T> clazz){
		return (T) get(attribute);
	}
	
	public static void set(String attribute, Object value){
		Map map = (Map)SESSION_MAP.get();
		if(map == null){
			map = new HashMap<String, Object>();
			SESSION_MAP.set(map);
		}
		map.put(attribute, value);
	}
	
	public static void setCoreUser(Object coreUser){
		set(CORE_USER_KEY, coreUser);
	}
	
	public static Object getCoreUser(){
		return get(CORE_USER_KEY);
	}
	

	public static void setCoreUserDetail(Object coreUserDetail){
		set(CORE_USER_DETAIL_KEY, coreUserDetail);
	}
	
	public static Object getCoreUserDetail(){
		return get(CORE_USER_DETAIL_KEY);
	}
	
	public static void removeThreadData(){
		SESSION_MAP.remove();
	}
	
	
	
}
