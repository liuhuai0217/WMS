package com._520it.ssh.service.impl;

import lombok.Setter;

import com._520it.ssh.dao.ISaleAccountDAO;
import com._520it.ssh.service.ISaleAccountService;

public class SaleAccountServiceImpl implements ISaleAccountService {
 
	@Setter
	private ISaleAccountDAO saleAccountDAO;
}
