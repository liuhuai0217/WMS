package com._520it.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.Brand;
import com._520it.ssh.query.BrandQueryObject;
import com._520it.ssh.service.IBrandService;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class BrandAction extends BaseAction {


	private static final long serialVersionUID = 1L;

	@Setter
    private IBrandService brandService;

    @Getter
    private Brand brand = new Brand();

    @Getter
    private BrandQueryObject qo = new BrandQueryObject();

    @RequiredPermission("商品类别列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            putContext("pageResult", brandService.queryAndHigh(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("商品类别编辑")
    public String input() throws Exception {
        if (brand.getId() != null) {
            brand = brandService.get(brand.getId());
        }
        return INPUT;
    }

    @RequiredPermission("商品类别删除")
    public String delete() throws Exception {
    	if (brand.getId() != null) {
    		brandService.delete(brand.getId());
    		putResponseText("删除成功");
    	}
	
        return NONE;
    }

    @RequiredPermission("商品类别保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (brand.getId() == null) {
                brandService.save(brand);
                addActionMessage("保存成功!");
            } else {
                brandService.update(brand);
                addActionMessage("更改成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (brand.getId() != null) {
            brand = brandService.get(brand.getId());
        }
    }
}
