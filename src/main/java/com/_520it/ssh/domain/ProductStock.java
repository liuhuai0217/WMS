package com._520it.ssh.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

//库存类,主要
@Setter@Getter
public class ProductStock extends BaseDomain{
	
	private Product product;//存放到仓库中的商品信息
	private Depot depot;//商品所存放的仓库
	private BigDecimal stroeNumber;//数据库中库存的数量
	private BigDecimal price;//商品的价格(库存价格)
	private BigDecimal amount;//在数据库中的总金额
	
}
