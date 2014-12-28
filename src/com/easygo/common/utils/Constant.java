package com.easygo.common.utils;

public final class Constant {
	/**
	 * 状态：出错
	 */
	public static final Byte STATUS_ERR = 0;
	
	
	/**
	 * 状态：成功
	 */
	public static final Byte STATUS_SUCC = 1;
	

	/**
	 * 返回内容格式：文本
	 */
	public static final String HTTP_PLAIN_CONTENTTYPE = "text/plain;charset=UTF-8";

	
	/**
	 * 返回内容格式：json数据
	 */
	public static final String HTTP_JSON_CONTENTTYPE = "application/json;charset=UTF-8";
	
	
	/**
	 * 以json格式返回modelAndView的数据，没有view
	 */
	public static final String NULL_VIEW = "_null_view";
}
