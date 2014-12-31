package com.easygo.common.utils.userManager;

import java.util.Map;

public class LoginUser {
	
	/**
	 * 身份标志:管理员
	 */
	public static final String ROLE_ID_ADMIN = "ID000";
	/**
	 * 身份标志:商品管理员
	 */
	public static final String ROLE_ID_GOODS_ADMIN = "ID001";
	/**
	 * 身份标志:网购用户
	 */
	public static final String ROLE_ID_USERS = "ID002";
	/**
	 * 身份标志:订单管理员
	 */
	public static final String ROLE_ID_ORDER_ADMIN = "ID003";
	
	
	private String userId;
	private String loginName;
	private String nickName;
	private Integer state;
	private Integer sex;
	private String userDetailId;
	private String email;
	private String address;
	private String roleId;
	
	public LoginUser() {
	}
	
	public LoginUser(Map<String, Object> params) {
		if(params.get("userId") != null && params.get("userId") instanceof String){
			this.userId = (String) params.get("userId");
		}
	}
	
	

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getUserDetailId() {
		return userDetailId;
	}

	public void setUserDetailId(String userDetailId) {
		this.userDetailId = userDetailId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
