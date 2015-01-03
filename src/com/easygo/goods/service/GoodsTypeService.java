package com.easygo.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.easygo.common.utils.BusinessException;
import com.easygo.common.utils.dao.SearchResult;
import com.easygo.goods.bo.AppGoodsType;
import com.easygo.goods.dao.AppGoodsTypeDao;

@Service("goodsTypeService")						//括号内是对象名称
public class GoodsTypeService {
	@Autowired
	private AppGoodsTypeDao appGoodsTypeDao;
	
	public void AddOrEditAppGoodsType(Integer GoodsTypeID, String GoodsTypeName, String typeIntro)
	{
		AppGoodsType goodsType = null;
		if(GoodsTypeID != null){				//typeId不为空，表示编辑
			goodsType = this.appGoodsTypeDao.get(GoodsTypeID);	//通过dao层获取数据库层的bo对象
			if(goodsType == null){
				throw new BusinessException("商品类型不存在，typeId：" + GoodsTypeID);
			}
		}else{									//typeId为空，表示新增
			goodsType = new AppGoodsType();
		}
		//设置type的字段值
		goodsType.setTypeName(GoodsTypeName);
		goodsType.setTypeIntro(typeIntro);
		
		//保存或更新
		this.appGoodsTypeDao.saveOrUpdate(goodsType);
	}
	
	public void DeleteAppGoodsType(Integer[] GoodsTypeID)
	{
		//1
		Map params = new HashMap<Integer, Object>();
		params.put("typeID", GoodsTypeID);
		this.appGoodsTypeDao.bulkUpdate("delete from AppGoodsType g where g.goodsTypeId in (:typeID)", params);
		
		/*2
		List<AppGoodsType> list = this.appGoodsTypeDao.findByParams("", params);
		for(AppGoodsType type : list){
			this.appGoodsTypeDao.delete(type);
		}*/
	}
	
	public SearchResult<Map> SearchAppGoodsType(String GoodsTypeName,Integer pageSize, Integer pageNum)
	{
		Map params = new HashMap<String, Object>();
		
		String sql = "select * from app_goods_type gt where 1=1";
		
		if(StringUtils.isNotEmpty(GoodsTypeName)){
			sql += " and gt.type_name like '%:GoodsTypeName%'";
			params.put("typeName", GoodsTypeName);
		}
		SearchResult<Map> rs = this.appGoodsTypeDao.doSQLSearch(sql, params, pageSize, pageNum);
		return rs;
	}
	
	public AppGoodsType getGoodsType(Integer typeId){
		Assert.notNull(typeId);
		return this.appGoodsTypeDao.get(typeId);
	}
}
