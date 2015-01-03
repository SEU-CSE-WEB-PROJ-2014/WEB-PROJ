package com.easygo.user.service;



import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.easygo.common.utils.BusinessException;
import com.easygo.common.utils.dao.SearchResult;
import com.easygo.common.utils.platform.Platform;
import com.easygo.common.utils.userManager.LoginUser;
import com.easygo.common.utils.userManager.UserManager;
import com.easygo.goods.bo.AppGoods;
import com.easygo.user.bo.CoreUser;
import com.easygo.user.dao.UserDao;


@Service("userService")
public class UserService {
	@Autowired
	private UserDao userDao;
	
	
	public void addOrEditUser(String userId, String loginName, String nickName, String password, Integer sex, String roleId, String email, String address){
		 CoreUser user = null;
		if(userId != null){				//typeId不为空，表示编辑
			user = this.userDao.get(userId);	//通过dao层获取数据库层的bo对象
			if(user == null){
				throw new BusinessException("用户不存在，userId：" + userId);
			}
		}else{									//typeId为空，表示新增
			user = new CoreUser();
		}
		user.setLoginName(loginName);
		user.setNickName(nickName);
		user.setPassword(DigestUtils.md5Hex(password));
		user.setState(CoreUser.STATE_YES);
		user.setSex(sex);
		user.setRoleId(roleId);
		user.setEmail(email);
		user.setAddress(address);
		
		this.userDao.saveOrUpdate(user);
	}
	
	public void login(String loginName, String password, 
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		Assert.notNull(loginName);
		Assert.notNull(password);
		Assert.notNull(request);
		Assert.notNull(response);
		
		Map params = new HashMap<String, Object>();
		params.put("loginName", loginName);
		params.put("state", CoreUser.STATE_YES);
		params.put("password", DigestUtils.md5Hex(password));
		List<CoreUser> userList = this.userDao.eqQueryByParams(params);
		
		if(userList != null && userList.size() > 0){	//密码验证成功
			CoreUser user = userList.get(0);

			//登录用户数据放入session
			LoginUser loginUser = new LoginUser();
			BeanUtils.copyProperties(loginUser, user);
			if(StringUtils.isNotEmpty(loginUser.getRoleId())){
				Map[] backstageUrlInfo = Platform.invoke("roleService", "getRoleManUrlInfo", Map[].class, new Object[]{loginUser.getRoleId()});
				if(ArrayUtils.isNotEmpty(backstageUrlInfo)){
					loginUser.setHasBackstageAuth(true);
				}
			}
			
			HttpSession s = request.getSession();
			s.setAttribute(UserManager.LOGIN_USER_KEY, loginUser);
			
			UserManager.setCurrentUser(loginUser);
		}else{
			throw new BusinessException("用户名或密码错误");
		}
	}
	
	public SearchResult<Map> myOrder(String userId, Integer pageSize, Integer pageNum){
		Map params = new HashMap<String, Object>();
		String sql="select * from app_goods g join app_order gt on g.goods_id = gt.goods_id where gt.user_id = :userId";
		params.put("userId", userId);
		SearchResult<Map> rs = this.userDao.doSQLSearch(sql, params, pageSize, pageNum);
		return rs;
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response){
		Assert.notNull(request);
		Assert.notNull(response);
		
		HttpSession s = request.getSession(false);
		if(s == null){
			throw new BusinessException("无登录用户");
		}
		
		LoginUser loginUser = (LoginUser) s.getAttribute(UserManager.LOGIN_USER_KEY);
		if(loginUser == null){
			throw new BusinessException("无登录用户");
		}
		
		s.removeAttribute(UserManager.LOGIN_USER_KEY);
		UserManager.setCurrentUser(null);
	}

	public void batchDelUser(String[] userIds){
		Map params = new HashMap<String, Object>();
		params.put("userIds", userIds);
		this.userDao.bulkUpdate("update CoreUser u set u.state = 0 where u.userId in (:userIds)", params);
	}
	
	public SearchResult<Map> searchUser(String name, String roleId, String email, Integer pageSize, Integer pageNum){
		Map params = new HashMap<String, Object>();
		String sql = "select * from core_user u where 1=1";
		
		if(StringUtils.isNotEmpty(name)){
			sql += " and (u.login_name like '%:name%' or u.nick_name like '%:name%')";
			params.put("name", name);
		}
		if(StringUtils.isNotEmpty(roleId)){
			sql += " and u.role_id = :roleId";
			params.put("roleId", roleId);
		}
		if(StringUtils.isNotEmpty(email)){
			sql += "and u.email = :email";
			params.put("email", email);
		}
		
		SearchResult<Map> rs = this.userDao.doSQLSearch(sql, params, pageSize, pageNum);
		return rs;
	}
}
