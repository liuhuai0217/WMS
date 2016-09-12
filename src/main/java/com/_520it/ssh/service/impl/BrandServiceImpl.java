package com._520it.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.ssh.dao.IBrandDAO;
import com._520it.ssh.domain.Brand;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IBrandService;

public class BrandServiceImpl implements IBrandService {
    @Setter
    private IBrandDAO brandDAO;
    public void save(Brand brand) {
        brandDAO.save(brand);
    }

    public void update(Brand brand) {
        brandDAO.update(brand);
    }

    public void delete(Long id) {
        brandDAO.delete(id);
    }

    public Brand get(Long id) {
        return brandDAO.get(id);
    }

    public List<Brand> listAll() {
        return brandDAO.listAll();
    }

    public PageQuery<?> queryAndHigh(QueryObject qo) {
        return brandDAO.queryAndHigh(qo);
    }
}
