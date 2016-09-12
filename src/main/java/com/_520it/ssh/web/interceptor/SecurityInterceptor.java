package com._520it.ssh.web.interceptor;

import java.lang.reflect.Method;
import java.util.Set;

import com._520it.ssh.domain.Employee;
import com._520it.ssh.utils.PermissionUtil;
import com._520it.ssh.utils.RequiredPermission;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author Administrator
 *
 */
public class SecurityInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation actionActionInvocation) throws Exception {
		//获取session中的user对象
		Employee current = (Employee) actionActionInvocation.getInvocationContext().getSession().get("use_in_session");
		//判断用户是否为管理员 
		if(current!=null&&current.isAdmin()){
			return actionActionInvocation.invoke();
		}
		//获取  用户所请求的方法名是否是需要权限
		String methodName = actionActionInvocation.getProxy().getMethod();
		Method actionMethod = actionActionInvocation.getProxy().getAction().getClass().getDeclaredMethod(methodName);
		RequiredPermission rp = actionMethod.getAnnotation(RequiredPermission.class);
		if(rp==null){
			return actionActionInvocation.invoke();
		}
		//进行判断 如果不符合 不能进行执行
		String exp = PermissionUtil.buildExpression(actionMethod);
		@SuppressWarnings("unchecked")
		Set<String> permissions = (Set<String>) actionActionInvocation.getInvocationContext().getSession().get("permission_in_session");
		if(permissions.contains(exp)){
			return actionActionInvocation.invoke();
		}
		return "nopermission";
	}

}
