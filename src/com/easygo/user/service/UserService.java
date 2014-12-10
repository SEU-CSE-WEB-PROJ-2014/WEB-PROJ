package com.easygo.user.service;



import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.annotations.Entity;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easygo.user.bo.User;
import com.easygo.user.dao.UserDao;

@Service("userService")
public class UserService {
	@Autowired
	private UserDao userDao;
	
//	@Transactional(readOnly = false)
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
		
		Session s = userDao.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = s.beginTransaction();
		userDao.getHibernateTemplate().save(user);
		userDao.getHibernateTemplate().flush();
		s.flush();
		tx.commit();
		
//		user.setUserId(null);
//		userDao.getHibernateTemplate().save(user);
		userDao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction().commit();
	}
	
	
	public void listAllUsers(){ 
		//List list = userDao.getHibernateTemplate().findByNamedQuery("listUserQuery", null);
	}
}
