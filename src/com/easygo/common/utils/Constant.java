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
	public static final String NULL_VIEW = "";
	
	
	/**
	 * controller方法返回的状态变量的名称
	 */
	public static final String STATUS_NAME = "status";
	
	
	/**
	 * controller方法异常时返回出错提示变量的名称
	 */
	public static final String MSG_NAME = "msg";
	
	
	/**
	 * 网站主页
	 */
	public static final String HOME_PAGE = "goods/searchGoods.do";
	
	
	/**
	 * 默认分页大小
	 */
	public static final Integer DEFAULT_PAGE_SIZE = 10;
}
