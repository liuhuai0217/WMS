package com._520it.ssh.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装订货查询表的结果
 * 1分组的类型
 * 2订货的数量
 * 3订货的总金额
 */
@Setter@Getter
public class OrderChartVO {
	private String groupType;//分组的类型
	private BigDecimal totalNumber;//订货总数量
	private BigDecimal totlalAmout;//订货的总金额
	public OrderChartVO(String groupType, BigDecimal totalNumber,
			BigDecimal totlalAmout) {
		super();
		this.groupType = groupType;
		this.totalNumber = totalNumber;
		this.totlalAmout = totlalAmout;
	}
}
