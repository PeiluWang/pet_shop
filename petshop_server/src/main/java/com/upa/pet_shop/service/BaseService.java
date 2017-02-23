package com.upa.pet_shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upa.pet_shop.util.Page;

@Service
@Transactional
public class BaseService {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<Object[]> executeQuerySql(String sql){
		return executeQuerySql(sql, null);
	}
	
	/**
	 * 执行查询类sql
	 * @param sql，sql命令
	 * @param sqlParams，sql命令的参数
	 * @return
	 */
	public List<Object[]> executeQuerySql(String sql, HashMap<String, Object> sqlParams){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
		if(sqlParams!=null && sqlParams.size()>0){
			for(Entry<String, Object> entry: sqlParams.entrySet()){
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.list();
	}
	
	/**
	 * 执行更新类sql
	 * @param sql
	 * @param sqlParams
	 * @return
	 */
	public boolean executeUpdateSql(String sql, HashMap<String, Object> sqlParams){
		Session session=sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
		if(sqlParams.size()>0){
			for(Entry<String, Object> entry: sqlParams.entrySet()){
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		query.executeUpdate();
		tx.commit();
		return true;
	}
	
	/**
	 * Criteria条件查询
	 * @param entityClass
	 * @param criteriaParam
	 * @return
	 */
	public Page criteriaQuery(Class entityClass, CriteriaParam criteriaParam){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(entityClass,entityClass.toString());
		/*
		 * 添加限制条件
		 */
		for (Criterion criterion : criteriaParam.getCriterions()){
			criteria.add(criterion);
		}
		/*
		 * 添加order条件
		 */
		for (Order order : criteriaParam.getOrders()){
			criteria.addOrder(order);
		}
		// 统计总的个数，如果放在.list()后会返回Null，是bug!
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();
		//重新设置返回对象
		// TODO: 如果不加distinct，会出现重复的返回对象，原因未知
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		/*
		 * 分页参数
		 */
		if(criteriaParam.hasPageParam()){
			criteria = criteria.setFirstResult(criteriaParam.getPageId()*criteriaParam.getPageSize())
					.setMaxResults(criteriaParam.getPageSize());
		}
		//获取数据
		return new Page(resultCount, criteria.list());
	}

}
