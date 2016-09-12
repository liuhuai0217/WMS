<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
	<s:if test="hasActionMessages()">
	<%--
		var msg='<s:property value="actionMessages[0]"/>';
		alert(msg);
	--%>
	$.dialog({
		title:'操作提示',
		icon:'face-smile',
		content:'<s:property value="actionMessages[0]"/>',
		ok:true
	})
	</s:if>
	<s:if test="hasActionErrors()">
	<%-- 
		var msg='<s:property value="errorMessages[0]"/>';
		alert(msg);
	--%>
	$.dialog({
		title:'操作提示',
		icon:'face-sad',
		content:'<s:property value="errorMessages[0]"/>',
		ok:true
	})
	</s:if>
</script>
