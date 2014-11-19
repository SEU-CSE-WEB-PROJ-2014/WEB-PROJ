package com.easygo.user.dao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserDao {
	
	@RequestMapping("/regUserPage.do")
	public ModelAndView regUserPage(){
		
		return new ModelAndView();
	}
}
