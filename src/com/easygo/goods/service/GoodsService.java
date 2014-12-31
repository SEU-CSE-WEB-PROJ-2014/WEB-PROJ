package com.easygo.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easygo.common.utils.dao.QueryResult;
import com.easygo.common.utils.dao.SearchResult;
import com.easygo.goods.bo.AppGoods;
import com.easygo.goods.dao.AppGoodsDao;

@Service("goodsService")
public class GoodsService {
	@Autowired
	private AppGoodsDao appGoodsDao;
	
	public String addOrEditAppGoods(String goodsId, String goodsName, Double price, Integer quantity, String description, Integer goodsTypeId)
	{
		AppGoods goods = new AppGoods();
		goods.setGoodsName(goodsName);
		goods.setPrice(price);
		goods.setQuantity(quantity);
		goods.setDescription(description);
		goods.setState(goods.STATE_YES);
		goods.setGoodsTypeId(goodsTypeId);
		
		this.appGoodsDao.saveOrUpdate(goods);
		return goods.getGoodsId();
	}
	
	public void deleteAppGoods(String[] goodsIds)
	{
		Map params = new HashMap<String, Object>();
		params.put("goodsIds", goodsIds);
		this.appGoodsDao.bulkUpdate("update AppGoods g set g.state = 0 where g.goodsId in (:goodsIds) ", params);
//		List<AppGoods> list = (List<AppGoods>)this.appGoodsDao.findByParams("select from AppGoods g where g.goodsId = :goodsId", params);
	}
	
	public SearchResult<Map> searchAppGoods(String goodsName, Integer goodsTypeId, Double minPrice, Double maxPrice,Integer pageSize, Integer pageNum)
	{
		Map params = new HashMap<String, Object>();
		
//		String sql = "select * from app_goods g where 1=1";    动态查询
		String sql = this.appGoodsDao.getQueryString("AppGoods.goodsSearch");
		
		//判断构造动态条件
		if(StringUtils.isNotEmpty(goodsName)){
			sql += " and g.goods_name like '%:goodsName%'";
			params.put("goodsName", goodsName);
		}
		if(goodsTypeId != null){
			sql += " and g.goods_type_id = :goodsTypeId";
			params.put("goodsTypeId", goodsTypeId);
		}
		if(minPrice != null){
			sql += " and g.price >= :minPrice";
			params.put("minPrice", minPrice);
		}
		if(maxPrice != null){
			sql += " and g.price <= :maxPrice";
			params.put("maxPrice", maxPrice);
		}
		
		//查询
		SearchResult<Map> sr = this.appGoodsDao.doSQLSearch(sql, params, pageSize, pageNum);
		
		return sr;
	}
}
