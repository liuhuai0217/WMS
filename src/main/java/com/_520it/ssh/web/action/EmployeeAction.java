package com._520it.ssh.web.action;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.Department;
import com._520it.ssh.domain.Employee;
import com._520it.ssh.query.EmployeeQueryObject;
import com._520it.ssh.service.IDepartmentService;
import com._520it.ssh.service.IEmployeeService;
import com._520it.ssh.service.IRoleService;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class EmployeeAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@Setter
	private IEmployeeService  serviceDAO;
	@Getter
	EmployeeQueryObject qo = new EmployeeQueryObject();
	@Setter
	private IRoleService roleService;
	@Setter
	private IDepartmentService  departmentService;
	@Setter@Getter
	private Employee employee  = new Employee();
	
	@Setter@Getter
	private Department depts = new Department();
	
	@Setter
	List<Long> ids = new ArrayList<>();
	@RequiredPermission("员工信息")
	@InputConfig(methodName="input")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			super.putContext("depts", departmentService.listAll());
			super.putContext("pageResult",serviceDAO.queryAndHigh(qo));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionError(e.getMessage());
		}
			return "list";
	}
	@RequiredPermission("员工编辑")
	public String input()throws Exception{
		super.putContext("depts",departmentService.listAll());
		super.putContext("roles", roleService.listAll());
		if(employee.getId()!=null){
			employee = serviceDAO.get(employee.getId());
		}
		return "input";
	}
	@RequiredPermission("员工删除")
	public String delete()throws Exception{
		try {
			serviceDAO.delete(employee.getId());
			putResponseText("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("员工更新或保存")
	public String saveOrUpdate()throws Exception{
		try {
			if(employee.getId()!=null){
				serviceDAO.update(employee);
				addActionMessage("更新成功");
			}else{
				serviceDAO.save(employee);
				addActionMessage("保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}
	//批量删除员工信息
	public String batchDelete(){
		try {			
			if(ids.size()>0){
				//System.out.println(ids);
				serviceDAO.batchDelete(ids);
				addActionMessage("批量删除成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionMessage(e.getMessage());
		}
		return NONE;
	}
	public void prepareSaveOrUpdate(){
		if(employee.getId()!=null){
			employee = serviceDAO.get(employee.getId());
		}
		employee.getRole().clear();//打破关系
	}
	
	
}
