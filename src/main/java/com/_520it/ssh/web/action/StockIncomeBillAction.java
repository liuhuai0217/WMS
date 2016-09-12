package com._520it.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.StockIncomeBill;
import com._520it.ssh.query.StockIncomeBillQueryObject;
import com._520it.ssh.service.IDepotService;
import com._520it.ssh.service.IStockIncomeBillService;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class StockIncomeBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
    private IStockIncomeBillService stockIncomeBillService;

    @Setter
    private IDepotService depotService;
    
    @Getter
    private StockIncomeBill stockIncomeBill = new StockIncomeBill();


    @Getter
    private StockIncomeBillQueryObject qo = new StockIncomeBillQueryObject();

    @RequiredPermission("入库列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
        	putContext("depots", depotService.listAll());
            putContext("pageResult", stockIncomeBillService.queryAndHigh(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("入库编辑")
    public String input() throws Exception {
    	putContext("depots", depotService.listAll());
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
        }
        return INPUT;
    }

    @RequiredPermission("入库删除")
    public String delete() throws Exception {
    	try {
    		if (stockIncomeBill.getId() != null) {
    			stockIncomeBillService.delete(stockIncomeBill.getId());
    			putResponseText("删除成功");
    		}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionError(e.getMessage());
		}
        return NONE;
    }

    @RequiredPermission("入库保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (stockIncomeBill.getId() == null) {
                stockIncomeBillService.save(stockIncomeBill);
                addActionMessage("保存成功!");
            } else {
                stockIncomeBillService.update(stockIncomeBill);
                addActionMessage("更改成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    @RequiredPermission("入库审核")
    public String audit() throws Exception{
    	try {
    		if(stockIncomeBill.getId()!=null){
    			stockIncomeBillService.audit(stockIncomeBill.getId());
    			addActionMessage("审核成功");
    		}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionError(e.getMessage());
		}
    	return SUCCESS;
    }
    @RequiredPermission("入库信息查看")
    public String show() throws Exception{
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
        }
    	return "show";
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
            stockIncomeBill.getItems().clear();
        }
    }
}
