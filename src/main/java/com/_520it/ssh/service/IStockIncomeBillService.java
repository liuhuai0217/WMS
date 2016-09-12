package com._520it.ssh.service;


import java.util.List;

import com._520it.ssh.domain.StockIncomeBill;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface IStockIncomeBillService {

    void save(StockIncomeBill stockIncomeBill);

    void update(StockIncomeBill stockIncomeBill);

    void delete(Long id);

    StockIncomeBill get(Long id);

    List<StockIncomeBill> listAll();

    PageQuery<?> queryAndHigh(QueryObject qo);

	/**
	 * 进行入库单据审核,完成对入库单据审核 并更改数据库库存等
	 * @param id
	 */
	void audit(Long id);
}
