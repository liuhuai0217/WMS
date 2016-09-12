package com._520it.ssh.service;

import java.util.List;

import com._520it.ssh.domain.Permission;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface IPermissionService {
	 void delete(Long id);
	   	   
	 Permission get(Long id);

	void load();
	
	List<Permission> listAll();
	
	PageQuery<?>  queryAndHigh(QueryObject qo);
}
