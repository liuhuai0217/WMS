package com._520it.ssh.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemMenuQueryObject extends QueryObject {
	
	private Long parentId = -1L;
	private String parentSn;//记录了数据库中主菜单的编号
    public String customerCondition() {
    	if(parentId>0){
    		condition.add(" obj.parent.id = ?");  
    		params.add(parentId);
    	}else{
    		condition.add(" obj.parent IS NULL");  
    	}
    	String aa = condition.get(0).toString();
    	return "  WHERE " + aa;
    }
}
