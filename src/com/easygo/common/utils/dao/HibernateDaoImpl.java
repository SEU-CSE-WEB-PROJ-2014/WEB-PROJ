package com.easygo.common.utils.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
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
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.easygo.common.utils.BusinessException;

/**
 * 供hibernate使用的通用dao. 适用于处理单主键的bo对象. K为主键的类型, T为bo对象的类型.
 */
public class HibernateDaoImpl<K extends Serializable, T> extends
		HibernateDaoSupport implements HibernateDao<K, T> {
	
	/**
	 * queryByParams查询类型：等查询(eq)
	 */
	private static final Byte QUERY_BY_PARAMS_EQ = 0;
	
	/**
	 * queryByParams查询类型：in查询
	 */
	private static final Byte QUERY_BY_PARAMS_IN = 1;
	
	/**
	 * queryByParams查询类型：混合的in/eq查询
	 */
	private static final Byte QUERY_BY_PARAMS_MIXED = 2;
	
	/**
	 * 实体类类型
	 */
	private final Class<T> typeClass;
	
	
	/**
	 * 根据entry构造查询条件
	 */
	private String buildQueryCondition(Map.Entry<String, ?> entry){
		String condition = null;
		if(isArrayOrCollection(entry.getValue())){
			condition = " AND t." + entry.getKey() + " in (:" + entry.getKey() + ")";
		}else{
			condition = " AND t." + entry.getKey() + " = :" + entry.getKey();
		}
		return condition;
	}
	
	/**
	 * 全字段可选，参数指定的in/eq/mixed查询
	 */
	@Transactional(readOnly = true)
	private List<T> queryByParams(Map<String, Object> params, Byte queryType) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM " + typeClass.getName() + " t WHERE 1=1");

		// 构造等查询hql
		if (params != null && params.size() > 0) {
			Iterator it = params.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, ?> entry = (Entry<String, ?>) it.next();
				
				//查询字段数据校验
				if (entry.getValue() == null) {								//查询字段值为空
					throw new BusinessException("查询字段：" + entry.getKey() + "\t值不能为null");
				}
				
				//查询字段存在性校验
				if(!isFieldExist(entry.getKey())){							//typeClass不存在该字段（数据库表不存在该字段）
					throw new BusinessException("查询字段：" + entry.getKey() + "\t不存在于数据表映射类 " + typeClass.getName() + " 中");
				}
				
				if(queryType.equals(QUERY_BY_PARAMS_EQ)){
					if(isArrayOrCollection(entry.getValue())){
						throw new BusinessException("查询字段：" + entry.getKey() + "\t值不能为数组或集合");
					}
				}else if(queryType.equals(QUERY_BY_PARAMS_IN)){
					if(!isArrayOrCollection(entry.getValue())){
						throw new BusinessException("查询字段：" + entry.getKey() + "\t值必须为为数组或集合");
					}
				}else if(queryType.equals(QUERY_BY_PARAMS_MIXED)){
					//in/eq都可以，不报异常
				}else{
					throw new BusinessException("未定义的查询类型：" + queryType);
				}
				
				sb.append(buildQueryCondition(entry));
			}
		}
		
		return (List<T>) this.findByParams(sb.toString(), params);
	}

	
	/**
	 * 判断参数是否为数组或集合类型
	 * @param obj
	 * @return
	 */
	private Boolean isArrayOrCollection(Object obj){
		return obj instanceof Object[] || obj instanceof Collection<?> 
			? true : false;
	}
	
	
	/**
	 * 判断某个字段是否存在于typeClass
	 * @param fieldName
	 * @return
	 */
	private Boolean isFieldExist(String fieldName){
		Boolean result = false;
		try{
			typeClass.getDeclaredField(fieldName);
			result = true;
		}catch(Exception e){
			
		}
		return result;
	}
	
	
	
	// 设置dao的sessionFactory
	@Resource
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	@SuppressWarnings("unchecked")
	public HibernateDaoImpl() {
		Type type = this.getClass().getGenericSuperclass();
		this.typeClass = (Class<T>) ((ParameterizedType) type)
				.getActualTypeArguments()[1];
	}

	@Transactional(readOnly = true)
	public T get(K key) {
		return this.getHibernateTemplate().get(typeClass, key);
	}

	@Transactional(readOnly = true)
	public T load(K key) {
		return this.getHibernateTemplate().load(typeClass, key);
	}

	@Transactional(readOnly = false)
	public void save(T bean) {
		this.getHibernateTemplate().save(bean);
	}

	@Transactional(readOnly = false)
	public void update(T bean) {
		this.getHibernateTemplate().update(bean);
	}

	@Transactional(readOnly = false)
	public void saveOrUpdate(T bean) {
		this.getHibernateTemplate().saveOrUpdate(bean);
	}

	@Transactional(readOnly = false)
	public void delete(T bean) {
		this.getHibernateTemplate().delete(bean);
	}

	public void flush() {
		this.getHibernateTemplate().flush();
	}

	@Transactional(readOnly = true)
	public List<?> findByParams(String hql,
			Map<String, Object> params) {
		HQLCallback<List<?>> call = new HQLCallback<List<?>>(hql, params, false, this.getHibernateTemplate());
		return this.getHibernateTemplate().execute(call);
	}
	
	
	

	/**
	 * 全字段可选等查询
	 */
	@Transactional(readOnly = true)
	public List<T> eqQueryByParams(Map<String, Object> params){
		return queryByParams(params, QUERY_BY_PARAMS_EQ);
	}

	
	/**
	 * 全字段可选in查询
	 */
	@Transactional(readOnly = true)
	public List<T> inQueryByParams(Map<String, Object> params) {
		return queryByParams(params, QUERY_BY_PARAMS_IN);
	}
	
	
	/**
	 * 全字段可选，混合的in/eq查询
	 */
	@Transactional(readOnly = true)
	public List<T> mixedInEqQueryByParams(Map<String, Object> params) {
		return queryByParams(params, QUERY_BY_PARAMS_MIXED);
	}
	
	/**
	 * hql更新数据表 
	 */
	@Transactional(readOnly = false)
	public Integer bulkUpdate(String hql, Map<String, ?> params){
		HQLCallback<Integer> call = new HQLCallback<Integer>(hql, params, true, this.getHibernateTemplate());
		return this.getHibernateTemplate().execute(call);
	}
	
}


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
