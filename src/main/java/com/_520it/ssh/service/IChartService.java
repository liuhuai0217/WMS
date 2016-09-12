package com._520it.ssh.service;

import java.util.List;

import com._520it.ssh.query.OrderBillChartQueryObject;
import com._520it.ssh.query.SaleAccountQueryObject;
import com._520it.ssh.vo.OrderChartVO;
import com._520it.ssh.vo.SaleChartVO;

public interface IChartService {

	List<OrderChartVO> queryOrderChart(OrderBillChartQueryObject qo);
	List<SaleChartVO> querySaleChart(SaleAccountQueryObject qo);
}
