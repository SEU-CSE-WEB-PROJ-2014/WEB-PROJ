package com.easygo.goods.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * AppGoodsType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_goods_type", catalog = "easygo")
public class AppGoodsType implements java.io.Serializable {

	// Fields

	private Integer goodsTypeId;
	private String typeName;
	private String typeIntro;

	// Constructors

	/** default constructor */
	public AppGoodsType() {
	}

	/** full constructor */
	public AppGoodsType(String typeName) {
		this.typeName = typeName;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "goods_type_id", unique = true, nullable = false)
	public Integer getGoodsTypeId() {
		return this.goodsTypeId;
	}

	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	@Column(name = "type_name", nullable = false, length = 100)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	
	@Column(name = "type_intro")
	public String getTypeIntro() {
		return typeIntro;
	}

	public void setTypeIntro(String typeIntro) {
		this.typeIntro = typeIntro;
	}
	
	

}