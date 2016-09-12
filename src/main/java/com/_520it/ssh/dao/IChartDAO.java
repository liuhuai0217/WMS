package com._520it.ssh.dao;

import java.util.List;

import com._520it.ssh.query.OrderBillChartQueryObject;
import com._520it.ssh.query.SaleAccountQueryObject;
import com._520it.ssh.vo.OrderChartVO;
import com._520it.ssh.vo.SaleChartVO;

/**
 *订货报表的dao
 *
 */
public interface IChartDAO {
	
	List<OrderChartVO> queryOrderChart(OrderBillChartQueryObject qo);
	List<SaleChartVO> querySaleChart(SaleAccountQueryObject qo);
}
