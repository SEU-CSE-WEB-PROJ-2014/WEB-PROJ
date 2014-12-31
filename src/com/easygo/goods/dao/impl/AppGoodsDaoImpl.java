package com.easygo.goods.dao.impl;

import org.springframework.stereotype.Repository;

import com.easygo.common.utils.dao.HibernateDaoImpl;
import com.easygo.goods.bo.AppGoods;
import com.easygo.goods.dao.AppGoodsDao;

@Repository("AppGoodsDao")
public class AppGoodsDaoImpl extends HibernateDaoImpl<String, AppGoods> implements AppGoodsDao{

}
