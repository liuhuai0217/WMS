package com._520it.ssh.service.impl;

import com._520it.ssh.dao.IClientDAO;
import com._520it.ssh.domain.Client;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IClientService;
import lombok.Setter;

import java.util.List;

public class ClientServiceImpl implements IClientService {
    @Setter
    private IClientDAO clientDAO;
    public void save(Client client) {
        clientDAO.save(client);
    }

    public void update(Client client) {
        clientDAO.update(client);
    }

    public void delete(Long id) {
        clientDAO.delete(id);
    }

    public Client get(Long id) {
        return clientDAO.get(id);
    }

    public List<Client> listAll() {
        return clientDAO.listAll();
    }

    public PageQuery<?> queryAndHigh(QueryObject qo) {
        return clientDAO.queryAndHigh(qo);
    }
}
