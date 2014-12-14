package com.easygo.common.utils;


/**
 * 业务异常
 * 平台业务处理出错时都应该抛出此异常
 * @author jljia
 *
 */
public class BusinessException extends RuntimeException{
	public BusinessException() {
		super();
	}
	
	public BusinessException(String errorMsg) {
		super(errorMsg);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String errorMsg, Throwable cause) {
		super(errorMsg, cause);
	}
}
