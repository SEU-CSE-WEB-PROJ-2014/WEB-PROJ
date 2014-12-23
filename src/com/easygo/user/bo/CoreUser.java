package com.easygo.user.bo;


import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mysql.jdbc.Field;

/**
 * CoreUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="core_user")
@NamedQueries({
	@NamedQuery(name="CoreUser.listAll", query="select u, u.userId from CoreUser u")
})

@NamedNativeQueries({
	@NamedNativeQuery(name="CoreUser.nListAll", query="select u.user_id as id, u.* from core_user u", resultSetMapping="listAllMapping")
})

@SqlResultSetMappings({
	@SqlResultSetMapping(name="listAllMapping", entities={
			@EntityResult(entityClass=CoreUser.class)
	}, columns={
			@ColumnResult(name="id")
	})
})

public class CoreUser implements java.io.Serializable {

	// Fields

	private String userId;
	private String loginName;
	private String nickName;
	private String password;
	private Integer state;
	private Integer sex;

	// Constructors

	/** default constructor */
	public CoreUser() {
	}

	/** full constructor */
	public CoreUser(String loginName, String nickName, String password,
			Integer state, Integer sex) {
		this.loginName = loginName;
		this.nickName = nickName;
		this.password = password;
		this.state = state;
		this.sex = sex;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "user_id", unique = true, nullable = false, length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "login_name", length = 50)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "nick_name", length = 100)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "password", length = 128)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

}