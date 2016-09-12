package com._520it.ssh.dao.impl;

import lombok.Setter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com._520it.ssh.dao.ISaleAccountDAO;
import com._520it.ssh.domain.SaleAccount;


public class SaleAccountDAOImpl implements ISaleAccountDAO {
	@Setter
	private SessionFactory  sessionFactory;

	public void save(SaleAccount sale) {
		Session session = sessionFactory.getCurrentSession();
		session.save(sale);
	}
	
}
