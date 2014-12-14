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
	/**
	 * 根据key值获取bo对象
	 */
	public T get(K key);

	/**
	 * 根据key值获取bo对象（延迟加载）
	 */
	public T load(K key);

	/**
	 * 保存bo
	 */
	public void save(T bean);

	/**
	 * 更新bo
	 */
	public void update(T bean);

	/**
	 * 更新或保存bo
	 */
	public void saveOrUpdate(T bean);
	
	/**
	 * 删除
	 */
	public void delete(T bean);
	
	/**
	 * 刷新当前session
	 */
	public void flush();
	
	/**
	 * hql获取列表
	 */
	public List<?> findByParams(String hql, Map<String, Object> params);
	
	/**
	 * 全字段可选等查询
	 * 把查询的等条件(bo.filed_1="value")放入map（map.put("filed_1", "value")），
	 * 返回表中符合filed_1="value"的数据列表
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 */
	public List<T> eqQueryByParams(Map<String, Object> params) throws SecurityException, NoSuchFieldException;

}
