package com.easygo.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class AbstractController {
	@RequestMapping("*.do")
	public ModelAndView doControll(HttpServletRequest request, HttpServletResponse response){
		String servletPath = request.getServletPath();
		if(StringUtils.isNotEmpty(servletPath)){
			String[] strs = StringUtils.split(servletPath, "/");
			if(ArrayUtils.isNotEmpty(strs) && strs.length >= 2){
				String methodName = StringUtils.substringBeforeLast(strs[strs.length-1], 
						".do");
				Class clazz = this.getClass();
				clazz.getMethods();
				
				
			}
			
		}
		
		
		return null;
	}

}
