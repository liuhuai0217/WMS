package com._520it.ssh.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com._520it.ssh.dao.ISystemMenuDAO;
import com._520it.ssh.domain.Role;
import com._520it.ssh.domain.SystemMenu;

public class SystemMenuDAOImpl extends GenericDAOImpl<SystemMenu> implements ISystemMenuDAO {

	@Override
	public List<SystemMenu> queryByParentSn(String parentSn) {
		Session session = sessionFactory.getCurrentSession();
		String hql="select obj from SystemMenu obj where obj.parent.sn=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, parentSn);
		return query.list();
	}

	@Override
	public List<SystemMenu> queryByParentSnAndRole(String parentSn,
			List<Role> role) {
		Session session = sessionFactory.getCurrentSession();
		String hql="SELECT  m  FROM Role r JOIN r.systemMenus m WHERE m.parent.sn = :parentSn AND r IN (:roles)"; 
		Query query = session.createQuery(hql);
		query.setParameter("parentSn", parentSn);
		query.setParameterList("roles",role);
		return query.list();
	}
}
