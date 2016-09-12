package com._520it.ssh.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//采购的明细
@Setter@Getter@ToString
public class OrderBillItem extends BaseDomain {
	private Product product;//采购的商品
	private BigDecimal costPrice;//采购的商品的成本价格
	private BigDecimal number;//采购的数量
	private BigDecimal amount;//对某个商品采购用的金额
	private String remark;//备注
	
	private OrderBill bill;//所属的单据
	
}
