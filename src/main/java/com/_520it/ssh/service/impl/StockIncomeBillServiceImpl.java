package com._520it.ssh.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import com._520it.ssh.dao.IProductStockDAO;
import com._520it.ssh.dao.IStockIncomeBillDAO;
import com._520it.ssh.domain.Employee;
import com._520it.ssh.domain.ProductStock;
import com._520it.ssh.domain.StockIncomeBill;
import com._520it.ssh.domain.StockIncomeBillItem;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IStockIncomeBillService;
import com.opensymphony.xwork2.ActionContext;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
	
	@Setter
	private IProductStockDAO productStockDAO;
    @Setter
    private IStockIncomeBillDAO stockIncomeBillDAO;
    public void save(StockIncomeBill bill) {
    	//1 绑定制单人和制单时间
    	bill.setInputUser((Employee)ActionContext.getContext().getSession().get("use_in_session"));
    	bill.setInputTime(new Date());
    	parese(bill);
        stockIncomeBillDAO.save(bill);
    }

	private void parese(StockIncomeBill bill) {
		//2,重新设置状态
    	bill.setStatus(StockIncomeBill.NORMAL);
    	//3.处理关系
    	bill.setTotalNumber(BigDecimal.ZERO);
    	bill.setTotalAmout(BigDecimal.ZERO);
    	//3,处理单据和明细之间的关系
    	for (StockIncomeBillItem item : bill.getItems()) {
			item.setBill(bill);
			//4,计算单挑明细的小计
			item.setAmount(item.getNumber().multiply(item.getCostPrice()).setScale(2, RoundingMode.HALF_UP));
			//5,计算单据的总数量和总金额
			bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
			
			System.out.println("仓库库存总额"+bill.getTotalNumber());
			bill.setTotalAmout(bill.getTotalAmout().add(item.getAmount()));
			System.out.println("仓库库存金额总额"+bill.getTotalAmout());
    	}
	}

    public void update(StockIncomeBill stockIncomeBill) {
    	parese(stockIncomeBill);
        stockIncomeBillDAO.update(stockIncomeBill);
    }

    public void delete(Long id) {
        stockIncomeBillDAO.delete(id);
    }

    public StockIncomeBill get(Long id) {
        return stockIncomeBillDAO.get(id);
    }

    public List<StockIncomeBill> listAll() {
        return stockIncomeBillDAO.listAll();
    }

	public PageQuery<?> queryAndHigh(QueryObject qo) {
        return stockIncomeBillDAO.queryAndHigh(qo);
    }

	@Override
	public void audit(Long id) {
		//获取入库单据信息
		StockIncomeBill bill = stockIncomeBillDAO.get(id);
    	
    		//判断是否拥有该单据
    	if(bill!=null){
    		//更改状态
    		if(bill.getStatus()==StockIncomeBill.NORMAL){    
    			//1 绑定审核人和审核时间
    	    	bill.setAuditor((Employee)ActionContext.getContext().getSession().get("use_in_session"));
    	    	bill.setAuditTime(new Date());
    	    	bill.setStatus(StockIncomeBill.AUDIT);
    			//进行遍历该入库单的详细信息
    			for (StockIncomeBillItem item : bill.getItems()) {
    				//根据商品编号和仓库编号进行查询
    				//System.out.println("获取商品的id"+item.getProduct().getId());
    				//System.out.println("获取仓库的id"+bill.getDepot().getId());
    				ProductStock produtstock=null;
    				produtstock = productStockDAO.queryByProidAndDepotId(item.getProduct().getId(),bill.getDepot().getId());
					if(produtstock!=null){
						//存商品
    					produtstock.setProduct(item.getProduct());
    					//存仓库
    					produtstock.setDepot(bill.getDepot());
    					//存金额
    					produtstock.setAmount(item.getAmount().add(produtstock.getAmount()));
    					//存数量
    					produtstock.setStroeNumber(item.getNumber().add(produtstock.getStroeNumber()));
    					//库存价格
    					produtstock.setPrice(produtstock.getAmount().divide(produtstock.getStroeNumber(),2,RoundingMode.HALF_UP));
    					productStockDAO.update(produtstock);
    				}else{
    					produtstock = new ProductStock();
    					//存商品
    					produtstock.setProduct(item.getProduct());
    					//存仓库
    					produtstock.setDepot(bill.getDepot());
    					//存价格
    					produtstock.setPrice(item.getCostPrice());
    					//存金额
    					produtstock.setAmount(item.getAmount());
    					//存数量
    					produtstock.setStroeNumber(item.getNumber());
    					
    					productStockDAO.save(produtstock);
    				}
    			}
    		}
    	}
	}
}
