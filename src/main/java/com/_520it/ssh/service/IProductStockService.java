package com._520it.ssh.service;


import java.util.List;

import com._520it.ssh.domain.ProductStock;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface IProductStockService {

    void save(ProductStock productStock);

    void update(ProductStock productStock);

    void delete(Long id);

    ProductStock get(Long id);

    List<ProductStock> listAll();

    PageQuery<?> queryAndHigh(QueryObject qo);
}
