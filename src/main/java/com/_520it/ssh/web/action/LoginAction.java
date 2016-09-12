package com._520it.ssh.web.action;

import lombok.Setter;

import com._520it.ssh.service.IEmployeeService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Setter
	private String username;
	@Setter
	private String password;
	@Setter
	private IEmployeeService employeeService; 
	@Override
	@InputConfig(resultName = "login")
	public String execute() {
		// TODO Auto-generated method stub
		System.out.println("gagaga");
		try {			
			employeeService.checkLogin(username,password);
		} catch (RuntimeException e) {
			// TODO: handle exception
			super.addActionError(e.getMessage());
			return LOGIN;
		}
		return SUCCESS;
	}
}
