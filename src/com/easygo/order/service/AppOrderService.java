package com.easygo.order.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.easygo.common.utils.dao.QueryResult;
import com.easygo.common.utils.dao.SearchResult;
import com.easygo.common.utils.platform.Platform;
import com.easygo.order.bo.AppOrder;
import com.easygo.order.dao.AppOrderDao;
import java.sql.Timestamp;

@Service("appOrderService")
public class AppOrderService {
	@Autowired
	private AppOrderDao appOrderDao;
	
	public void addOrder(String goodsId, String userId, Integer quantity){
		QueryResult<Map> result = (QueryResult<Map>)Platform.invoke("goodsService", "searchGoodsViaId", Object.class, new Object[]{goodsId});
		if((Integer)result.getResultList().get(0).get("quantity")>=quantity){
			Integer newQuantity=(Integer)result.getResultList().get(0).get("quantity")-quantity;
			
			String oldGoodsName=(String)result.getResultList().get(0).get("goods_name"); 
			
			Double oldPrice=(Double)result.getResultList().get(0).get("price");
            String oldDescription=(String)result.getResultList().get(0).get("description"); 
            Integer oldGoodsTypeId=(Integer)result.getResultList().get(0).get("goods_type_id");
			Platform.invoke("goodsService", "addOrEditAppGoods", String.class,new Object[]{goodsId,oldGoodsName,oldPrice,newQuantity,oldDescription,oldGoodsTypeId});
			
			Double totalPrice=(Double)result.getResultList().get(0).get("price")*quantity;
			
			Timestamp createTime=new Timestamp(System.currentTimeMillis());
			
			insertOrder(goodsId,userId,quantity,totalPrice,createTime);
			
		}
	}
	
	public void insertOrder(String goodsId,String userId, Integer quantity, Double price,Timestamp createTime ){
		AppOrder order=new AppOrder();
		order.setGoodsId(goodsId);
		order.setPayState(0);
		order.setSignState(0);
		order.setUserId(userId);
		order.setTotalPrice(price);
		order.setState(1);
		order.setQuantity(quantity);
		order.setCreateTime(createTime);
		order.setPayTime(null);
		order.setSignState(0);
		order.setSignTime(null);
		order.setTransState(0);
		order.setTransTime(null);
		
		this.appOrderDao.save(order);
		
	}
	
	public void payOrder(String orderId){//存疑/////////////////////////
		Map params = new HashMap<String,Object>();
		AppOrder order = this.appOrderDao.get(orderId);
		order.setPayState(1);
		order.setPayTime(new Date());
		this.appOrderDao.update(order);
	}
	
	
	public void deliverGoods(String orderId, String invoiceNum){
		Assert.notNull(orderId);
		Assert.notNull(invoiceNum);
		AppOrder order = this.appOrderDao.get(orderId);
		order.setTransTime(new Date());
		order.setTransState(1);
		order.setInvoiceNum(invoiceNum);
		this.appOrderDao.update(order);
	}
	
	public void signOrder(String orderId){
		AppOrder order = this.appOrderDao.get(orderId);
		order.setSignState(1);
		order.setSignTime(new Date());
		this.appOrderDao.update(order);
	}
	
	
	public void setOrderTransState(String orderId, Integer transState){
		//Map[] result = Platform.invoke("goodsService", "searchAppGoods", Map[].class, new Object(){});	
		Map params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		params.put("transState", transState);
		this.appOrderDao.bulkUpdate("update AppOrder order set order.transState = :transState where order.orderId = :orderId", params);
	}
	
	public void batchCancelOrder(String[] orders){
		Map params = new HashMap<String, Object>();
		params.put("orders", orders);
		this.appOrderDao.bulkUpdate("update AppOrder order set order.state = 0 where order.orderId in (:orders)", params);
	}
	
	public SearchResult<Map> searchAppOrder(Integer payState, Integer transState, Integer signState, Integer pageSize, Integer pageNum){
		String sql = "select o.*, g.goods_name, g.price, u.nick_name, u.address from app_order o inner join app_goods g on g.goods_id = o.goods_id inner join core_user u on u.user_id = o.user_id where 1=1";
		Map params = new HashMap<Integer, Object>();
		
		if(payState != null){
			sql += " and o.pay_state = :payState";
			params.put("payState", payState);
		}
		if(transState != null){
			sql += " and o.trans_state = :transState";
			params.put("transState", transState);
		}
		if(signState != null){
			sql += " and o.sign_state = :signState";
			params.put("signState", signState);
		}
		
		SearchResult<Map> rs = this.appOrderDao.doSQLSearch(sql, params, pageSize, pageNum);
		return rs;
	}
	public SearchResult<Map> searchMyOrder(String userId, Integer payState, Integer transState, Integer signState, Integer pageSize, Integer pageNum){
		String sql = "select g.goods_name, g.description, o.*, gt.type_name, gt.type_intro from app_goods g inner join app_order o on o.goods_id =  g.goods_id inner join app_goods_type gt on gt.goods_type_id = g.goods_type_id where o.user_id = :userId";
		Map params = new HashMap<Integer, Object>();
		params.put("userId", userId);
		
		if(payState != null){
			sql += " and o.pay_state = :payState";
			params.put("payState", payState);
		}
		if(transState != null){
			sql += " and o.trans_state = :transState";
			params.put("transState", transState);
		}
		if(signState != null){
			sql += " and o.sign_state = :signState";
			params.put("signState", signState);
		}
		
		sql += " order by o.create_time";
		SearchResult<Map> rs = this.appOrderDao.doSQLSearch(sql, params, pageSize, pageNum);
		return rs;
	}
	
}

 
   
