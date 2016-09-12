package com._520it.ssh.vo;

import lombok.Getter;

@Getter
public enum SaleGroupByType {
	Employee("obj.saleman.name","obj.saleman","销售人员"), 
	PRODUCT("obj.product.name","obj.product","货品名称"),
	CLIENT("obj.client.name","obj.client","客户"),
	BRAND("obj.product.brand.name","obj.product.brand","品牌名称"),
	MONTH("date_format(obj.vdate,'%Y-%m')","date_format('obj.vdate','%Y-%m')","订货日期(月)"),
	DAY("date_format(obj.vdate,'%Y-%m-%d')","date_format('obj.vdate','%Y-%m-%d')","订货日期(日)");
	private SaleGroupByType(String groupValue,String groupBy,String groupType){
		this.groupValue = groupValue;
		this.groupBy = groupBy;
		this.groupType = groupType;
	}
	private String groupType;//分组的类型
	private String groupBy;//订货总数量
	private String groupValue;//订货的总金额
}
