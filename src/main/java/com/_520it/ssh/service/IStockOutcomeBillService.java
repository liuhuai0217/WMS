package com._520it.ssh.service;


import java.util.List;

import com._520it.ssh.domain.StockOutcomeBill;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface IStockOutcomeBillService {

    void save(StockOutcomeBill stockOutcomeBill);

    void update(StockOutcomeBill stockOutcomeBill);

    void delete(Long id);

    StockOutcomeBill get(Long id);

    List<StockOutcomeBill> listAll();

    PageQuery<?> queryAndHigh(QueryObject qo);

	/**
	 * 根据id进行审核出库单
	 * @param id
	 */
	void audit(Long id);
}
