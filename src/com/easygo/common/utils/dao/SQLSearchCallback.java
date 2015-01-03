package com.easygo.common.utils.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.util.Assert;

import com.easygo.common.utils.BusinessException;

public class SQLSearchCallback<T> implements HibernateCallback<SearchResult<T>> {
	private static final Integer DEFAULT_PAGE_SIZE = 20;
	private String query = null;
	private HibernateTemplate template = null;
	private Map<String, Object> params = null;
	private Class<T> type = null;
	private Integer pageSize = null;
	private Integer pageNum = null;
	
	public SQLSearchCallback(String query, HibernateTemplate template, 
			Map<String, Object> params, Class<T> type, Integer pageSize, Integer pageNum) {
		Assert.notNull(query);
		Assert.notNull(template);
		pageSize = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
		pageNum = pageNum == null ? 0 : pageNum;
		
		
		if(pageSize < 1){
			throw new BusinessException("pageSize必须为大于0");
		}
		if(pageNum < 0){
			throw new BusinessException("pageNum不能为负数");
		}
		
		this.query = query;
		this.template = template;
		this.params = params;
		this.type = type;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
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
	
	
	/**
	 * 计算分页开始数据行号
	 */
	protected int getStartRowNum(){
		return pageSize*pageNum;
	}
	
	
	/**
	 * 计算分页结束数据行号
	 */
	protected int getEndRowNum(){
		return pageSize*(pageNum+1)-1;
	}
	
	
	/**
	 * 获取总记录数
	 */
	protected int getTotalCount(Session s, String query){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(1) FROM (");
		sb.append(query);
		sb.append(") tmp_table_"+System.currentTimeMillis());
		Query countQuery = s.createSQLQuery(sb.toString());
		
		//查询参数
		if (params != null && !params.isEmpty()) {
			Iterator it = params.entrySet().iterator();
			for (int i = 0; i < params.size(); i++) {
				Map.Entry<String, ?> entry = (Entry<String, ?>) it.next();
				this.setQueryObjectParams(countQuery, entry);
			}
		}
		
		List countList = countQuery.list();
		if(!(countList != null && countList.size() > 0)){
			throw new BusinessException("获取结果总数出错");
		}
		return Integer.valueOf(countList.get(0).toString());
	}
	
	
	/**
	 * 获取总页数
	 */
	protected int getTotalPage(int totalCount){
		if(totalCount % pageSize == 0){
			return totalCount/pageSize;
		}
		return totalCount/pageSize + 1;
	}
	
	
	public SearchResult<T> doInHibernate(Session s) throws HibernateException,
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
		
		List<T> resultList = (List<T>) queryObject.list();
		
		int totalCount = getTotalCount(s, query);
		int totalPage = getTotalPage(totalCount);
		return new SearchResult<T>(resultList, type, pageSize, pageNum, totalPage, totalCount);
	}
	
}
