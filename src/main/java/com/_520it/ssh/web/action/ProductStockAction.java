package com._520it.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.ProductStock;
import com._520it.ssh.query.ProductStockQueryObject;
import com._520it.ssh.service.IBrandService;
import com._520it.ssh.service.IDepotService;
import com._520it.ssh.service.IProductStockService;
import com._520it.ssh.utils.RequiredPermission;

public class ProductStockAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
    private IProductStockService productStockService;
	
	@Setter
	private IBrandService brandService;
	
	@Setter
	private IDepotService depotService;

    @Getter
    private ProductStock productStock = new ProductStock();
    @Getter
    private ProductStockQueryObject qo = new ProductStockQueryObject();

    @RequiredPermission("商品库存列表")
    public String execute() throws Exception {
        try {
        
        	putContext("depots", depotService.listAll());
        	putContext("brands" ,brandService.listAll());
            putContext("pageResult", productStockService.queryAndHigh(qo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }
}
