package com.easygo.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("/regUserPage.do")
	public ModelAndView regUserPage(){
		
		return new ModelAndView();
	}
}
