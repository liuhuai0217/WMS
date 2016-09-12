package com._520it.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.Role;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IPermissionService;
import com._520it.ssh.service.IRoleService;
import com._520it.ssh.service.ISystemMenuService;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class RoleAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	@Setter
	private IPermissionService permissionService;
	
	@Setter
	private ISystemMenuService systemMenuService;
	@Setter
	private IRoleService roleService;
	@Getter@Setter
	private Role role = new Role();
	@Setter
	QueryObject qo = new QueryObject();
	@Override
	@RequiredPermission("角色列表")
	@InputConfig(methodName="input")
	public String execute() throws Exception {
		// TODO Auto-generated method stu
		try {
			
			ActionContext.getContext().put("pageResult", roleService.queryAndHigh(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	
	public String input() throws Exception{
		ActionContext.getContext().put("permissions", permissionService.listAll());
		ActionContext.getContext().put("menus", systemMenuService.listAll());
		if(role.getId()!=null){
			role = roleService.get(role.getId());
		}
		return "input";
	}
	@RequiredPermission("角色删除")
	public String delete() throws Exception{
		try {			
			if(role.getId()!=null){
				roleService.delete(role.getId());
				putResponseText("删除成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("角色修改或者添加")
	public String saveOrUpdate() throws Exception{
		try {
			if(role.getId()==null){
				roleService.save(role);
				addActionMessage("保存成功");
			}else{
				roleService.update(role);
				addActionMessage("更新成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionError("失败了!请稍后重试");
		}
		return SUCCESS;
	}
	public void prepareSaveOrUpdate(){
		if(role.getId()!=null){
			role = roleService.get(role.getId());
		}
		role.getPermission().clear();
		role.getSystemMenus().clear();
	}
}
