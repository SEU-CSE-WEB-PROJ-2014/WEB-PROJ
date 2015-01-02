package com.easygo.user.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.common.utils.dao.SearchResult;
import com.easygo.user.service.UserService;


@Controller("bsUser")
@RequestMapping("/bsUser")
public class BSUserController {
	
	@Autowired
	private UserService user;
	
	@RequestMapping("/index.do")
	public ModelAndView index(){
		return new ModelAndView("user/bsUser/index");
	}
	
	@RequestMapping("/userGrid.do")
	public ModelAndView userGrid(
			@RequestParam Integer pageSize,
			@RequestParam Integer pageNum,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String roleId,
			@RequestParam(required=false) String email){
		Map result = new HashMap<String, Object>();
		
		//调用service层,返回分页用户数据
		SearchResult<Map> rs = user.searchUser(name, roleId, email, pageSize, pageNum);
		result.put("pageObject", rs);
		return new ModelAndView("", result);
	}
	
	@RequestMapping("/AddOrEditUser.do")
	public void addOrEditUser(
			@RequestParam(required=false) String userId,
			@RequestParam String loginName,
			@RequestParam String nickName,
			@RequestParam String password,
			@RequestParam Integer sex,
			@RequestParam String roleId,
			@RequestParam String email,
			@RequestParam String address){
		
		//TODO: 掉用service层方法,userId非空时修改用户,userId空时新增用户
		user.addOrEditUser(userId, loginName, nickName, password, sex, roleId, email, address);
	}
	
	@RequestMapping("/batchDelUsers.do")
	public void batchDelUsers(
			@RequestParam String[] userIds){
		
		//TODO: 调用service层方法,删除对应userIds的用户
		user.batchDelUser(userIds);
	}
}
