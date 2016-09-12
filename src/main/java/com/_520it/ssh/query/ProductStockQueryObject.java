package com._520it.ssh.query;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStockQueryObject extends QueryObject {
	
	public String keyword;//客户录入的货品条件
	
	public Long depot=-1L;//客户录入的仓库条件
	
	public Long brand =-1L;//客户录入的品牌条件
	
	public BigDecimal storeLimit;//客户录入的限制
    public  String  customerCondition(){
        if(keyword!=null&&!"".equals(keyword.trim())){
        	condition.add("  obj.product.sn like ?  ");
        	params.add("%"+keyword+"%");
        }if(depot>0){
        	condition.add("  obj.depot.id = ?  ");
        	params.add(depot);
        }if(brand>0){
        	condition.add("  obj.product.brand.id = ?  ");
        	params.add(brand);
        }if(storeLimit!=null){
        	condition.add(" obj.stroeNumber > 0  ");
        	condition.add(" obj.stroeNumber < ?  ");
        	params.add(storeLimit);
        }if(condition.size()==0){
        	return "";
        }
        return "  WHERE " + StringUtils.join(condition, "  AND  ");
    }
}
