package com.easygo.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			Platform.invoke("goodsService", "addOrEditAppGoods", String.class,new Object[]{goodsId,null,null,newQuantity,null,null});
			
			Double totalPrice=(Double)result.getResultList().get(0).get("price")*quantity;
			
			Timestamp createTime=new Timestamp(System.currentTimeMillis());
			
			insertOrder(goodsId,userId,newQuantity,totalPrice,createTime);
			
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
		params.put("orderId", orderId);
		params.put("payState", 1);
		Timestamp payTime=new Timestamp(System.currentTimeMillis());
		params.put("payTime", payTime);
		this.appOrderDao.bulkUpdate("update AppOrder order set order.payState = :payState , order.payTime = :payTime where order.orderId = :orderId", params);
		
	}
	
	
	public void deliverGoods(String orderId){
		setOrderTransState(orderId,1);
		Timestamp transTime=new Timestamp(System.currentTimeMillis());
		Map params=new HashMap<String,Object>();
		params.put("orderId", orderId);
		params.put("transTime", transTime);
		this.appOrderDao.bulkUpdate("update AppOrder order set order.transTime = :transTime where order.orderId = :orderId", params);
	}
	
	public void signOrder(String orderId){
		Timestamp signTime=new Timestamp(System.currentTimeMillis());
		Map params=new HashMap<String,Object>();
		params.put("orderId", orderId);
		params.put("signState", 1);
		params.put("signTime", signTime);
		this.appOrderDao.bulkUpdate("update AppOrder order set order.signState = :signState, order.signTime = :signTime where order.orderId = :orderId", params);
		
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
		String sql = "select * from app_order order where 1=1";
		Map params = new HashMap<Integer, Object>();
		
		if(payState != null){
			sql += " and order.pay_state = :payState";
			params.put("payState", payState);
		}
		if(transState != null){
			sql += " and order.trans_state = :transState";
			params.put("transState", transState);
		}
		if(signState != null){
			sql += " and order.sign_state = :signState";
			params.put("signState", signState);
		}
		
		SearchResult<Map> rs = this.appOrderDao.doSQLSearch(sql, params, pageSize, pageNum);
		return rs;
	}
}

 
   
