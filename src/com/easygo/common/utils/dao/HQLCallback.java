package com.easygo.common.utils.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

/**
 * HibernateCallback实现类
 * @author jljia
 */
class HQLCallback<T> implements HibernateCallback<T>{
	private String hql = null;
	private Map<String, ?> params = null;
	private boolean doUpdate = false;
	private HibernateTemplate template = null;
	
	protected void prepareQuery(Query queryObject){
		if (this.template.isCacheQueries())
		{
			queryObject.setCacheable(true);
			if (this.template.getQueryCacheRegion() != null)
			{
				queryObject.setCacheRegion(this.template.getQueryCacheRegion());
			}
		}
		if (this.template.getFetchSize() > 0)
		{
			queryObject.setFetchSize(this.template.getFetchSize());
		}
		if (this.template.getMaxResults() > 0)
		{
			queryObject.setMaxResults(this.template.getMaxResults());
		}
		SessionFactoryUtils.applyTransactionTimeout(
				queryObject, this.template.getSessionFactory());
	}
	
	/**
	 * 设置查询参数
	 */
	private void setQueryObjectParams(Query queryObject, Map.Entry<String, ?> entry){
		if (entry.getValue() instanceof Object[]) {
			queryObject.setParameterList(entry.getKey(),
					(Object[]) entry.getValue());
		} else if (entry.getValue() instanceof Collection<?>) {
			queryObject.setParameterList(entry.getKey(),
					(Collection) entry.getValue());
		} else {
			queryObject.setParameter(entry.getKey(),
					entry.getValue());
		}
	}
	
	public HQLCallback(String hql, Map<String, ?> params,
			boolean doUpdate, HibernateTemplate template) {
		this.hql = hql;
		this.params = params;
		this.doUpdate = doUpdate;
		this.template = template;
	}

	public T doInHibernate(Session session)
			throws HibernateException, SQLException {
		Query queryObject = session.createQuery(hql);
		this.prepareQuery(queryObject);
		
		if (params != null && !params.isEmpty()) {
			Iterator it = params.entrySet().iterator();
			for (int i = 0; i < params.size(); i++) {
				Map.Entry<String, ?> entry = (Entry<String, ?>) it
						.next();
				this.setQueryObjectParams(queryObject, entry);
			}
		}
		
		if(this.doUpdate){
			return (T) new Integer(queryObject.executeUpdate());
		}else{
			return (T) queryObject.list();
		}
	}
}