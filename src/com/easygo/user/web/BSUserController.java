package com.easygo.user.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.common.utils.dao.SearchResult;
import com.easygo.user.bo.CoreUser;
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
			@RequestParam(defaultValue="123") String password,		//默认密码123
			@RequestParam Integer sex,
			@RequestParam String roleId,
			@RequestParam String email,
			@RequestParam String address){
		
		//TODO: 掉用service层方法,userId非空时修改用户,userId空时新增用户
		if(StringUtils.isEmpty(userId)){
			userId = null;
		}
		user.addOrEditUser(userId, loginName, nickName, password, sex, roleId, email, address);
	}
	
	@RequestMapping("/addOrEditUserPage.do")
	public ModelAndView addOrEditUserPage(
			@RequestParam(required=false) String userId){
		Map result = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(userId)){
			CoreUser user = this.user.getUser(userId);
			result.put("user", user);
		}
		
		return new ModelAndView("user/bsUser/addOrEditUser", result);
	}
	
	@RequestMapping("/delUser.do")
	public void delUser(
			@RequestParam String userId){
		
		//TODO: 调用service层方法,删除对应userIds的用户
		user.batchDelUser(new String[]{userId});
	}
	
	@RequestMapping("/myOrder.do")
	public ModelAndView myOrder(
			@RequestParam String userId,
			@RequestParam(required=false) Integer pageSize,
			@RequestParam(required=false) Integer pageNum){
		
		SearchResult<Map> rs=user.myOrder(userId, pageSize, pageNum);
		
		Map Result = new HashMap<String,Object>();
		Result.put("pageObject", rs);
		return new ModelAndView("",Result);
		
		
	}
}
