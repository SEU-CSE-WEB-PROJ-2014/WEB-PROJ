package com.easygo.common.role.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.easygo.common.role.dao.CoreRoleDao;
import com.easygo.common.role.dao.CoreRoleManUrlDao;

@Service("roleService")
public class RoleService {
	@Autowired
	private CoreRoleDao coreRoleDao;
	
	@Autowired
	private CoreRoleManUrlDao coreRoleManUrlDao;
	
	public Map[] getRoleManUrlInfo(String roleId){
		Assert.notNull(roleId);
//		coreRoleDao.doQuery(queryName, params, type)
		
		
		return null;
	}
	
}
