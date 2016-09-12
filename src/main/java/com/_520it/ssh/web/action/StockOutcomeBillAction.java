package com._520it.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.StockOutcomeBill;
import com._520it.ssh.query.StockOutcomeBillQueryObject;
import com._520it.ssh.service.IClientService;
import com._520it.ssh.service.IDepotService;
import com._520it.ssh.service.IStockOutcomeBillService;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class StockOutcomeBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
    private IStockOutcomeBillService stockOutcomeBillService;

    @Setter
    private IDepotService depotService;
    
    @Setter
    private IClientService clientService;
    @Getter
    private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();

    @Getter
    private StockOutcomeBillQueryObject qo = new StockOutcomeBillQueryObject();

    @RequiredPermission("出库信息列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
        	putContext("depots", depotService.listAll());
            putContext("pageResult", stockOutcomeBillService.queryAndHigh(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("出库信息编辑")
    public String input() throws Exception {
    	putContext("clients", clientService.listAll());
    	putContext("depots", depotService.listAll());
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
            System.out.println("打印一共有多少条数据"+stockOutcomeBill.getItems().size());
        }
        return INPUT;
    }

    @RequiredPermission("出库信息删除")
    public String delete() throws Exception {
    	try {
    		if (stockOutcomeBill.getId() != null) {
    			stockOutcomeBillService.delete(stockOutcomeBill.getId());
    			putResponseText("删除成功");
    		}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
        return NONE;
    }

    @RequiredPermission("出库信息保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (stockOutcomeBill.getId() == null) {
                stockOutcomeBillService.save(stockOutcomeBill);
                addActionMessage("保存成功!");
            } else {
                stockOutcomeBillService.update(stockOutcomeBill);
                addActionMessage("更改成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    
    public String audit() throws Exception{
    	try {			
    		if(stockOutcomeBill.getId()!=null){
    			stockOutcomeBillService.audit(stockOutcomeBill.getId());
    			putResponseText("审核成功");
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionError(e.getMessage());
		}
    	return SUCCESS;
    }
    
    public String show() throws Exception{
    	stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
    	return "show";
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
            stockOutcomeBill.getItems().clear();
        }
    }
}
