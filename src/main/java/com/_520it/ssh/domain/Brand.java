package com._520it.ssh.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//品牌表
@Setter@Getter@ToString
public class Brand extends BaseDomain{
	private String name;//分类名
	private String sn;
}
