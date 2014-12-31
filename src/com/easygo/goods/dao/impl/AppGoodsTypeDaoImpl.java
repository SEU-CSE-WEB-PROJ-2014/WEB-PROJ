package com.easygo.goods.dao.impl;

import org.springframework.stereotype.Repository;

import com.easygo.common.utils.dao.HibernateDaoImpl;
import com.easygo.goods.bo.AppGoodsType;
import com.easygo.goods.dao.AppGoodsTypeDao;

@Repository("AppGoodsTypeDao")
public class AppGoodsTypeDaoImpl extends HibernateDaoImpl<Integer, AppGoodsType> implements AppGoodsTypeDao{
}
