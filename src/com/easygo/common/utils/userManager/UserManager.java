package com.easygo.common.utils.userManager;

import java.util.HashMap;
import java.util.Map;


public class UserManager {
	private static final ThreadLocal SESSION_MAP = new ThreadLocal();
	
	public static final String LOGIN_USER_KEY = "loginUser";
	
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
	
	

	public static void setCurrentUser(LoginUser LoginUser){
		set(LOGIN_USER_KEY, LoginUser);
	}
	
	public static LoginUser getCurrentUser(){
		return get(LOGIN_USER_KEY, LoginUser.class);
	}
	
	public static String getCurrentUserId(){
		LoginUser loginUser = get(LOGIN_USER_KEY, LoginUser.class);
		if(loginUser != null){
			return loginUser.getUserId();
		}else{
			return null;
		}
	}
	
	public static void removeThreadData(){
		SESSION_MAP.remove();
	}
	
	
	
}
