package com._520it.ssh.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import com._520it.ssh.dao.IOrderBillDAO;
import com._520it.ssh.domain.Employee;
import com._520it.ssh.domain.OrderBill;
import com._520it.ssh.domain.OrderBillItem;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IOrderBillService;
import com.opensymphony.xwork2.ActionContext;

public class OrderBillServiceImpl implements IOrderBillService {
    @Setter
    private IOrderBillDAO orderBillDAO;
    public void save(OrderBill orderBill) {
     	//表单保存的六大步骤
    	//1,设置制单人和制单时间
    	Employee emp = (Employee) ActionContext.getContext().getSession().get("use_in_session");
    	orderBill.setInputUser(emp);

    	orderBill.setInputTime(new Date());
    	//2,重新设置单据的审核状态(未审核)
    	orderBill.setStatus(OrderBill.NORMAL);
    	params(orderBill);
    	//6,保存单据
    	
        orderBillDAO.save(orderBill);
    }

	private void params(OrderBill orderBill) {
		orderBill.setTotalNumber(BigDecimal.ZERO);
    	orderBill.setTotalAmout(BigDecimal.ZERO);
    	//3,处理单据和明细之间的关系
    	for (OrderBillItem item : orderBill.getItems()) {
			item.setBill(orderBill);
			//4,计算单挑明细的小计
			item.setAmount(item.getNumber().multiply(item.getCostPrice()).setScale(2,RoundingMode.HALF_UP));
			//5,计算单据的总数量和总金额
			orderBill.setTotalNumber(orderBill.getTotalNumber().add(item.getNumber()));
			orderBill.setTotalAmout(orderBill.getTotalAmout().add(item.getAmount()));
    	}
	}

    public void update(OrderBill orderBill) {
    	if(orderBill.getStatus()==OrderBill.NORMAL){
    		params(orderBill);
    		orderBillDAO.update(orderBill);    		
    	}
    }

    public void delete(Long id) {
        orderBillDAO.delete(id);
    }

    public OrderBill get(Long id) {
        return orderBillDAO.get(id);
    }

    public List<OrderBill> listAll() {
        return orderBillDAO.listAll();
    }

    public  PageQuery<?> queryAndHigh(QueryObject qo) {
        return orderBillDAO.queryAndHigh(qo);
    }

	@Override
	public void audit(Long id) {
		OrderBill bill = orderBillDAO.get(id);
		if(bill.getStatus()==OrderBill.NORMAL){
			//获取审核人
			Employee emp = (Employee) ActionContext.getContext().getSession().get("use_in_session");
			//把该人存放到orderbILL
			bill.setAuditor(emp);
			bill.setAuditTime(new Date());
			bill.setStatus(OrderBill.AUDIT);		
			orderBillDAO.update(bill);
		}
	}
}
