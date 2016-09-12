package com._520it.ssh.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author brilliant
 *
 */
@Setter@Getter
public class Role extends BaseDomain {
	private String name;//角色名称
	private String sn;//角色编码
	private List<Permission> permission = new ArrayList<Permission>();
	private List<SystemMenu> systemMenus = new ArrayList<>();

}
