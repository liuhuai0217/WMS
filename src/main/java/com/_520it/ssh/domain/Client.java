package com._520it.ssh.domain;

import lombok.Getter;
import lombok.Setter;
/**
 * 客户表
 *
 */
@Setter@Getter
public class Client extends BaseDomain{
	private String name;
	private String sn;
	private String phone;
}
