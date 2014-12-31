package com.easygo.home.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.common.utils.BusinessException;
import com.easygo.common.utils.platform.Platform;
import com.easygo.common.utils.userManager.UserManager;

@Controller("home")
@RequestMapping("/home")
public class HomeController {
	@RequestMapping("/index.do")
	public ModelAndView index(){
		Map result = new HashMap<String, Object>();
		return new ModelAndView("home/index", null);
	}
	
	@RequestMapping("/backstageIndex.do")
	public ModelAndView backstageIndex(){
		Map result = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(UserManager.getCurrentUserId())){
			//获取当前用户的管理页面
			Map[] backstageUrls = Platform.invoke("roleService", "getRoleManUrlInfo", 
					Map[].class, UserManager.getCurrentUser().getRoleId());
			
			result.put("backstageUrls", backstageUrls);
		}
		return new ModelAndView("home/backstageIndex", result);
	}

}
