package com.easygo.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.easygo.common.utils.dao.HibernateDao;
import com.easygo.common.utils.dao.HibernateDaoImpl;
import com.easygo.user.bo.CoreUserDetail;
import com.easygo.user.dao.UserDetailDao;

@Repository("userDetailDao")
public class UserDetailDaoImpl extends HibernateDaoImpl<String, CoreUserDetail> 
implements UserDetailDao{

}
