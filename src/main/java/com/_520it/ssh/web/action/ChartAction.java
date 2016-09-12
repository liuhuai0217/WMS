package com._520it.ssh.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.query.OrderBillChartQueryObject;
import com._520it.ssh.query.SaleAccountQueryObject;
import com._520it.ssh.service.IBrandService;
import com._520it.ssh.service.IChartService;
import com._520it.ssh.service.IClientService;
import com._520it.ssh.service.ISupplierService;
import com._520it.ssh.vo.OrderGroupByType;
import com._520it.ssh.vo.SaleChartVO;
import com._520it.ssh.vo.SaleGroupByType;
import com.alibaba.fastjson.JSON;
public class ChartAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IChartService chartService;
	@Setter
	private ISupplierService supplierService;
	@Setter
	private IClientService clientService;
	@Getter@Setter
	public OrderBillChartQueryObject oqo = new OrderBillChartQueryObject();
	
	
	@Getter @Setter
	private SaleAccountQueryObject qo = new SaleAccountQueryObject();
	@Setter
	private IBrandService brandService;
	
	
	public String orderChart() throws Exception {
		putContext("suppliers", supplierService.listAll());
		putContext("brands", brandService.listAll());
		putContext("orderCharts", chartService.queryOrderChart(oqo));
		putContext("orderGroupByType", OrderGroupByType.values());
		return "orderChart";
	}
	public String saleChart() throws Exception {
		putContext("suppliers", supplierService.listAll());
		putContext("brands", brandService.listAll());
		putContext("saleCharts", chartService.querySaleChart(qo));
		putContext("clients", clientService.listAll());
		putContext("saleGroupByType", SaleGroupByType.values());
		return "saleChart";
	}
	//跳转到线形图的界面
	public String saleChartByLine()throws Exception{
		//获取根据所有的销售的金额
		List<SaleChartVO> sales= chartService.querySaleChart(qo);
		//定义一个数据,进行存放所有的存储方式
		List<String> groupByType= new ArrayList<>();
		List<BigDecimal> totalAmount = new ArrayList<>();
		for (SaleChartVO vo : sales) {
			groupByType.add(vo.getGroupType());
			totalAmount.add(vo.getTotlalAmout());
		}
		putContext("groupBy", qo.getGroupType());
		putContext("groupType", JSON.toJSONString(groupByType));
		putContext("totalAmount", JSON.toJSONString(totalAmount));
		return "saleChartByLine";
	}
	//跳转到饼图的页面
	public String saleChartByPie() throws Exception{
		List<SaleChartVO> sales= chartService.querySaleChart(qo);
		//定义一个数据,进行存放所有的存储方式
		List<Object[]> datas= new ArrayList<>();
		for (SaleChartVO vo : sales) {
			datas.add(new Object[]{vo.getGroupType(),vo.getTotlalAmout()});
		}
	
		putContext("datas", JSON.toJSONString(datas));
		return "saleChartByPie";
	}
	
	
}
