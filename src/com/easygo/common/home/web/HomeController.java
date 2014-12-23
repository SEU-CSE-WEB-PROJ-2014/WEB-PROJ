package com.easygo.common.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("home")
@RequestMapping("/home")
public class HomeController {

	@RequestMapping("/index.do")
	public ModelAndView index(){
		return new ModelAndView("common/index", null);
	}

}
