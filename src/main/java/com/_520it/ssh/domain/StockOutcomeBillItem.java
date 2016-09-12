package com._520it.ssh.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//出库单据明细
@Setter@Getter@ToString
public class StockOutcomeBillItem extends BaseDomain {
	private Product product;//出库的商品
	private BigDecimal salePrice;//出库的商品的价格
	private BigDecimal number;//出库的数量
	private BigDecimal amount;//某个商品出库的资金
	private String remark;//备注
	private StockOutcomeBill bill;//所属的单据
}
