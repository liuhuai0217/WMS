package com._520it.ssh.query;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 *高级查询的类 主要是用来处理高级查询
 *
 */
public class QueryObject {

	//定义一个存放所有的条件的变量
	List<String> condition = new ArrayList<String>();
	//定义一个存放所有的条件的参数的变量
	List<Object> params = new ArrayList<Object>();
	@Setter@Getter
	private int currentPage=1;
	@Setter@Getter
	private int pageSize=10;
	//获取条件
	public String  customerCondition(){
		return "";
	}
	//获取参数列表
	public  List<Object> getParamate(){
		return params;
	}
}
