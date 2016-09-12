package com._520it.ssh.dao;


import com._520it.ssh.domain.ProductStock;

public interface IProductStockDAO extends IGenericDAO<ProductStock>{

	/**
	 * 根据商品编号和仓库的编号进行查询
	 * @param id 商品的编号
	 * @param id2 仓库的编号
	 * @return 一个仓库的对象
	 */
	ProductStock queryByProidAndDepotId(Long id, Long id2);
}
