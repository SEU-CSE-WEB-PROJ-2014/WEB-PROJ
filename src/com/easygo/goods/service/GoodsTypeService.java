package com.easygo.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easygo.goods.bo.AppGoodsType;
import com.easygo.goods.dao.AppGoodsTypeDao;

@Service("goodsTypeService")						//括号内是对象名称
public class GoodsTypeService {
	@Autowired
	private AppGoodsTypeDao appGoodsTypeDao;
	
	public boolean AddAppGoodsType(String GoodsTypeName)
	{
		AppGoodsType goodsType = new AppGoodsType();
		goodsType.setTypeName(GoodsTypeName);
		this.appGoodsTypeDao.save(goodsType);
		
		return true;
	}
	
	public boolean DeleteAppGoodsType(int GoodsTypeID)
	{
		//1
		Map params = new HashMap<Integer, Object>();
		params.put("typeID", GoodsTypeID);
		this.appGoodsTypeDao.bulkUpdate("delete from AppGoodsType g where g.goodsTypeId = :goodsTypeId", params);
		
		/*2
		List<AppGoodsType> list = this.appGoodsTypeDao.findByParams("", params);
		for(AppGoodsType type : list){
			this.appGoodsTypeDao.delete(type);
		}*/
		
		return true;
	}
	
	public AppGoodsType[] SearchAppGoodsType(String GoodsTypeName)
	{
		Map params = new HashMap<String, Object>();
		params.put("typeName", GoodsTypeName);
		List<AppGoodsType> list = (List<AppGoodsType>) this.appGoodsTypeDao.findByParams("select from AppGoodsType g where g.typeName = :typeName", params);
		
		return list.toArray(new AppGoodsType[]{});
	}
	
	public boolean EditAppGoodsType(int GoodsTypeID, String TargetGoodsTypeName)
	{
		AppGoodsType goodsType = this.appGoodsTypeDao.get(GoodsTypeID);
		goodsType.setTypeName(TargetGoodsTypeName);
		this.appGoodsTypeDao.update(goodsType);
		
		return true;
	}
}
