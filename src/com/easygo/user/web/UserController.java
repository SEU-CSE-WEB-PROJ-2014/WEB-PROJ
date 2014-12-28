package com.easygo.user.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.easygo.common.utils.BusinessException;
import com.easygo.common.utils.Constant;
import com.easygo.common.utils.userManager.UserManager;
import com.easygo.user.bo.CoreUser;
import com.easygo.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	
	@RequestMapping("/loginPage.do")
	public ModelAndView loginPage(){
		
		return new ModelAndView("user/login");
	}

	
	@RequestMapping("/login.do")
	public ModelAndView login(@RequestParam String loginName, @RequestParam String password,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		this.userService.login(loginName, password, request, response);
		return null;
	}
	
	
	@RequestMapping("/regUserPage.do")
	public ModelAndView regUserPage(){
		return new ModelAndView("user/regUser");
	}
	
	@RequestMapping("/regUser.do")
	public ModelAndView regUser(@RequestParam String email, @RequestParam String password, @RequestParam String nickName,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map result = new HashMap<String, Object>();
		String userId = userService.regUser(nickName, password, email);
		result.put("userId", userId);
		return new ModelAndView("", result);
	}
	
	@RequestMapping("/test.do")
	public ModelAndView test(){
		CoreUser coreUser = (CoreUser) UserManager.getCoreUser();
		
		return null;
	}
}