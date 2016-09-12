package com._520it.ssh.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Setter;

import com._520it.ssh.dao.ISystemMenuDAO;
import com._520it.ssh.domain.Employee;
import com._520it.ssh.domain.SystemMenu;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.query.SystemMenuQueryObject;
import com._520it.ssh.service.ISystemMenuService;
import com._520it.ssh.vo.SystemMenuVO;
import com.opensymphony.xwork2.ActionContext;

public class SystemMenuServiceImpl implements ISystemMenuService {
    @Setter
    private ISystemMenuDAO systemMenuDAO;
    public void save(SystemMenu systemMenu) {
        systemMenuDAO.save(systemMenu);
    }

    public void update(SystemMenu systemMenu) {
        systemMenuDAO.update(systemMenu);
    }

    public void delete(Long id) {
    	SystemMenuQueryObject qo = new SystemMenuQueryObject();
    	qo.setParentId(id);
    	PageQuery<?> query = systemMenuDAO.queryAndHigh(qo);
    	if(query.getTotalCount()>0){
    		throw new RuntimeException("亲,您要删除的数据正在使用中....");
    	}
    	systemMenuDAO.delete(id);
    }

    public SystemMenu get(Long id) {
        return systemMenuDAO.get(id);
    }

    public List<SystemMenu> listAll() {
        return systemMenuDAO.listAll();
    }

	public PageQuery<?> queryAndHigh(QueryObject qo) {
		// TODO Auto-generated method stub
		return systemMenuDAO.queryAndHigh(qo);
	}

	@Override
	public List<SystemMenuVO> queryMenuByParentId(Long parentId) {
		
		List<SystemMenuVO> menus = new ArrayList<>();
		
		SystemMenu currentParent = systemMenuDAO.get(parentId);
		listParents(menus,currentParent);
		Collections.reverse(menus);
		return menus;
	}

	//进行迭代获取所有的子菜单 然后添加到list数组中去
	private void listParents(List<SystemMenuVO> menus, SystemMenu currentParent) {
		// TODO Auto-generated method stub
		if(currentParent !=null){
			SystemMenuVO vo = new SystemMenuVO();
			vo.setId(currentParent.getId());
			vo.setName(currentParent.getName());
			menus.add(vo);
			listParents(menus, currentParent.getParent());
		}
	}

	@Override
	public List<SystemMenu> queryByParentSn(String parentSn) {
		// TODO Auto-generated method stub
		Employee emp = (Employee) ActionContext.getContext().getSession().get("use_in_session");
    	if(emp.isAdmin()){
    		return systemMenuDAO.queryByParentSn(parentSn);
    	}else{
    		return systemMenuDAO.queryByParentSnAndRole(parentSn,emp.getRole());
    	}
	}
}
