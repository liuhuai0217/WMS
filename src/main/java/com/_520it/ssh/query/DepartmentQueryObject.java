package com._520it.ssh.query;


public class DepartmentQueryObject extends QueryObject {
	public String customerCondition(){
		if(condition.size()==0){
			return "";
		}
		return null;
	}
}
