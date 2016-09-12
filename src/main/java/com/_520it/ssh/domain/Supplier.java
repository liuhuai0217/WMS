package com._520it.ssh.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class Supplier extends BaseDomain{
	private String name;//供货商的名称
	private String phone;//供货商电话
	private String address;//供货商的地址
	
}
