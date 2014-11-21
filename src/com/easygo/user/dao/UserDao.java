package com.easygo.user.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends HibernateDaoSupport {
	private SessionFactory sessionFacotry;

	@Resource
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}
}
