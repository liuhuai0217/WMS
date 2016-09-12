package com._520it.ssh.dao.impl;

import java.util.List;

import lombok.Setter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com._520it.ssh.dao.IChartDAO;
import com._520it.ssh.query.OrderBillChartQueryObject;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.query.SaleAccountQueryObject;
import com._520it.ssh.vo.OrderChartVO;
import com._520it.ssh.vo.OrderGroupByType;
import com._520it.ssh.vo.SaleChartVO;
import com._520it.ssh.vo.SaleGroupByType;

public class ChartDAOImpl implements IChartDAO{
	@Setter
	private SessionFactory sessionFactory;
	public List<OrderChartVO> queryOrderChart(OrderBillChartQueryObject qo) {
		OrderGroupByType groupByType = qo.getGroupType();

		Session session =sessionFactory.getCurrentSession();
		StringBuffer hql = new StringBuffer(80);
		hql.append("SELECT new OrderChartVO(");
		hql.append(groupByType.getGroupValue());
		hql.append(",SUM(obj.number),sum(obj.amount)) From OrderBillItem obj");
		hql.append(qo.customerCondition());
		hql.append(" group by ");
		hql.append(groupByType.getGroupBy());	
		System.out.println("打印出hlq="+hql);
		Query query = session.createQuery(hql.toString());
		setPlaceParameters(qo, query);
		return query.list();
	}
	private void setPlaceParameters(QueryObject qo,Query query){
		for(int index=0;index <qo.getParamate().size();index++){
			query.setParameter(index, qo.getParamate().get(index));
		}
	}
	public List<SaleChartVO> querySaleChart(SaleAccountQueryObject qo) {
		SaleGroupByType groupByType = qo.getGroupType();
		Session session =sessionFactory.getCurrentSession();
		StringBuffer hql = new StringBuffer(80);
		hql.append("SELECT new SaleChartVO(");
		hql.append(groupByType.getGroupValue());
		hql.append(",sum(obj.number),sum(obj.saleAmount),sum(obj.saleAmount-obj.costAmount)) From SaleAccount obj");
		hql.append(qo.customerCondition());
		hql.append(" group by ");
		hql.append(groupByType.getGroupBy());	
		System.out.println("打印出hlq="+hql);
		Query query = session.createQuery(hql.toString());
		setPlaceParameters(qo, query);
		return query.list();
	}
	
}
