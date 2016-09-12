package com._520it.ssh.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装销售查询表的结果
 * 1分组的类型
 * 2销售的数量
 * 3销售总金额
 * 4销售的利润
 */
@Setter@Getter
public class SaleChartVO {
	private String groupType;//分组的类型
	private BigDecimal totalNumber;//订货总数量
	private BigDecimal totlalAmout;//订货的总金额
	private BigDecimal totalLirun;//
	public SaleChartVO(String groupType, BigDecimal totalNumber,
			BigDecimal totlalAmout,BigDecimal totalLirun) {
		super();
		this.groupType = groupType;
		this.totalNumber = totalNumber;
		this.totlalAmout = totlalAmout;
		this.totalLirun = totalLirun;
	}
}
