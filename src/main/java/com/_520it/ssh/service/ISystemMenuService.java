package com._520it.ssh.service;


import java.util.List;

import com._520it.ssh.domain.SystemMenu;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.vo.SystemMenuVO;

public interface ISystemMenuService {

    void save(SystemMenu systemMenu);

    void update(SystemMenu systemMenu);

    void delete(Long id);

    SystemMenu get(Long id);

    List<SystemMenu> listAll();

    PageQuery<?> queryAndHigh(QueryObject qo);
    
    List<SystemMenuVO> queryMenuByParentId(Long parentId);

	List<SystemMenu> queryByParentSn(String parentSn);
}
