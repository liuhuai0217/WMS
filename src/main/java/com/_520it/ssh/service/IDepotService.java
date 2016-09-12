package com._520it.ssh.service;


import java.util.List;

import com._520it.ssh.domain.Depot;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface IDepotService {

    void save(Depot depot);

    void update(Depot depot);

    void delete(Long id);

    Depot get(Long id);

    List<Depot> listAll();

    PageQuery<?> queryAndHigh(QueryObject qo);
}
