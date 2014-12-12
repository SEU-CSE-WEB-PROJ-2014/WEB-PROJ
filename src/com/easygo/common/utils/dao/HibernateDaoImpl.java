package com.easygo.common.utils.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
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
	
	@Transactional(readOnly=false)
	public List<?> findByParams(final String hql, final Map<String, Object> params){
		HibernateCallback callback = new HibernateCallback() {
			public List doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query queryObject = session.createQuery(hql);
				if(params != null && !params.isEmpty()){
					Iterator it = params.entrySet().iterator();
					for(int i = 0; i < params.size(); i++){
						Map.Entry<String, ?> entry = (Entry<String, ?>) it.next();
						if(entry.getValue() instanceof Object[]){
							queryObject.setParameterList(entry.getKey(), (Object[]) entry.getValue());
						}else if(entry.getValue() instanceof Collection<?>){
							queryObject.setParameterList(entry.getKey(), (Collection)entry.getValue());
						}else{
							queryObject.setParameter(entry.getKey(), entry.getValue());
						}
					}
				}
				return queryObject.list();
			}
		};
		
		
		return this.getHibernateTemplate().execute(callback);
	}

	
	
	
	//	public void bulkUpdate(String hql, Map params);
	
	
	

}
