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
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.easygo.common.utils.BusinessException;
import com.easygo.user.bo.CoreUser;

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
		Assert.notNull(hql);
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
		Assert.notNull(hql);
		HQLCallback<Integer> call = new HQLCallback<Integer>(hql, params, true, this.getHibernateTemplate());
		return this.getHibernateTemplate().execute(call);
	}
	
	
	/**
	 * 获取sql命名查询的字符串
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public String getQueryString(String namedQueryName){
		Session s = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		return s.getNamedQuery(namedQueryName).getQueryString();
	}
	
	
	/**
	 * SQL Query查询，不分页
	 */
	@Transactional(readOnly = false)
	public QueryResult<Map> doSQLQuery(String query, Map<String, Object> params){
		SQLQueryCallback<Map> callback = new SQLQueryCallback<Map>(query, this.getHibernateTemplate(),
				params, Map.class);
		return this.getHibernateTemplate().execute(callback);
	}
	
	
	/**
	 * namedQuery查询，不分页
	 */
	@Transactional(readOnly = false)
	public QueryResult<Map> doNamedSQLQuery(String namedQueryName, Map<String, Object> params){
		String query = getQueryString(namedQueryName);
		return doSQLQuery(query, params);
	}
	

	/**
	 * SQLQuery查询，分页
	 */
	@Transactional(readOnly = false)
	public SearchResult<Map> doSQLSearch(String query, Map<String, Object> params, Integer pageSize, Integer pageNum){
		SQLSearchCallback<Map> callback = new SQLSearchCallback<Map>(query, this.getHibernateTemplate(), params, 
				Map.class, pageSize, pageNum);
		
		return this.getHibernateTemplate().execute(callback);
	}
	
	
	/**
	 * namedQuery查询，分页
	 */
	@Transactional(readOnly = false)
	public SearchResult<Map> doNamedSQLSearch(String namedQueryName, Map<String, Object> params, Integer pageSize, Integer pageNum){
		String query = getQueryString(namedQueryName);
		return doSQLSearch(query, params, pageSize, pageNum);
	}

}

