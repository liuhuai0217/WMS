package com._520it.ssh.web.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author Administrator
 *
 */
public class CheckLongInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation actionActionInvocation) throws Exception {
		// TODO Auto-generated method stub
		Object user = actionActionInvocation.getInvocationContext().getSession().get("use_in_session");
		if(user==null){
			return Action.LOGIN;
		}
		return actionActionInvocation.invoke();
	}

}
