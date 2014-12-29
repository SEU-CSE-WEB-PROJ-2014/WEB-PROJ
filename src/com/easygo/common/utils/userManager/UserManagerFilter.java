package com.easygo.common.utils.userManager;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserManagerFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest h_request = (HttpServletRequest) request;
		HttpServletResponse h_response = (HttpServletResponse) response;

		//登录用户数据放入UserManager
		HttpSession s = h_request.getSession(false);
		if(s != null){
			LoginUser loginUser  = (LoginUser) s.getAttribute(UserManager.LOGIN_USER_KEY);
			UserManager.setCurrentUser(loginUser);
		}
		
		//调用controller方法
		chain.doFilter(h_request, h_response);
		
		//清空当前线程数据
		UserManager.removeThreadData();
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
