package com._520it.ssh.service;

import java.util.List;

import com._520it.ssh.domain.Role;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface IRoleService {
    void save(Role dept);
    void delete(Long id);
    void update(Role dept);
    Role get(Long id);
    List<Role> listAll();
    PageQuery<?> queryAndHigh(QueryObject qo);
}
