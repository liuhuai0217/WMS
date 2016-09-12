package com._520it.ssh.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import com._520it.ssh.dao.IProductStockDAO;
import com._520it.ssh.dao.ISaleAccountDAO;
import com._520it.ssh.dao.IStockOutcomeBillDAO;
import com._520it.ssh.domain.Employee;
import com._520it.ssh.domain.ProductStock;
import com._520it.ssh.domain.SaleAccount;
import com._520it.ssh.domain.StockIncomeBill;
import com._520it.ssh.domain.StockOutcomeBill;
import com._520it.ssh.domain.StockOutcomeBillItem;
import com._520it.ssh.query.PageQuery;
import com._520it.ssh.query.QueryObject;
import com._520it.ssh.service.IStockOutcomeBillService;
import com.opensymphony.xwork2.ActionContext;

public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
    @Setter
    private IStockOutcomeBillDAO stockOutcomeBillDAO;
    
    
    @Setter
    private ISaleAccountDAO saleAccountDAO;
    @Setter
    private IProductStockDAO productStockDAO;
    public void save(StockOutcomeBill bill) {
    	//设置制单人和制单时间
    	bill.setInputUser((Employee)ActionContext.getContext().getSession().get("use_in_session"));
    	bill.setInputTime(new Date());
    	//重新设置出库单的状态
    	parse(bill);
        stockOutcomeBillDAO.save(bill);
    }

	private void parse(StockOutcomeBill bill) {
		bill.setStatus(StockOutcomeBill.NORMAL);
    	//关系
    	bill.setTotalAmout(BigDecimal.ZERO);
    	bill.setTotalNumber(BigDecimal.ZERO);
    	for(StockOutcomeBillItem item:bill.getItems()){
    		item.setBill(bill);
    		//存储每条商品的总金额
    		
    		item.setAmount(item.getNumber().multiply(item.getSalePrice()).setScale(2, RoundingMode.HALF_UP));
    		bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
    		bill.setTotalAmout(bill.getTotalAmout().add(item.getAmount()));
    		
    	}
	}

    public void update(StockOutcomeBill bill) {
    	parse(bill);
        stockOutcomeBillDAO.update(bill);
    }

    public void delete(Long id) {
        stockOutcomeBillDAO.delete(id);
    }

    public StockOutcomeBill get(Long id) {
        return stockOutcomeBillDAO.get(id);
    }

    public List<StockOutcomeBill> listAll() {
        return stockOutcomeBillDAO.listAll();
    }

    public PageQuery<?> queryAndHigh(QueryObject qo) {
        return stockOutcomeBillDAO.queryAndHigh(qo);
    }

	@Override
	public void audit(Long id) {
		//获取入库单据信息
				StockOutcomeBill bill = stockOutcomeBillDAO.get(id);
		    	
		    		//判断是否拥有该单据
		    	if(bill!=null){
		    		//更改状态
		    		if(bill.getStatus()==StockIncomeBill.NORMAL){    
		    			//1 绑定审核人和审核时间
		    	    	bill.setStatus(StockIncomeBill.AUDIT);
		    	    	bill.setAuditor((Employee)ActionContext.getContext().getSession().get("use_in_session"));
		    	    	bill.setAuditTime(new Date());
		    			//进行遍历该入库单的详细信息
		    			for (StockOutcomeBillItem item : bill.getItems()) {
		    				//根据商品编号和仓库编号进行查询  
		    				//System.out.println("获取商品的id"+item.getProduct().getId());
		    				//System.out.println("获取仓库的id"+bill.getDepot().getId());
		    				ProductStock produtstock = productStockDAO.queryByProidAndDepotId(item.getProduct().getId(),bill.getDepot().getId());
		    				if(produtstock==null||produtstock.getStroeNumber().compareTo(item.getNumber())<0){
		    						//提示
		    					throw new RuntimeException(item.getProduct().getName()+"库存不足");
		    				}else{
		    					produtstock.setStroeNumber(produtstock.getStroeNumber().subtract(item.getNumber()));
		    					produtstock.setAmount(produtstock.getStroeNumber().multiply(produtstock.getPrice()));
		    					productStockDAO.update(produtstock);
		    					//当进行完毕出口库后
		    					//对每一条交易记录进行录入到数据库
		    					SaleAccount sale = new SaleAccount();
		    					sale.setVdate(bill.getVdate());//存储业务时间
		    					sale.setNumber(item.getNumber());//获取商品销售的数量
		    					sale.setCostPrice(item.getProduct().getCostPrice());//获取商品的成本价
		    					sale.setSalePrice(item.getSalePrice());//获取的商品的销售价
		    					//商品如果在数据库中 的价格
		    					sale.setCostAmount(item.getNumber().multiply(item.getProduct().getCostPrice()).setScale(2, RoundingMode.HALF_UP));
		    					//商品销售的总价
		    					sale.setSaleAmount(item.getNumber().multiply(item.getSalePrice()).setScale(2, RoundingMode.HALF_UP));
		    					sale.setProduct(item.getProduct());
		    					sale.setClient(item.getBill().getClient());
		    					sale.setSaleman(item.getBill().getInputUser());
		    					saleAccountDAO.save(sale);
		    			}
		    				
		    			stockOutcomeBillDAO.update(bill);
		    		}
		    	}
		    }
		
	}
}
