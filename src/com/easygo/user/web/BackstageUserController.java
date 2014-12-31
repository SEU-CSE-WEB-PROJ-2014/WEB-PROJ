package com.easygo.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller("backstageUser")
@RequestMapping("/backstageUser")
public class BackstageUserController {
	@RequestMapping("/index.do")
	public ModelAndView index(){
		return new ModelAndView("user/backstage/index");
	}
}
