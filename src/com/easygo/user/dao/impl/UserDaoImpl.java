package com.easygo.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.easygo.common.utils.dao.HibernateDaoImpl;
import com.easygo.user.bo.CoreUser;
import com.easygo.user.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoImpl<String, CoreUser> implements UserDao{
}
