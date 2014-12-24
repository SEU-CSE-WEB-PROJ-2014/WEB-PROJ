package com.easygo.user.web;

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
import com.easygo.common.utils.Constant;
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
	
	
	@RequestMapping("/regUserPage.do")
	public ModelAndView regUserPage(){

		return new ModelAndView("user/regUser");
	}
	
	@RequestMapping("/regUser.do")
	public ModelAndView regUser(@RequestParam String email, @RequestParam String password, @RequestParam String nickName,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map result = new HashMap<String, Object>();
		Byte returnCode = Constant.RETURN_CODE_ERR;
		String msg = null;
		try{
			String userId = userService.regUser(nickName, password, email);
			result.put("userId", userId);
			returnCode = Constant.RETURN_CODE_SUCC;
		}catch(Exception e){
			msg = e.getMessage();
			e.printStackTrace();
		}
		result.put("returnCode", returnCode);
		result.put("msg", msg);
		response.setContentType(Constant.HTTP_JSON_CONTENTTYPE);
		response.getWriter().print(JSON.toJSONString(result));
		
		return null;
	}
}