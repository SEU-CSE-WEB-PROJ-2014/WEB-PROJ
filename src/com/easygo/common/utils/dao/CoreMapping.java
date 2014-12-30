package com.easygo.common.utils.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * CoreMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "core_mapping", catalog = "easygo")
@SqlResultSetMappings({
	@SqlResultSetMapping(name="sqlMapping")
})
public class CoreMapping implements java.io.Serializable {

	// Fields

	private Integer id;

	// Constructors

	/** default constructor */
	public CoreMapping() {
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}