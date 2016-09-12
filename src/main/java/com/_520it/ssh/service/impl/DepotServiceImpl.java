package com._520it.ssh.service.impl;

import com._520it.ssh.dao.IDepotDAO;
import com._520it.ssh.domain.Depot;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IDepotService;
import lombok.Setter;

import java.util.List;

public class DepotServiceImpl implements IDepotService {
    @Setter
    private IDepotDAO depotDAO;
    public void save(Depot depot) {
        depotDAO.save(depot);
    }

    public void update(Depot depot) {
    	
        depotDAO.update(depot);
    }

    public void delete(Long id) {
        depotDAO.delete(id);
    }

    public Depot get(Long id) {
        return depotDAO.get(id);
    }

    public List<Depot> listAll() {
        return depotDAO.listAll();
    }

    public PageQuery<?> queryAndHigh(QueryObject qo) {
        return depotDAO.queryAndHigh(qo);
    }
}
