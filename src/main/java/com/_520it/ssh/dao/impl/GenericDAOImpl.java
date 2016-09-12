package com._520it.ssh.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com._520it.ssh.dao.IGenericDAO;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public class GenericDAOImpl<T> implements IGenericDAO<T> {
	private Class<T> target;
	
	protected SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public GenericDAOImpl(){
		ParameterizedType ptype=(ParameterizedType) this.getClass().getGenericSuperclass();
		target = (Class<T>) ptype.getActualTypeArguments()[0];
	}
	public void save(T obj) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		//session.save(obj);
		session.persist(obj);
	}

	public void delete(Long obj) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		System.out.println("根据id进行删除");
		T object  = (T) session.get(target, obj);
		session.delete(object);
	}

	public void update(T obj) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(obj);
		
	}

	@SuppressWarnings("unchecked")
	public T get(Long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		return (T) session.get(target, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(target).list();
	}
	public List<T> query(QueryObject qo) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<T> queryByPage(int currentPage,int pageSize){
		return null;
	}
	@SuppressWarnings("unchecked")
	public  PageQuery  queryAndHigh(QueryObject qo){
		System.out.println("打印查询的条件"+qo.customerCondition());
		//接收条件
		String condition  = qo.customerCondition();
		Session session  = sessionFactory.getCurrentSession();
		String hql1 = "Select count(obj) from "+target.getSimpleName()+" obj "+ condition;
		Query q1=session.createQuery(hql1);
		for (int index = 0; index < qo.getParamate().size(); index++) {
			q1.setParameter(index, qo.getParamate().get(index));
		}
		int totalCount =((Long)q1.uniqueResult()).intValue();//获取总条数
		String hql = "Select obj  from "+target.getSimpleName()+" obj "+condition;
		Query query = session.createQuery(hql);
		for (int index = 0; index < qo.getParamate().size(); index++) {
			query.setParameter(index, qo.getParamate().get(index));
		}
		int currentPage =(qo.getCurrentPage()-1)*qo.getPageSize();
		query.setMaxResults(qo.getPageSize());
		query.setFirstResult(currentPage);
		return new PageQuery(qo.getCurrentPage(), qo.getPageSize(), query.list(), totalCount);
	}
	
}
