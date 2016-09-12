package com._520it.ssh.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 权限类
 * 主要用来对权限的一些字段
 *
 */


@Setter@Getter
public class Permission extends BaseDomain{
	private String name;//权限名称
	private String expression;//权限的表达式
	@Override
	public String toString() {
		return "Permission [name=" + name + ", expression=" + expression + "]";
	}	
}
