package com._520it.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.ssh.dao.ISupplierDAO;
import com._520it.ssh.domain.Supplier;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.ISupplierService;

public class SupplierServiceImpl implements ISupplierService {
    @Setter
    private ISupplierDAO supplierDAO;
    public void save(Supplier supplier) {
        supplierDAO.save(supplier);
    }

    public void update(Supplier supplier) {
        supplierDAO.update(supplier);
    }

    public void delete(Long id) {
        supplierDAO.delete(id);
    }

    public Supplier get(Long id) {
        return supplierDAO.get(id);
    }

    public List<Supplier> listAll() {
        return supplierDAO.listAll();
    }

    public PageQuery<?> queryAndHigh(QueryObject qo) {
        return supplierDAO.queryAndHigh(qo);
    }
}
