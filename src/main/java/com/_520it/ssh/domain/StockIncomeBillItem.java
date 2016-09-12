package com._520it.ssh.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//入库单据明细
@Setter@Getter@ToString
public class StockIncomeBillItem extends BaseDomain {
	private Product product;//入库的商品
	private BigDecimal costPrice;//入库的商品的成本价格
	private BigDecimal number;//入库的数量
	private BigDecimal amount;//某个商品 存入库时的资金
	private String remark;//备注
	private StockIncomeBill bill;//所属的单据
}
