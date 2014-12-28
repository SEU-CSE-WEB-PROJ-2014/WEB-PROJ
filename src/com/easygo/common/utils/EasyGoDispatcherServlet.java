package com.easygo.common.utils;

import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.View;
import org.springframework.web.util.NestedServletException;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSON;

public class EasyGoDispatcherServlet extends DispatcherServlet{
	private static final long serialVersionUID = -888294923606772463L;

	public EasyGoDispatcherServlet() {
		super();
	}
	
	public EasyGoDispatcherServlet(WebApplicationContext webApplicationContext) {
		super(webApplicationContext);
	}
	

	/**
	 * 根据modelAndView渲染页面
	 */
	protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(mv != null && mv.getViewName().equals(Constant.NULL_VIEW) && mv.getModel() != null){
			response.setContentType(Constant.HTTP_JSON_CONTENTTYPE);
			response.getWriter().print(JSON.toJSONString(mv.getModel()));
		}else{
			super.render(mv, request, response);
		}
		
	}

}
