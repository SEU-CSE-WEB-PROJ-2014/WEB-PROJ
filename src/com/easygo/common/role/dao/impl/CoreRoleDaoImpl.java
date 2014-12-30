package com.easygo.common.role.dao.impl;

import org.springframework.stereotype.Repository;

import com.easygo.common.role.bo.CoreRole;
import com.easygo.common.role.dao.CoreRoleDao;
import com.easygo.common.utils.dao.HibernateDaoImpl;

@Repository("coreRoleDao")
public class CoreRoleDaoImpl extends HibernateDaoImpl<String, CoreRole> implements CoreRoleDao{

}
