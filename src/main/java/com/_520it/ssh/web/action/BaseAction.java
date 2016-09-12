package com._520it.ssh.web.action;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements Preparable{

	public static final long serialVersionUID = 1L;
	public void prepare() throws Exception {
	}
	protected void putContext(String name,Object value){
		ActionContext.getContext().put(name, value);
	}
	//把一些响应的信息进行设置
	protected void putResponseText(String data) throws Exception{
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
	}
	protected void putJson(Object value) throws Exception{
		String json = JSON.toJSONString(value);
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
    	ServletActionContext.getResponse().getWriter().print(json);
	}
}
