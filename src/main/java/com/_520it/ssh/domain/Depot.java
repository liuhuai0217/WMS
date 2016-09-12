package com._520it.ssh.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//仓库表
@Setter@Getter@ToString
public class Depot  extends BaseDomain{
	private String name;//所属的地区
	private String address;//仓库所在的地址
}
