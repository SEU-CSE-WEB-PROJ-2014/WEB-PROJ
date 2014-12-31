package com.easygo.user.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import com.easygo.common.utils.userManager.LoginUser;
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
			HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException, InvocationTargetException{
		this.userService.login(loginName, password, request, response);
		return null;
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
		this.userService.logout(request, response);
		return null;
	}
	
	
	@RequestMapping("/regUserPage.do")
	public ModelAndView regUserPage(){
		return new ModelAndView("user/regUser");
	}
	
	@RequestMapping("/regUser.do")
	public void regUser(
			@RequestParam String loginName,
			@RequestParam String nickName,
			@RequestParam String password,
			@RequestParam(required = false) Integer sex,
			@RequestParam String email, 
			@RequestParam(required = false) String address,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		userService.addOrEditUser(null, loginName, nickName, password, sex, LoginUser.ROLE_ID_USERS, email, address);

	}
}