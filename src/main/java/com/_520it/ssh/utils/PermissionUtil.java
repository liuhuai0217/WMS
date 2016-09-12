package com._520it.ssh.utils;

import java.lang.reflect.Method;

public class PermissionUtil {
	public static String buildExpression(Method method) {
	    //获取当前Method所在类的全限定名称
	    String className=method.getDeclaringClass().getName();
	    String methodName=method.getName();

	    return className+":"+methodName;
	}
}
