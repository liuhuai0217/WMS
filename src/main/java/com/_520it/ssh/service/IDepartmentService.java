package com._520it.ssh.service;

import java.util.List;

import com._520it.ssh.domain.Department;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface IDepartmentService {
    void save(Department dept);

    void delete(Long id);
   
    void update(Department dept);
   
    Department get(Long id);

    List<Department> listAll();
    
    PageQuery<?> queryAndHigh(QueryObject qo);
}
