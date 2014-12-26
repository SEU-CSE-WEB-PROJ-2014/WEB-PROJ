package com.easygo.serverManager.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.easygo.common.utils.Constant;

@Controller("manager")
@RequestMapping("/manager")
public class ServerManagerController {
	
	@RequestMapping("/index.do")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("manager/index");
	}
	
	@RequestMapping("/reload.do")
	public ModelAndView reload(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map result = new HashMap<String, Object>();
		Byte status = Constant.STATUS_ERR;
		String msg = null;
		try{
			WebApplicationContext wac
			 = WebApplicationContextUtils.getWebApplicationContext(request.getSession(true).getServletContext());
			XmlWebApplicationContext context = (XmlWebApplicationContext) wac;
			context.refresh();
			status = Constant.STATUS_SUCC;
		}catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		result.put("status", status);
		result.put("msg", msg);
		response.setContentType(Constant.HTTP_JSON_CONTENTTYPE);
		response.getWriter().print(JSON.toJSONString(result));
		
		return null;
	}
}
