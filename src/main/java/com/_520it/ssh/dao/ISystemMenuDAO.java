package com._520it.ssh.dao;


import java.util.List;

import com._520it.ssh.domain.Role;
import com._520it.ssh.domain.SystemMenu;

public interface ISystemMenuDAO extends IGenericDAO<SystemMenu>{

	List<SystemMenu> queryByParentSn(String parentSn);

	List<SystemMenu> queryByParentSnAndRole(String parentSn,List<Role> role);
}
