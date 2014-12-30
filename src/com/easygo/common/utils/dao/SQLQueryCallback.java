package com.easygo.common.utils.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.util.Assert;

class SQLQueryCallback<T> implements HibernateCallback<T>{
	private String query = null;
	private HibernateTemplate template = null;
	private Map<String, Object> params = null;
	
	public SQLQueryCallback(String query, HibernateTemplate template, 
			Map<String, Object> params) {
		Assert.notNull(query);
		Assert.notNull(template);
		
		this.query = query;
		this.template = template;
		this.params = params;
	}

	
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
	
	
	public T doInHibernate(Session s) throws HibernateException,
			SQLException {
		Query queryObject = s.createSQLQuery(query);
		this.prepareQuery(queryObject);
		
		//以map返回数据
		queryObject.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		//查询参数
		if (params != null && !params.isEmpty()) {
			Iterator it = params.entrySet().iterator();
			for (int i = 0; i < params.size(); i++) {
				Map.Entry<String, ?> entry = (Entry<String, ?>) it
						.next();
				this.setQueryObjectParams(queryObject, entry);
			}
		}
		
		
		return (T) queryObject.list();
	}
	
}