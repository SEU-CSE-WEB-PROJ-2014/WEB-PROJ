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
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.easygo.common.utils.BusinessException;
import com.easygo.common.utils.dao.QueryResult;
import com.easygo.common.utils.userManager.LoginUser;
import com.easygo.common.utils.userManager.UserManager;
import com.easygo.user.bo.CoreUser;
import com.easygo.user.bo.CoreUserDetail;
import com.easygo.user.dao.UserDao;
import com.easygo.user.dao.UserDetailDao;


@Service("userService")
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDetailDao userDetailDao;
	
	
	public String regUser(String nickName, String password, String email){
		CoreUser user = new CoreUser();
		user.setNickName(nickName);
		user.setLoginName(email);
		user.setPassword(DigestUtils.md5Hex(password));
		user.setState(CoreUser.STATE_YES);
		
		this.userDao.save(user);
		
		CoreUserDetail userDetail = new CoreUserDetail();
		userDetail.setEmail(email);
		userDetail.setUserId(user.getUserId());
		this.userDetailDao.save(userDetail);
		return user.getUserId();
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
			params.clear();
			params.put("userId", user.getUserId());
			List<CoreUserDetail> userDetail = this.userDetailDao.eqQueryByParams(params);
			if(userDetail == null || userDetail.size() < 1){
				throw new BusinessException("用户数据不正确，请联系管理员");
			}

			//登录用户数据放入session
			LoginUser loginUser = new LoginUser();
			BeanUtils.copyProperties(loginUser, user);
			BeanUtils.copyProperties(loginUser, userDetail.get(0));
			
			
			HttpSession s = request.getSession();
			s.setAttribute(UserManager.LOGIN_USER_KEY, loginUser);
			
			UserManager.setCurrentUser(loginUser);
		}else{
			throw new BusinessException("用户名或密码错误");
		}
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
	
	
	public void listUserInfo(){
//		QueryResult<CoreUser> qr = 
//				this.userDao.doQuery("CoreUser.nListAll", null, Map.class);
		userDao.testNamedQuery();
		userDao.testNamedNativeQuery();
		return;
	}
		
	
}
