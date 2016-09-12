package com._520it.ssh.query;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

import com._520it.ssh.utils.DateUtil;
import com._520it.ssh.vo.SaleGroupByType;

@Getter
@Setter
public class SaleAccountQueryObject extends QueryObject {
	private Date beginTime;//查询的开始时间
	private Date endTime;//查询的结束时间
	private String keyWord;//查询的货品
	private Long client=-1L;//客户
	private Long brand =-1L;//查询的品牌
	private SaleGroupByType groupType=SaleGroupByType.Employee;//查询的类型
	//进行拼接字符串,拼接所有的条件语句
	public String customerCondition(){
		if(beginTime!=null){
			condition.add(" obj.vdate >= ? ");
			params.add(DateUtil.beginTimeOfDay(beginTime));
		}
		if(endTime!=null){
			condition.add(" obj.vdate <= ? ");
			params.add(DateUtil.endTimeOfDay(endTime));
		}if(keyWord!=null&&!"".equals(keyWord.trim())){
			condition.add("obj.product.sn like ? ");
			params.add("%"+keyWord+"%");
		}
		if(client>0){
    		condition.add("  obj.client.id = ?  ");
    		params.add(client);
    	}
    	if(brand>=0){
    		condition.add("  obj.product.brand.id = ?  ");
    		params.add(brand);
    	}if(condition.size()==0){
    		return "";
    	}
    	//查询所有的满足条件的,根据要求需要设定为已经审核了的
    	//所以在此时
    	
    	return "  WHERE " + StringUtils.join(condition, "  AND ");
	}
}
