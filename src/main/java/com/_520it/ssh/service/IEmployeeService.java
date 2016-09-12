package com._520it.ssh.service;

import java.util.List;

import com._520it.ssh.domain.Employee;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;

public interface IEmployeeService {
    /**
     * 保存员工信息
     * @param employee
     */
    void save(Employee employee);
    /**
     * 删除员工信息
     * @param employee
     */
    void delete(Long id);
    /**
     * 更新员工信息
     * @param employee
     */
    void update(Employee employee);
    /**
     *根据某个编号 获取摸个员工的信息
     * @param employee
     * @return
     */
    Employee get(Long id);
    /**
     * 查询出所有的员工信息
     * @return
     */
    List<Employee> listAll();
    
    List<Employee> query(QueryObject obj);
    
    List<Employee> queryByPage(int currentPage,int pageSize);
    
    PageQuery<?> queryAndHigh(QueryObject qo);

	void  checkLogin(String username, String password);
	
	void batchDelete(List<Long> ids);
}
