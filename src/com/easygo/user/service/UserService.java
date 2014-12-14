package com.easygo.user.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Transaction;
import org.hibernate.annotations.Entity;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easygo.user.bo.User;
import com.easygo.user.dao.UserDao;

@Service("userService")
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void saveUser(String loginName,
			String nickName,
			Short sex,
			String password,
			Short state) throws Exception {
		User user = new User();
		user.setLoginName(loginName);
		user.setNickName(nickName);
		user.setPassword(password);
		user.setSex(sex);
		user.setState(state);
		
		User user2 = new User();
		user2.setLoginName("A1");
		user2.setNickName("A2");
		user2.setState((short) 1);
		
		User user3 = new User();
		user3.setLoginName("A1");
		user3.setNickName("A2");
		user3.setState((short) 1);
		
		User user4 = new User();
		user4.setLoginName("A1");
		user4.setNickName("A2");
		user4.setState((short) 1);
		userDao.save(user);
		userDao.save(user2);
		userDao.save(user3);
		userDao.save(user4);
		
	}
	
	
	public void listAllUsers(){
		Map params = new HashMap<String, Object>();
		params.put("nickName", "JALL");
		List<User> list = (List<User>) userDao.findByParams("from User u where 1=1 and u.nickName = :nickName", params);
		userDao.eqQueryByParams(params);
		
		params.clear();
		params.put("nickName", new String[]{"JALL", "A2"});
		
		userDao.inQueryByParams(params);
		
		
		params.put("sex", (short)0);
		userDao.mixedInEqQueryByParams(params);
		
		
		return;
	}
}
