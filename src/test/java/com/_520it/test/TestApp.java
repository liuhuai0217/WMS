package com._520it.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com._520it.ssh.domain.Supplier;
import com._520it.ssh.domain.SystemMenu;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.SystemMenuQueryObject;
import com._520it.ssh.service.IBrandService;
import com._520it.ssh.service.IEmployeeService;
import com._520it.ssh.service.ISupplierService;
import com._520it.ssh.service.ISystemMenuService;
import com._520it.ssh.service.impl.BrandServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestApp {
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IBrandService  brandService;
	@Autowired
	private ISystemMenuService systemMenuService;
	
	@Autowired
	private ISupplierService supplierService;
	@Test
	public void test(){
		employeeService.batchDelete(Arrays.asList(11L,12L,13L));
	}
	@Test
	public void test2(){
		SystemMenu e = systemMenuService.get(1L);
		System.out.println(e);
	}
	@Test
	public void test3(){
		SystemMenuQueryObject qo = new SystemMenuQueryObject();
		PageQuery<SystemMenu> pages = (PageQuery<SystemMenu>) systemMenuService.queryAndHigh(qo);
		System.out.println(pages);
	}
	@Test
	public void test4(){
		List<Supplier> list  = supplierService.listAll();
		for (Supplier supplier : list) {
			System.out.println(supplier);
		}
	}
	@Test
	public void test5(){
		System.out.println(brandService.listAll());
	}

}
