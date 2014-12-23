package com.easygo.user.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.easygo.common.utils.dao.HibernateDaoImpl;
import com.easygo.user.bo.CoreUser;
import com.easygo.user.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoImpl<String, CoreUser> implements UserDao{
	
	
	public void testNamedQuery(){
		Session s = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.getNamedQuery("CoreUser.listAll");
		q.setFirstResult(2);
		q.setMaxResults(4);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List list = q.list();
		return;
	}
	
	
	public void testNamedNativeQuery(){
		Session s = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		s.beginTransaction();

		Query q = s.createSQLQuery("select u.* from core_user u");
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		q.list();
		
//		Query q = s.getNamedQuery("CoreUser.nListAll");
//		q.setFirstResult(2);
//		q.setMaxResults(4);
//		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//		List list = q.list();
		return;
	}

}
