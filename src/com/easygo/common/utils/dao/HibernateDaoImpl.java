package com.easygo.common.utils.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * 供hibernate使用的通用dao.
 * 适用于处理单主键的bo对象.
 * K为主键的类型, T为bo对象的类型.
 */
public class HibernateDaoImpl<K extends Serializable, T> extends HibernateDaoSupport implements HibernateDao<K, T>{
	//设置dao的sessionFactory
	@Resource
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}
	
	/**
	 * 实体类类型
	 */
	private final Class<T> typeClass;
	
	@SuppressWarnings("unchecked")
	public HibernateDaoImpl() {
		Type type = this.getClass().getGenericSuperclass();
		this.typeClass = (Class<T>) ((ParameterizedType)type).getActualTypeArguments()[1];
	}
	
	@Transactional(readOnly=true)
	public T get(K key){
		return this.getHibernateTemplate().get(typeClass, key);
	}
	
	@Transactional(readOnly=true)
	public T load(K key){
		return this.getHibernateTemplate().load(typeClass, key);
	}
	
	@Transactional(readOnly=false)
	public void save(T bean){
		this.getHibernateTemplate().save(bean);
	}
	
	@Transactional(readOnly=false)
	public void update(T bean){
		this.getHibernateTemplate().update(bean);
	}
	
	@Transactional(readOnly=false)
	public void saveOrUpdate(T bean){
		this.getHibernateTemplate().saveOrUpdate(bean);
	}
	
	@Transactional(readOnly=false)
	public void delete(T bean){
		this.getHibernateTemplate().delete(bean);
	}
	
	public void flush(){
		this.getHibernateTemplate().flush();
	}
//	
//	public void findByParam(String hql, Map params);
//	
//	public void bulkUpdate(String hql, Map params);
	
	
	

}
