package com._520it.ssh.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * Created by Administrator on 2016/8/7 0007.
 */

@Setter@Getter
public class Employee extends BaseDomain{
    private String name;
    private String password;
	private String email;
    private int age;
    private boolean admin = false;
    private Department dept;
    private List<Role> role = new ArrayList<Role>();
    
    public String getRoleName(){
    	StringBuffer sb = new StringBuffer().append("[");
    	if(this.admin){
    		return"[超级管理员]";
    	}if(this.role.size()==0){
    		return "[暂无角色]";
    	}
    	for (Role re : role) {
			sb.append(re.getName()).append(",");
		}
    	sb.deleteCharAt(sb.length()-1);
    	sb.append("]");
    	return sb.toString();
    }
}
