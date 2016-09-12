package com._520it.ssh.web.action;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.SystemMenu;
import com._520it.ssh.query.SystemMenuQueryObject;
import com._520it.ssh.service.ISystemMenuService;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class SystemMenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
    private ISystemMenuService systemMenuService;

    @Getter
    private SystemMenu systemMenu = new SystemMenu();

    @Getter
    private SystemMenuQueryObject qo = new SystemMenuQueryObject();

    @RequiredPermission("菜单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
        	if(qo.getParentId()>0){
        		putContext("menus", systemMenuService.queryMenuByParentId(qo.getParentId()));
        	}
            putContext("pageResult", systemMenuService.queryAndHigh(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("菜单编辑")
    public String input() throws Exception {
    	if(qo.getParentId()<0){
    		putContext("parentName", "根目录");
    	}else{
    		//System.out.println("打印名称"+systemMenuService.get(qo.getParentId()).getName());
    		putContext("parentName", systemMenuService.get(qo.getParentId()).getName());
    	}
        if (systemMenu.getId() != null) {
            systemMenu = systemMenuService.get(systemMenu.getId());
        }
        return INPUT;
    }

    @RequiredPermission("菜单删除")
    public String delete() throws Exception {
    	try {			
    		if (systemMenu.getId() != null) {
    			systemMenuService.delete(systemMenu.getId());
    			putResponseText("删除成功");
    		}
		} catch (Exception e) {
			e.printStackTrace();
			putResponseText(e.getMessage());
		}
        return NONE;
    }

    @RequiredPermission("菜单保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
        	if(qo.getParentId()>0){
        		SystemMenu parent = systemMenuService.get(qo.getParentId());
        		systemMenu.setParent(parent);
        	}
            if (systemMenu.getId() == null) {
                systemMenuService.save(systemMenu);
                addActionMessage("保存成功!");
            } else {
                systemMenuService.update(systemMenu);
                addActionMessage("更改成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    public void prepareSaveOrUpdate() throws Exception {
        if (systemMenu.getId() != null) {
            systemMenu = systemMenuService.get(systemMenu.getId());
        }
    }
    public String loadMenuByParentSn() throws Exception{
    	List<SystemMenu> list =systemMenuService.queryByParentSn(qo.getParentSn());
    	List<Object> menus = new ArrayList<>();
    	for (SystemMenu systemMenu : list) {
    		menus.add(systemMenu.toJson());
		}
    	putJson(menus);
    	return NONE;
    }
}
