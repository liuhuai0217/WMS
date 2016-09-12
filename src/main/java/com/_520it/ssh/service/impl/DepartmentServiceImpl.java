package com._520it.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.ssh.dao.impl.DepartmentDAOImpl;
import com._520it.ssh.domain.Department;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService{
	@Setter
	private DepartmentDAOImpl deptDAO;
	public void save(Department dept) {
		// TODO Auto-generated method stub
		deptDAO.save(dept);
	}


	public void delete(Long id) {
		// TODO Auto-generated method stub
		deptDAO.delete(id);
	}


	public void update(Department dept) {
		// TODO Auto-generated method stub
		deptDAO.update(dept);
	}


	public Department get(Long id) {
		// TODO Auto-generated method stub
		return deptDAO.get(id);
	}


	public List<Department> listAll() {
		// TODO Auto-generated method stub
		return deptDAO.listAll();
	}


	public PageQuery<?> queryAndHigh(QueryObject qo) {
		// TODO Auto-generated method stub
		return deptDAO.queryAndHigh(qo);
	}
}
