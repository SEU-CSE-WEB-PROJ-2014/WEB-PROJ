package com.easygo.common.utils.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * 供hibernate使用的通用dao.
 * 适用于处理单主键的bo对象.
 * K为主键的类型, T为bo对象的类型.
 */
public interface HibernateDao<K extends Serializable, T>{
	public T get(K key);
	
	public T load(K key);
	
	public void save(T bean);
	
	public void update(T bean);
	
	public void saveOrUpdate(T bean);
	
	public void delete(T bean);
	
	public void flush();
	
	public List<?> findByParams(String hql, Map<String, Object> params);

}
