package com._520it.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.ssh.dao.IChartDAO;
import com._520it.ssh.query.OrderBillChartQueryObject;
import com._520it.ssh.query.SaleAccountQueryObject;
import com._520it.ssh.service.IChartService;
import com._520it.ssh.vo.OrderChartVO;
import com._520it.ssh.vo.SaleChartVO;

public class ChartServiceImpl implements IChartService{
	@Setter
	private IChartDAO chartDAO;

	@Override
	public List<OrderChartVO> queryOrderChart(OrderBillChartQueryObject qo) {
		// TODO Auto-generated method stub
		return chartDAO.queryOrderChart(qo);
	}

	@Override
	public List<SaleChartVO> querySaleChart(SaleAccountQueryObject qo) {
		// TODO Auto-generated method stub
		return chartDAO.querySaleChart(qo);
	}
}
