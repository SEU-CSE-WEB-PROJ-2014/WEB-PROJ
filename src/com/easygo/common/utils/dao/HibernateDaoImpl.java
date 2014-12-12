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

	
	/**
	 * 全字段可选等查询
	 * 把查询的等条件(bo.filed_1="value")放入map（map.put("filed_1", "value")），
	 * 返回表中符合filed_1="value"的数据列表
	 */
	public List<T> eqQueryByParams(Map<String, Object> params){
		
		StringBuilder sb = new StringBuilder();
		sb.append("FROM " + typeClass.getName() + " t WHERE 1=1");
		
		//构造等查询hql
		if(params != null && params.size() > 0){
			Iterator it = params.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, ?> entry = (Entry<String, ?>) it.next();
				try{
					if(entry.getValue() == null){
						params.remove(entry.getKey());
					}
					
					typeClass.getField(entry.getKey());			//没有异常则表示bo类存在该字段
					
					sb.append(" AND t." + entry.getKey() + " = :" + entry.getKey());
				}catch(Exception e){
					params.remove(entry.getKey());				//报异常则表示bo类不存在该字段，应从map中移除该字段的查询条件
				}
			}
		}
		return (List<T>) this.findByParams(sb.toString(), params);
	}
	
	
//	public List<T> eqQueryByParams(Map<String, Object> params){
//		Map procedParams = new HashMap<String, Object>();
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append("FROM " + typeClass.getName() + " t WHERE 1=1");
//		
//		//构造等查询hql
//		if(params != null && params.size() > 0){
//			//遍历bo类所有字段，如果params存在此字段的key，将其key和value放进procedParams
//			Field[] fields = typeClass.getFields();
//			if(fields != null && fields.length > 0){
//				for(int i = 0; i < fields.length; i++){
//					String fieldName = fields[i].getName();
//					if(params.get(fieldName) != null){
//						procedParams.put("fieldName", params.get(fieldName));
//						sb.append(" AND t." + fieldName + " = :" + fieldName);
//					}
//				}
//			}
//		}
//		return (List<T>) this.findByParams(sb.toString(), params);
//	}
	
	
	
	
	
	
	
	
	
	
	//	public void bulkUpdate(String hql, Map params);
	
	
	

}
