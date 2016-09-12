package com._520it.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.ssh.dao.impl.RoleDAOImpl;
import com._520it.ssh.domain.Role;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IRoleService;

public class RoleServiceImpl implements IRoleService{

	@Setter
	private RoleDAOImpl roleDAO;

	public void save(Role dept) {
		// TODO Auto-generated method stub
		roleDAO.save(dept);
	}


	public void delete(Long id) {
		// TODO Auto-generated method stub
		roleDAO.delete(id);
	}


	public void update(Role dept) {
		// TODO Auto-generated method stub
		roleDAO.update(dept);
	}


	public Role get(Long id) {
		// TODO Auto-generated method stub
		return roleDAO.get(id);
	}


	public List<Role> listAll() {
		// TODO Auto-generated method stub
		return roleDAO.listAll();
	}


	public PageQuery<?> queryAndHigh(QueryObject qo) {
		// TODO Auto-generated method stub
		return roleDAO.queryAndHigh(qo);
	}

}
