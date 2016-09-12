package com._520it.ssh.query;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQueryObject extends QueryObject {
	private String keyword;
	private Long brandid =-1L;
    public String customerCondition() {
    	if(keyword!=null&&!"".equals(keyword.trim())){
			condition.add("   (obj.name  like ? )  ");
			params.add("%"+keyword+"%");
	    }if(brandid>0){
			condition.add(" obj.brand.id= ?  ");
			params.add(brandid);
		}if(condition.size()==0){
			return "";
		}
		return "  WHERE " + StringUtils.join(condition, "  AND ");
    }
}
