package com.easygo.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller("bsUser")
@RequestMapping("/bsUser")
public class BSUserController {
	@RequestMapping("/index.do")
	public ModelAndView index(){
		return new ModelAndView("user/bsUser/index");
	}
}