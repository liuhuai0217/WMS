package com._520it.ssh.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com._520it.ssh.dao.IEmployeeDAO;
import com._520it.ssh.domain.Employee;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.utils.MD5;

public class EmployeeDAOImpl extends GenericDAOImpl<Employee> implements IEmployeeDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> query(QueryObject qo) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "Select obj  from Employee obj"+qo.customerCondition();
		Query query = session.createQuery(hql);
		for (int index = 0; index < qo.getParamate().size(); index++) {
			query.setParameter(index, qo.getParamate().get(index));
		}
		return query.list();
		
	}
	@SuppressWarnings("unchecked")
	public List<Employee> queryByPage(int currentPage,int pageSize){
		System.out.println("EmployeeDAOImpl.queryByPage()");
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(obj) from Employee obj";
		int totalCount = ((Long)session.createQuery(hql).uniqueResult()).intValue();//获取总条数
		System.out.println("总条数"+totalCount);
		hql = "select obj from Employee obj";
		Query query = session.createQuery(hql);
		currentPage = (currentPage-1)*pageSize;
		query.setFirstResult(currentPage);
		query.setMaxResults(pageSize);
		
		return query.list();
	}
	public Employee check(String username, String password) {
		// TODO Auto-generated method stub
		String pwd = MD5.encode(password);
		Session session = sessionFactory.getCurrentSession();
		String hql = "select obj from Employee obj where obj.name = ? and obj.password = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		query.setParameter(1, pwd);
		return (Employee) query.uniqueResult();
	}
	
/*	public PageQuery queryAndHigh(QueryObject qo){
		Session session  = sessionFactory.getCurrentSession();
		//获取符合条件的员工的信息的数量
		String hql1 = "Select count(obj) from Employee obj "+ qo.customerCondition();
		Query q1=session.createQuery(hql1);
		for (int index = 0; index < qo.getParamate().size(); index++) {
			q1.setParameter(index, qo.getParamate().get(index));
		}
		System.out.println("条数为"+(Long)q1.uniqueResult());
		int totalCount =((Long)q1.uniqueResult()).intValue();//获取总条数
		//获取所有的符合条件的员工信息
		String hql = "Select obj  from Employee obj"+qo.customerCondition();
		Query query = session.createQuery(hql);
		for (int index = 0; index < qo.getParamate().size(); index++) {
			query.setParameter(index, qo.getParamate().get(index));
		}
		int currentPage =(qo.getCurrentPage()-1)*qo.getPageSize();
		query.setMaxResults(qo.getPageSize());
		query.setFirstResult(currentPage);
		return new PageQuery(qo.getCurrentPage(), qo.getPageSize(), query.list(), totalCount);
	}*/
	
	public void batchDelete(List<Long> ids) {
		Session session = sessionFactory.getCurrentSession();
		//根据编号列表 查询出所有的符合条件的数据,断掉关系
		/*
		String hql1="update Employee obj SET obj.admin=0 WHERE obj.id in (:ids)";
		Query query1 =session.createQuery(hql1);
		query1.setParameterList("ids", ids);		
		System.out.println(ids);
		 */
		//进行删除
		String hql="DELETE FROM Employee obj where obj.id IN(:ids)";
		Query query = session.createQuery(hql);
		query.setParameterList("ids", ids);
		query.executeUpdate(); 
	}
}
