package com._520it.ssh.service;


import java.util.List;

import com._520it.ssh.domain.Client;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface IClientService {

    void save(Client client);

    void update(Client client);

    void delete(Long id);

    Client get(Long id);

    List<Client> listAll();

    PageQuery<?> queryAndHigh(QueryObject qo);
}
