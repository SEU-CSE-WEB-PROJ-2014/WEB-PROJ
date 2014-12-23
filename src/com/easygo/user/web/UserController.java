package com.easygo.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	
	@RequestMapping("/regUserPage.do")
	public ModelAndView regUserPage(){

		return new ModelAndView("user/regUser");
	}
	
	@RequestMapping("/regUser.do")
	public ModelAndView regUser(@RequestParam String email, @RequestParam String password, @RequestParam String nickName) throws Exception{
		userService.regUser(nickName, password, email);

		userService.listUserInfo();
		
		return null;
	}
}