package com._520it.ssh.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.ssh.domain.Client;
import com._520it.ssh.query.ClientQueryObject;
import com._520it.ssh.service.IClientService;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ClientAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
    private IClientService clientService;

    @Getter
    private Client client = new Client();

    @Getter
    private ClientQueryObject qo = new ClientQueryObject();

    @RequiredPermission("客户信息列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            putContext("pageResult", clientService.queryAndHigh(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("客户信息编辑")
    public String input() throws Exception {
        if (client.getId() != null) {
            client = clientService.get(client.getId());
        }
        return INPUT;
    }

    @RequiredPermission("客户信息删除")
    public String delete() throws Exception {
    	try {
    		if (client.getId() != null) {
    			clientService.delete(client.getId());
    			putResponseText("删除成功");
    		}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addActionMessage(e.getMessage());
		}
        return NONE;
    }

    @RequiredPermission("客户信息保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (client.getId() == null) {
                clientService.save(client);
                addActionMessage("保存成功!");
            } else {
                clientService.update(client);
                addActionMessage("更改成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (client.getId() != null) {
            client = clientService.get(client.getId());
        }
    }
}
