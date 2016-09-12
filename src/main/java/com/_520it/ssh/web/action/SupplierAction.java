package com._520it.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.Supplier;
import com._520it.ssh.query.SupplierQueryObject;
import com._520it.ssh.service.ISupplierService;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class SupplierAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
    private ISupplierService supplierService;

    @Getter
    private Supplier supplier = new Supplier();

    @Getter
    private SupplierQueryObject qo = new SupplierQueryObject();

    @RequiredPermission("供应商列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
           // putContext("pageResult", supplierService.query(qo));
        	putContext("pageResult", supplierService.queryAndHigh(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("供应商编辑")
    public String input() throws Exception {
    	
        if (supplier.getId() != null) {
            supplier = supplierService.get(supplier.getId());
        }
        return "input";
    }

    @RequiredPermission("供应商删除")
    public String delete() throws Exception {
    	try {
    		if (supplier.getId() != null) {
    			supplierService.delete(supplier.getId());
    			putResponseText("删除成功");
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionError(e.getMessage());
		}
        return NONE;
    }

    @RequiredPermission("供应商保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (supplier.getId() == null) {
                supplierService.save(supplier);
                addActionMessage("保存成功!");
            } else {
                supplierService.update(supplier);
                addActionMessage("更改成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (supplier.getId() != null) {
            supplier = supplierService.get(supplier.getId());
        }
    }
}
