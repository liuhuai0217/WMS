package com._520it.ssh.web.action;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.Product;
import com._520it.ssh.query.ProductQueryObject;
import com._520it.ssh.service.IBrandService;
import com._520it.ssh.service.IProductService;
import com._520it.ssh.utils.FileUploadUtil;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ProductAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Setter
	private IBrandService brandService;
    @Setter
    private IProductService productService;

    @Getter
    private Product product = new Product();

    //对于上传的图片的有关信息
    @Setter
    private File pic;//上传的图片的路径
    @Setter
    private String picFileName;//上传的图片的名称
    @Getter
    private ProductQueryObject qo = new ProductQueryObject();

    @RequiredPermission("商品列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
    	System.out.println(qo.getKeyword());
    	System.out.println(qo.getBrandid());
        try {
        	putContext("brands", brandService.listAll());
        	putContext("pageResult", productService.queryAndHigh(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("商品编辑")
    public String input() throws Exception {
    	putContext("brands", brandService.listAll());
        if (product.getId() != null) {
            product = productService.get(product.getId());
        }
        return INPUT;
    }

    @RequiredPermission("商品删除")
    public String delete() throws Exception {
        if (product.getId() != null) {
        	product  = productService.get(product.getId());
        	if(product.getImagePath()!=null){        		
        		FileUploadUtil.deleteFile(product.getImagePath());
        	}
            productService.delete(product.getId());
            putResponseText("删除成功");
        }
        return NONE;
    }

    @RequiredPermission("商品保存或更新")
    public String saveOrUpdate() throws Exception {
    	//判断是更新或者保存
    	try {
    		if(product.getId()!=null&&pic!=null){
    			  FileUploadUtil.deleteFile(product.getImagePath());
    		}
    		if(pic!=null){    			
    			String imgpath = FileUploadUtil.uploadFile(pic, picFileName);
    			product.setImagePath(imgpath);
    		}
    		if(product.getId()==null){
    			productService.save(product);
    			addActionMessage("保存成功");
    		}else{
    			productService.update(product);
    			addActionMessage("修改成功");
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
    	//下面进行判断  如果商品的id不为空 或者图片路径不为空时
        return SUCCESS;
    }
    
    //调取到商品信息列表
    public String selectProductList(){
    	putContext("pageResult", productService.queryAndHigh(qo));
    	putContext("brands",brandService.listAll());
    	return "selectProductList";
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (product.getId() != null) {
            product = productService.get(product.getId());
        }
    }
}
