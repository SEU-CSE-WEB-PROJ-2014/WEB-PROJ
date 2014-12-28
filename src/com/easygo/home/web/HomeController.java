package com.easygo.home.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.common.utils.BusinessException;

@Controller("home")
@RequestMapping("/home")
public class HomeController {

	@RequestMapping("/index.do")
	public ModelAndView index(){
		Map result = new HashMap<String, Object>();
		return new ModelAndView("home/index", null);
	}

}
