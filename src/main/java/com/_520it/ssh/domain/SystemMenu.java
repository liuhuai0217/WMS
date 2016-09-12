package com._520it.ssh.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;


//菜单类 主要是包含了在数据库中的所有的菜单
@Setter@Getter
public class SystemMenu extends BaseDomain implements IJsonObject {
	private String name;//表示菜单的名称
	private String url;//表示菜单的url
	private String sn;//菜单编码
	//表关系,每一个父类都有多个子类
	//每一个子类都有很多的唯一的一个父类
	private SystemMenu parent;//一对多的关系
	private List<SystemMenu> child = new ArrayList<>();//多对一的关系 主要是用来处理多个子类对应一个父类的关系
	//实现IJSONObject的类 把数据转换成json的格式
	public Object toJson() {
		// TODO Auto-generated method stub
		Map<String,Object> json=  new HashMap<String, Object>();
		json.put("id",id);
		json.put("name",name);
		json.put("action",url);
		json.put("pid",this.parent!=null?this.parent.getId():null);
		return json;
	}
	
}
 