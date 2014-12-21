package com.easygo.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	
	@RequestMapping("/regUserPage.do")
	public ModelAndView regUserPage(){

		return new ModelAndView();
	}
	
	@RequestMapping("/regUser.do")
	public ModelAndView regUser() throws Exception{
		userService.saveUser("jallenjia", "jall", "jallenjia@sina.com");
		userService.listUserInfo();
		
		return null;
	}
}