package com._520it.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.Permission;
import com._520it.ssh.query.PermissionQueryObject;
import com._520it.ssh.service.IPermissionService;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class PermissionAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Setter@Getter
	private Permission permission = new Permission();
	@Setter
	private IPermissionService permissionService; 
	@Setter@Getter
	private PermissionQueryObject qo=new PermissionQueryObject();
	@Override
	@RequiredPermission("权限列表")
	@InputConfig(methodName="input")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ActionContext.getContext().put("pageResult", permissionService.queryAndHigh(qo));
		return "list";
	}
	@RequiredPermission("权限加载")
	public String reload() throws Exception {
		// TODO Auto-generated method stub
		try {
			permissionService.load();
			addActionError("加载成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionMessage(e.getMessage());
		}
		return NONE;
	}
	public String delete(){
		try {
			permissionService.delete(permission.getId());
			putResponseText("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return NONE;
	}
}
