package com.easygo.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.common.utils.AbstractController;

@Controller("test")
@RequestMapping("/test")
public class TestController extends AbstractController{
	@RequestMapping("/test1.do")
	public ModelAndView test1(HttpServletRequest request, HttpServletResponse response){
		request.getContextPath();
		
		
		
		return null;
	}

}
