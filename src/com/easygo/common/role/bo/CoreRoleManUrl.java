package com.easygo.common.role.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CoreRoleManUrl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "core_role_man_url", catalog = "easygo")
public class CoreRoleManUrl implements java.io.Serializable {

	// Fields

	private Integer roleManUrlId;
	private String roleId;
	private String url;
	private String urlName;

	// Constructors

	/** default constructor */
	public CoreRoleManUrl() {
	}

	/** full constructor */
	public CoreRoleManUrl(String roleId, String url, String urlName) {
		this.roleId = roleId;
		this.url = url;
		this.urlName = urlName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "role_man_url_id", unique = true, nullable = false)
	public Integer getRoleManUrlId() {
		return this.roleManUrlId;
	}

	public void setRoleManUrlId(Integer roleManUrlId) {
		this.roleManUrlId = roleManUrlId;
	}

	@Column(name = "role_id", nullable = false, length = 45)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "url", nullable = false)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "url_name", nullable = false, length = 50)
	public String getUrlName() {
		return this.urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

}