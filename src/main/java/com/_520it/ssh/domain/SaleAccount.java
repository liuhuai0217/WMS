package com._520it.ssh.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

//销售报表
@Setter
@Getter
public class SaleAccount extends BaseDomain{
	//主要包含的字段
	private Date vdate;//销售日期
	private BigDecimal number;//销售的数量
	private BigDecimal costPrice;//库存价格
	private BigDecimal costAmount;//库存总价格
	private BigDecimal salePrice;//销售价格
	private BigDecimal saleAmount;//销售总价格
	
	private Product product;//商品
	private Client client;//客户
	private Employee saleman;//销售人员
}
