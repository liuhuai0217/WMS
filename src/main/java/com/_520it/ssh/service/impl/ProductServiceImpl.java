package com._520it.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.ssh.dao.IProductDAO;
import com._520it.ssh.domain.Product;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IProductService;

public class ProductServiceImpl implements IProductService {
    @Setter
    private IProductDAO productDAO;
    public void save(Product product) {
   
        productDAO.save(product);
    }

    public void update(Product product) {
        productDAO.update(product);
    }

    public void delete(Long id) {
        productDAO.delete(id);
    }

    public Product get(Long id) {
        return productDAO.get(id);
    }

    public List<Product> listAll() {
        return productDAO.listAll();
    }

    public PageQuery<?> queryAndHigh(QueryObject qo) {
        return productDAO.queryAndHigh(qo);
    }
}
