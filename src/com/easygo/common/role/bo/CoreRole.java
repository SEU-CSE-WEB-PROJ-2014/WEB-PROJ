package com.easygo.common.role.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * CoreRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "core_role", catalog = "easygo")
@NamedNativeQueries({
	@NamedNativeQuery(name="CoreRole.QueryRoleManUrlInfo", 
			query="select * from core_role r" +
					" inner join core_role_man_url mu on r.role_id = mu.role_id and r.state = 1" +
					" where r.role_id = :roleId", resultSetMapping="sqlMapping")
})
public class CoreRole implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleName;
	private String rolePinyinName;
	private String roleDescription;
	private Integer state;

	// Constructors

	/** default constructor */
	public CoreRole() {
	}

	/** full constructor */
	public CoreRole(String roleName, String rolePinyinName,
			String roleDescription, Integer state) {
		this.roleName = roleName;
		this.rolePinyinName = rolePinyinName;
		this.roleDescription = roleDescription;
		this.state = state;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "role_id", unique = true, nullable = false, length = 50)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_name", length = 50)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "role_pinyin_name", length = 100)
	public String getRolePinyinName() {
		return this.rolePinyinName;
	}

	public void setRolePinyinName(String rolePinyinName) {
		this.rolePinyinName = rolePinyinName;
	}

	@Column(name = "role_description", length = 200)
	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}