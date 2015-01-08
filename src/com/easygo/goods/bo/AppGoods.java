package com.easygo.goods.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * AppGoods entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_goods", catalog = "easygo")
@NamedNativeQueries({
	@NamedNativeQuery(name="AppGoods.goodsSearch", query="select g.*, gt.type_name, gt.type_intro from app_goods g left join app_goods_type gt on g.goods_type_id=gt.goods_type_id where 1=1 and g.state=1 ", resultSetMapping="sqlMapping"),
	@NamedNativeQuery(name="AppGoods.goodsInfoFormat", query="select * from app_goods g where g.goods_id in (:ids)", resultSetMapping="sqlMapping")
})
public class AppGoods implements java.io.Serializable {
	
	public static final Integer STATE_YES = 1;
	public static final Integer STATE_NO = 0;
	
	// Fields

	private String goodsId;
	private String goodsName;
	private Double price;
	private Integer quantity;
	private String description;
	private Integer state;
	private Integer goodsTypeId;

	// Constructors

	/** default constructor */
	public AppGoods() {
	}

	/** full constructor */
	public AppGoods(String goodsName, Double price, Integer quantity,
			String description, Integer state, Integer goodsTypeId) {
		this.goodsName = goodsName;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.state = state;
		this.goodsTypeId = goodsTypeId;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "goods_id", unique = true, nullable = false, length = 50)
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "goods_name", nullable = false, length = 45)
	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "price", nullable = false, precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "state", nullable = false)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "goods_type_id", length = 50)
	public Integer getGoodsTypeId() {
		return this.goodsTypeId;
	}

	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

}