package com._520it.ssh.query;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com._520it.ssh.utils.DateUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockOutcomeBillQueryObject extends QueryObject {
	public Long depotId = -1L;//供应商的id
	public Date beginTime;//查询的开始时间
	public Date endTime;//查询的结束时间
	public int status=-1;//采购单的状态 
    public String customerCondition() {
    	if(depotId>0){
    		condition.add("  obj.depot.id = ?  ");
    		params.add(depotId);
    	}
    	if(status>=0){
    		condition.add("  obj.status = ?  ");
    		params.add(status);
    	}
    	if(beginTime!=null){
    		condition.add(" obj.vdate >= ? ");
    		
    		params.add(DateUtil.beginTimeOfDay(beginTime));
    	}
    	if(endTime!=null){
    		condition.add(" obj.vdate <= ? ");
    		params.add(DateUtil.endTimeOfDay(endTime));
    	}
    	if(condition.size()==0){
			return "";
    	}
		return "  WHERE " + StringUtils.join(condition, "  AND  ");
	}
}
