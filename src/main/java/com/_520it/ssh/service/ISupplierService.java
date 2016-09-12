package com._520it.ssh.service;


import java.util.List;

import com._520it.ssh.domain.Supplier;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface ISupplierService {

    void save(Supplier supplier);

    void update(Supplier supplier);

    void delete(Long id);

    Supplier get(Long id);

    List<Supplier> listAll();

    PageQuery<?> queryAndHigh(QueryObject qo);
}
