package com.easygo.goods.dao;

import com.easygo.common.utils.dao.HibernateDao;
import com.easygo.goods.bo.AppGoodsType;

public interface AppGoodsTypeDao extends HibernateDao<Integer, AppGoodsType>{				//HibernateDao<Integer, AppGoodsType>第一个参数是主键类型，第二个参数是表格对应的类型名称

}
