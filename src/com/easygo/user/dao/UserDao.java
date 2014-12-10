package com.easygo.user.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.easygo.common.utils.dao.HibernateDao;
import com.easygo.user.bo.User;

public interface UserDao extends HibernateDao<String, User> {
	
}
