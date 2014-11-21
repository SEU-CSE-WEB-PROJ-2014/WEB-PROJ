package com.easygo.user.service;

import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easygo.user.bo.User;
import com.easygo.user.dao.UserDao;

@Service("userService")
public class UserService {
	@Autowired
	private UserDao userDao;
	
	
	public void addUser(String loginName,
			String nickName,
			Short sex,
			String password,
			Short state) {
		User user = new User();
		user.setLoginName(loginName);
		user.setNickName(nickName);
		user.setPassword(password);
		user.setSex(sex);
		user.setState(state);
		
		Session session = userDao.getSessionFactory().openSession();
		session.beginTransaction();
		userDao.getHibernateTemplate().save(user);
		session.getTransaction().commit();
		session.close();
	}
}
