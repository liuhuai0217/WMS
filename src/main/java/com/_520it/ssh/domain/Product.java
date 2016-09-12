package com._520it.ssh.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com._520it.ssh.utils.FileUploadUtil;
import com.alibaba.fastjson.JSON;

//商品表
@Getter@Setter@ToString
public class Product extends BaseDomain{
	private String name;//商品名称
	private String sn;//商品编号
	private BigDecimal costPrice;//商品成本价
	private BigDecimal salePrice;//商品销售价
	private String imagePath;//图片网址
	private String intro;//商品评价
	private Brand brand;//商品分类
	//xxx.jpg 转换成xxx_samll.jpg
	public  String getSmallImagePath(){
		if(imagePath==null){
			return "";
		}
		int index = imagePath.lastIndexOf(".");
		return this.imagePath.substring(0, index)+FileUploadUtil.suffix+this.imagePath.substring(index);
	}
	public String getProductJson(){
		Map<String,Object> json = new HashMap<>();
		json.put("id", id);
		json.put("name", name);
		json.put("costPrice", costPrice);
		json.put("salePrice", salePrice);
		json.put("brandName", this.brand!=null?this.brand.getName():"");
		return JSON.toJSONString(json);
		
	}
}
