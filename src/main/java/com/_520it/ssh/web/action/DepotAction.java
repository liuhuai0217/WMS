package com._520it.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.Depot;
import com._520it.ssh.query.DepotQueryObject;
import com._520it.ssh.service.IDepotService;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class DepotAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
    private IDepotService depotService;

    @Getter
    private Depot depot = new Depot();

    @Getter
    private DepotQueryObject qo = new DepotQueryObject();

    @RequiredPermission("仓库列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            putContext("pageResult", depotService.queryAndHigh(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("仓库编辑")
    public String input() throws Exception {
        if (depot.getId() != null) {
            depot = depotService.get(depot.getId());
        }
        return INPUT;
    }

    @RequiredPermission("仓库删除")
    public String delete() throws Exception {
    	try {
    		if (depot.getId() != null) {
    			depotService.delete(depot.getId());
    			putResponseText("删除成功");
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionError(e.getMessage());
		}
        return NONE;
    }

    @RequiredPermission("仓库保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (depot.getId() == null) {
                depotService.save(depot);
                addActionMessage("保存成功!");
            } else {
                depotService.update(depot);
                addActionMessage("更改成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (depot.getId() != null) {
            depot = depotService.get(depot.getId());
        }
    }
}
