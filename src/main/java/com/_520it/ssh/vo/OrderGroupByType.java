package com._520it.ssh.vo;

import lombok.Getter;

@Getter
public enum OrderGroupByType {
	EMPLOYEE("obj.bill.inputUser.name","obj.bill.inputUser","订货人员"), 
	PRODUCT("obj.product.name","obj.product","货品名称"),
	SUPPLIER("obj.bill.supplier.name","obj.bill.supplier","供应商"),
	BRAND("obj.product.brand.name","obj.product.brand","品牌名称"),
	MONTH("date_format(obj.bill.vdate,'%Y-%m')","date_format('obj.bill.vdate','%Y-%m')","订货日期(月)"),
	DAY("date_format(obj.bill.vdate,'%Y-%m-%d')","date_format('obj.bill.vdate','%Y-%m-%d')","订货日期(日)");
	private OrderGroupByType(String groupValue,String groupBy,String groupType){
		this.groupValue = groupValue;
		this.groupBy = groupBy;
		this.groupType = groupType;
	}
	private String groupType;//分组的类型
	private String groupBy;//订货总数量
	private String groupValue;//订货的总金额
}
