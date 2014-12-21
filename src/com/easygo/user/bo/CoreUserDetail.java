package com.easygo.user.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * CoreUserDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "core_user_detail")
public class CoreUserDetail implements java.io.Serializable {

	// Fields

	private String userDetailId;
	private String userId;
	private String email;
	private String address;

	// Constructors

	/** default constructor */
	public CoreUserDetail() {
	}

	/** full constructor */
	public CoreUserDetail(String userId, String email, String address) {
		this.userId = userId;
		this.email = email;
		this.address = address;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "user_detail_id", unique = true, nullable = false, length = 50)
	public String getUserDetailId() {
		return this.userDetailId;
	}

	public void setUserDetailId(String userDetailId) {
		this.userDetailId = userDetailId;
	}

	@Column(name = "user_id", length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "email", length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", length = 65535)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}