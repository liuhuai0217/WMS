package com._520it.ssh.service.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Setter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com._520it.ssh.dao.impl.PermissionDAOImpl;
import com._520it.ssh.domain.Permission;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IPermissionService;
import com._520it.ssh.utils.PermissionUtil;
import com._520it.ssh.utils.RequiredPermission;
import com._520it.ssh.web.action.BaseAction;

public class PermissionServiceImpl implements IPermissionService,ApplicationContextAware{

	@Setter
	private PermissionDAOImpl permissionDAO;
	private ApplicationContext ctx;

	public void load() {
		//在数据库中获取所有的权限
		List<Permission>ps = permissionDAO.listAll();
		System.out.println("ps"+ps);
		//定义一个set集合
		Set<String> expression = new HashSet<String>();
		for (Permission p : ps) {
			expression.add(p.getExpression());
		}
		Map<String,BaseAction> beansMap = ctx.getBeansOfType(BaseAction.class);
		Collection<BaseAction> actions = beansMap.values();
		for(BaseAction action:actions){
			Method[] ms = action.getClass().getDeclaredMethods();
			for(Method m:ms){
				//获取方法上的注解
				System.out.println(m);
				RequiredPermission rp = m.getAnnotation(RequiredPermission.class);
				 String exp = PermissionUtil.buildExpression(m);//获取该方法对应的权限表达式
		            if (!expression.contains(exp)) {
		                if (rp != null) {
		                    String name = rp.value();//权限名称

		                    Permission p = new Permission();
		                    p.setName(name);
		                    p.setExpression(exp);
		                    permissionDAO.save(p);
		                }
		            }
				}	
			}
		}
	

	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}
	public List<Permission> listAll() {
		return permissionDAO.listAll();
	}


	public void delete(Long id) {
		// TODO Auto-generated method stub
		permissionDAO.delete(id);
	}


	public Permission get(Long id) {
		// TODO Auto-generated method stub
		
		return permissionDAO.get(id);
	}


	public PageQuery<?> queryAndHigh(QueryObject qo) {
		// TODO Auto-generated method stub
		
		return permissionDAO.queryAndHigh(qo);
	}

}
