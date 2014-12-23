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
	
	
	public void saveUser(String loginName, String nickName, String email){
//		CoreUser user = new CoreUser();
//		user.setLoginName(loginName);
//		user.setNickName(nickName);
//		userDao.save(user);
//		
//		CoreUserDetail userDetail = new CoreUserDetail();
//		userDetail.setUserId(user.getUserId());
//		userDetail.setEmail(email);
//		userDetailDao.save(userDetail);
	}
	
	
	public void listUserInfo(){
//		QueryResult<CoreUser> qr = 
//				this.userDao.doQuery("CoreUser.nListAll", null, Map.class);
		userDao.testNamedQuery();
		userDao.testNamedNativeQuery();
		return;
	}
		
	
}
