package com._520it.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.OrderBill;
import com._520it.ssh.query.OrderBillQueryObject;
import com._520it.ssh.service.IOrderBillService;
import com._520it.ssh.service.ISupplierService;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class OrderBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Setter
    private IOrderBillService orderBillService;
    @Setter
    private ISupplierService supplierService;
    @Getter
    private OrderBill orderBill = new OrderBill();

    @Getter
    private OrderBillQueryObject qo = new OrderBillQueryObject();

    @RequiredPermission("采购单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
    	System.out.println("获取的qo的getStatus:"+qo.getStatus());
    	System.out.println("获取的qo的getSupplierid:"+qo.getSupplierid());
    	System.out.println("获取的qo的getBeginTime:"+qo.getBeginTime());
    	System.out.println("获取的qo的getEndTime:"+qo.getEndTime());
        try {
        	putContext("suppliers", supplierService.listAll());//获取所有的供应商信息
            putContext("pageResult", orderBillService.queryAndHigh(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("采购单编辑")
    public String input() throws Exception {
    	System.out.println("采购单的id="+orderBill.getId());
    	putContext("suppliers", supplierService.listAll());
    	if (orderBill.getId() != null) {
    		orderBill = orderBillService.get(orderBill.getId());
    		/*
    		List<OrderBillItem> lists = orderBill.getItems();
    		for (OrderBillItem orderBillItem : lists) {
				System.out.println(orderBillItem);
			}
			*/
    	}
    	return INPUT;
    	
    }

    @RequiredPermission("采购单删除")
    public String delete() throws Exception {
    	try {
    		if (orderBill.getId() != null) {
    			orderBillService.delete(orderBill.getId());
    			putResponseText("删除成功");
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
        return NONE;
    }

    @RequiredPermission("采购单保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (orderBill.getId() == null) {
                orderBillService.save(orderBill);
                addActionMessage("保存成功!");
            } else {
            	System.out.println("获取业务时间"+orderBill.getVdate());
                orderBillService.update(orderBill);
                addActionMessage("更改成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }
  
    public String audit() throws Exception {
    	
    	if(orderBill.getId()!=null){
    		orderBillService.audit(orderBill.getId());
    		addActionMessage("审核成功");
    	}
    	return SUCCESS;
    }

    public String show() throws Exception {
    	putContext("suppliers", supplierService.listAll());
    	if(orderBill.getId()!=null){
    		orderBill = orderBillService.get(orderBill.getId());
    	}
    	return "show";
    }
    public void prepareSaveOrUpdate() throws Exception {
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
            orderBill.getItems().clear();//断绝关系
        }
    }
}
