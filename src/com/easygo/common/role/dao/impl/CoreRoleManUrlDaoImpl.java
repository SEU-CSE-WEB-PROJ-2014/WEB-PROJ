package com.easygo.common.role.dao.impl;

import org.springframework.stereotype.Repository;

import com.easygo.common.role.bo.CoreRoleManUrl;
import com.easygo.common.role.dao.CoreRoleManUrlDao;
import com.easygo.common.utils.dao.HibernateDaoImpl;

@Repository("coreRoleManUrl")
public class CoreRoleManUrlDaoImpl extends HibernateDaoImpl<Integer, CoreRoleManUrl>
implements CoreRoleManUrlDao{

}
