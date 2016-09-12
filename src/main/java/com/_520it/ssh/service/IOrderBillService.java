package com._520it.ssh.service;


import java.util.List;

import com._520it.ssh.domain.OrderBill;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface IOrderBillService {

    void save(OrderBill orderBill);

    void update(OrderBill orderBill);

    void delete(Long id);

    OrderBill get(Long id);

    List<OrderBill> listAll();

    PageQuery<?> queryAndHigh(QueryObject qo);
    
    /**
     * 根据采购单进行获取采购单 然后进行审核 完成审核
     * @param id
     */
	void audit(Long id);
}
