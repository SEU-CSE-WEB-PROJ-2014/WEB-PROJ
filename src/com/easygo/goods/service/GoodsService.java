package com.easygo.goods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easygo.goods.bo.AppGoods;
import com.easygo.goods.dao.AppGoodsDao;

@Service("goodsService")
public class GoodsService {
	@Autowired
	private AppGoodsDao appGoodsDao;
	
	public boolean addAppGoods(String goodsName)
	{
		AppGoods goods = new AppGoods();
		return false;
	}
}
