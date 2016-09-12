package com._520it.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.ssh.dao.IProductStockDAO;
import com._520it.ssh.domain.ProductStock;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IProductStockService;

public class ProductStockServiceImpl implements IProductStockService {
    @Setter
    private IProductStockDAO productStockDAO;
    public void save(ProductStock productStock) {
        productStockDAO.save(productStock);
    }

    public void update(ProductStock productStock) {
        productStockDAO.update(productStock);
    }

    public void delete(Long id) {
        productStockDAO.delete(id);
    }

    public ProductStock get(Long id) {
        return productStockDAO.get(id);
    }

    public List<ProductStock> listAll() {
        return productStockDAO.listAll();
    }

    public PageQuery<?> queryAndHigh(QueryObject qo) {
        return productStockDAO.queryAndHigh(qo);
    }
}
