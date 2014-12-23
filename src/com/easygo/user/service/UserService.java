package com.easygo.user.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easygo.common.utils.dao.QueryResult;
import com.easygo.user.bo.CoreUser;
import com.easygo.user.bo.CoreUserDetail;
import com.easygo.user.dao.UserDao;
import com.easygo.user.dao.UserDetailDao;


@Service("userService")
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDetailDao userDetailDao;
	
	
	public String regUser(String nickName, String password, String email){
		CoreUser user = new CoreUser();
		user.setNickName(nickName);
		user.setLoginName(email);
		user.setPassword(password);
		
		this.userDao.save(user);
		
		CoreUserDetail userDetail = new CoreUserDetail();
		
		
		return null;
		
		
	}
	
	
	public void listUserInfo(){
//		QueryResult<CoreUser> qr = 
//				this.userDao.doQuery("CoreUser.nListAll", null, Map.class);
		userDao.testNamedQuery();
		userDao.testNamedNativeQuery();
		return;
	}
		
	
}
