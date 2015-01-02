package com.easygo.order.dao.impl;

import org.springframework.stereotype.Repository;

import com.easygo.common.utils.dao.HibernateDaoImpl;
import com.easygo.order.bo.AppOrder;
import com.easygo.order.dao.AppOrderDao;

@Repository("AppOrderDao")
public class AppOrderDaoImpl extends HibernateDaoImpl<String, AppOrder> implements AppOrderDao{
}
