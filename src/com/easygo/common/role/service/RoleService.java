package com.easygo.common.role.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.easygo.common.role.dao.CoreRoleDao;
import com.easygo.common.role.dao.CoreRoleManUrlDao;
import com.easygo.common.utils.dao.QueryResult;

@Service("roleService")
public class RoleService {
	@Autowired
	private CoreRoleDao coreRoleDao;
	
	@Autowired
	private CoreRoleManUrlDao coreRoleManUrlDao;
	
	public Map[] getRoleManUrlInfo(String roleId){
		Assert.notNull(roleId);
		Map params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		QueryResult<Map> qr = coreRoleDao.doNamedSQLQuery("CoreRole.QueryRoleManUrlInfo", params);
		return qr.toArray();
	}
	
}
