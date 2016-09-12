package com._520it.ssh.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Setter;

import com._520it.ssh.dao.impl.EmployeeDAOImpl;
import com._520it.ssh.domain.Employee;
import com._520it.ssh.domain.Permission;
import com._520it.ssh.domain.Role;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IEmployeeService;
import com._520it.ssh.utils.MD5;
import com.opensymphony.xwork2.ActionContext;

public class EmployeeServiceImpl implements IEmployeeService{
	@Setter
	private EmployeeDAOImpl servicedao;
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		employee.setPassword(MD5.encode(employee.getPassword()));
		servicedao.save(employee);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		servicedao.delete(id);
	}

	public void update(Employee employee) {
		// TODO Auto-generated method stub
		servicedao.update(employee);
	}

	public Employee get(Long id) {
		// TODO Auto-generated method stub
		return servicedao.get(id);
	}

	public List<Employee> listAll() {
		// TODO Auto-generated method stub
		return servicedao.listAll();
	}

	public List<Employee> query(QueryObject obj) {
		return servicedao.query(obj);
	}


	public List<Employee> queryByPage(int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		System.out.println("EmployeeServiceImpl.queryByPage()");
		return servicedao.queryByPage(currentPage,pageSize);
	}
	public PageQuery<?> queryAndHigh(QueryObject qo){
		return servicedao.queryAndHigh(qo);
	}


	public void checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		Employee current = servicedao.check(username,password);
		if(current==null){
			throw new RuntimeException("亲,登录失败,请重新登录!");
		}
		ActionContext.getContext().getSession().put("use_in_session", current);
		//把登录的用户的权限存放到session中
		Set<String> permissionSet = new HashSet<String>();
		List<Role> roles = current.getRole();
		if(!current.isAdmin()){
			for (Role role : roles) {
				List<Permission> ps = role.getPermission();
				for (Permission p: ps) {
					permissionSet.add(p.getExpression());
				}
			}
			ActionContext.getContext().getSession().put("permission_in_session", permissionSet);
		}
	}

	@Override
	public void batchDelete(List<Long> ids) {
		// TODO Auto-generated method stub
		servicedao.batchDelete(ids);		
		
	}
}
