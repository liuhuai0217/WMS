package com._520it.ssh.query;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

public class EmployeeQueryObject extends QueryObject {
	@Override
	public String toString() {
		return "EmployeeQueryObject [keyword=" + keyword + ", dept_id="
				+ dept_id + "]";
	}

	@Setter@Getter
	public String keyword;
	@Setter@Getter
	public Long dept_id=-1L;//部门的编号
	
	public String customerCondition(){
		if(keyword!=null&&!"".equals(keyword.trim())){
			condition.add("   (obj.name  like ?  or  obj.email like ?)  ");
			params.add("%"+keyword+"%");
			params.add("%"+keyword+"%");
		}if(dept_id>0){
			condition.add(" obj.dept.id= ?  ");
			params.add(dept_id);
		}if(condition.size()==0){
			return "";
		}
		return "  WHERE " + StringUtils.join(condition, "  AND ");
	}
}
