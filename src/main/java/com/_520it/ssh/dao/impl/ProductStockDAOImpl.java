package com._520it.ssh.dao.impl;

import org.hibernate.Session;

import com._520it.ssh.dao.IProductStockDAO;
import com._520it.ssh.domain.ProductStock;

public class ProductStockDAOImpl extends GenericDAOImpl<ProductStock> implements IProductStockDAO {

	@Override
	public ProductStock queryByProidAndDepotId(Long productID, Long depotID) {
		Session session = sessionFactory.getCurrentSession();
		String hql="select obj from ProductStock obj where obj.product.id =? and  obj.depot.id = ?";
		return (ProductStock) session.createQuery(hql).setParameter(0, productID).setParameter(1, depotID).uniqueResult();
	}
}
