package com.easygo.user.service;



import java.util.List;

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
		
		
		userDao.save(user);
	}
	
	
	public void listAllUsers(){ 
	}
}
