package com._520it.ssh.service;


import java.util.List;

import com._520it.ssh.domain.Brand;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface IBrandService {

    void save(Brand brand);

    void update(Brand brand);

    void delete(Long id);

    Brand get(Long id);

    List<Brand> listAll();

    PageQuery<?> queryAndHigh(QueryObject qo);
}
