package com.easygo.home.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.common.role.service.RoleService;
import com.easygo.common.utils.BusinessException;

@Controller("home")
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private RoleService roleService;

	@RequestMapping("/index.do")
	public ModelAndView index(){
		Map[] info = roleService.getRoleManUrlInfo("ID001");
		
		Map result = new HashMap<String, Object>();
		return new ModelAndView("home/index", null);
	}
	
	@RequestMapping("/backstageIndex.do")
	public ModelAndView backstageIndex(){
		
		return new ModelAndView();
	}

}
