package com.easygo.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easygo.common.utils.dao.SearchResult;
import com.easygo.order.bo.AppOrder;
import com.easygo.order.dao.AppOrderDao;

@Service("appOrderService")
public class AppOrderService {
	@Autowired
	private AppOrderDao appOrderDao;
	
	public void setOrderTransState(String orderId, Integer transState){
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
			sql += " and order.pay_state = :pay_state";
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
