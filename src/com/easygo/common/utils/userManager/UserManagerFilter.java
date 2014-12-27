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

		HttpSession s = h_request.getSession(false);
		if(s != null){
			Object coreUser = s.getAttribute(UserManager.CORE_USER_KEY);
			Object coreUserDetail = s.getAttribute(UserManager.CORE_USER_DETAIL_KEY);
			UserManager.setCoreUser(coreUser);
			UserManager.setCoreUserDetail(coreUserDetail);
		}
		
		chain.doFilter(h_request, h_response);
		
		//清空当前线程数据
		UserManager.removeThreadData();
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
