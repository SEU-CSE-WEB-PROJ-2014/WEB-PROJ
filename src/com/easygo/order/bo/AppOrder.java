package com.easygo.order.bo;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * AppOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_order", catalog = "easygo")
public class AppOrder implements java.io.Serializable {

	// Fields

	private String orderId;
	private String goodsId;
	private String userId;
	private Integer quantity;
	private Double totalPrice;
	private Integer state;
	private Integer payState;
	private Integer transState;
	private Date createTime;
	private Date payTime;
	private Date transTime;
	private Date signTime;
	private Integer signState;
	private String invoiceNum;

	// Constructors

	/** default constructor */
	public AppOrder() {
	}

	/** minimal constructor */
	public AppOrder(String goodsId, String userId, Integer quantity,
			Double totalPrice) {
		this.goodsId = goodsId;
		this.userId = userId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	/** full constructor */
	public AppOrder(String goodsId, String userId, Integer quantity,
			Double totalPrice, Integer state, Integer payState,
			Integer transState, Timestamp createTime, Timestamp payTime,
			Timestamp transTime, Timestamp signTime, Integer signState) {
		this.goodsId = goodsId;
		this.userId = userId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.state = state;
		this.payState = payState;
		this.transState = transState;
		this.createTime = createTime;
		this.payTime = payTime;
		this.transTime = transTime;
		this.signTime = signTime;
		this.signState = signState;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "order_id", unique = true, nullable = false, length = 50)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "goods_id", nullable = false, length = 50)
	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "user_id", nullable = false, length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "total_price", nullable = false, precision = 22, scale = 0)
	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "pay_state")
	public Integer getPayState() {
		return this.payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	@Column(name = "trans_state")
	public Integer getTransState() {
		return this.transState;
	}

	public void setTransState(Integer transState) {
		this.transState = transState;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "pay_time", length = 19)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "trans_time", length = 19)
	public Date getTransTime() {
		return this.transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	@Column(name = "sign_time", length = 19)
	public Date getSignTime() {
		return this.signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	@Column(name = "sign_state")
	public Integer getSignState() {
		return this.signState;
	}

	public void setSignState(Integer signState) {
		this.signState = signState;
	}
	
	@Column(name = "invoice_num", length = 50)
	public String getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	
}