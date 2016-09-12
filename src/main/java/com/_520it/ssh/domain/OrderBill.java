package com._520it.ssh.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


//采购订单表
@Setter@Getter
public class OrderBill extends BaseDomain {
	public static final int NORMAL = 0;
	public static final int AUDIT = 1;
	private String sn;//单据编号,可以自动生成,也可以手动输入
	private Date vdate;//业务时间
	private int status = OrderBill.NORMAL;//单据的审核状态,默认为未审核
	private BigDecimal totalNumber;//采购的总数
	private BigDecimal totalAmout;//采购的总价格
	private Supplier supplier;//供应商表
	private Employee inputUser;//制单人
	private Date inputTime;//制单时间
	private Employee auditor;//审核人
	private Date auditTime;//审核时间
	
	private List<OrderBillItem> items = new ArrayList<>();//采购单的明细
}
