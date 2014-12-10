package com.easygo.user.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.easygo.common.utils.dao.HibernateDao;
import com.easygo.common.utils.dao.HibernateDaoImpl;
import com.easygo.user.bo.User;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoImpl<String, User> implements UserDao{
	
}
