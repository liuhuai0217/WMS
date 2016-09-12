package com._520it.ssh.service;


import java.util.List;

import com._520it.ssh.domain.Product;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface IProductService {

    void save(Product product);

    void update(Product product);

    void delete(Long id);

    Product get(Long id);

    List<Product> listAll();

    PageQuery<?> queryAndHigh(QueryObject qo);
}
