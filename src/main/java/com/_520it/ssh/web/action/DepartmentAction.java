package com._520it.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.Department;
import com._520it.ssh.query.DepartmentQueryObject;
import com._520it.ssh.service.IDepartmentService;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class DepartmentAction extends BaseAction {

	@Setter@Getter
	private Department department = new Department();
	@Setter@Getter
	DepartmentQueryObject qo = new DepartmentQueryObject();
	@Setter 
	private IDepartmentService departmentService;
	private static final long serialVersionUID = 1L;
	@Override
	@RequiredPermission("部门展示")
	@InputConfig(methodName="input")
	public String execute() throws Exception {
		try {
			super.putContext("pageResult", departmentService.queryAndHigh(qo));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionMessage(e.getMessage());
		}
		return "list";
	}
	@RequiredPermission("部门编辑")
	public String input()throws Exception {
		if(department.getId()!=null){
			department = departmentService.get(department.getId());
		}
		return "input";
	}
	@RequiredPermission("部门删除")
	public String delete()throws Exception {
		try {
			departmentService.delete(department.getId());
			putResponseText("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("部门更新或保存")
	public String saveOrUpdate()throws Exception {
		try {			
			System.out.println(department.getId());
			if(department.getId()!=null){
				departmentService.update(department);
				addActionMessage("更新成功");
			}else{
				departmentService.save(department);
				addActionMessage("保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}
	
}
