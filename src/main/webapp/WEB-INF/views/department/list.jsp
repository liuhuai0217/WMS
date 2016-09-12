<%@page import="com.sun.tools.internal.ws.wsdl.document.Import"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript" src="/js/artDialog/jquery.artDialog.js?skin=blue">
</script>
<title>PSS-账户管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
	<s:form id="searchForm" action="department" method="post" namespace="/" theme="simple">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="<s:url namespace="/" action="department_input"/>"/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>部門名稱</th>
							<th>部門代碼</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.pageResult">
								<tr>
								<td><input type="checkbox" name="IDCheck" class="acb" autocomplete="off" data-eid="<s:property value="id"/>"/></td>
									<td><s:property value="id"/></td>
									<td><s:property value="name"/></td>
									<td><s:property value="sn"/></td>
									<td>
										<s:url var="editUrl" namespace="/" action="department_input">
											<s:param name="department.id" value="id"/>
										</s:url>
										<a href='<s:property value="#editUrl"/>'>编辑</a>
										<s:url var="deleteUrl" namespace="/" action="department_delete">
											<s:param name="department.id" value="id"/>
										</s:url>
										<a href="javascript:;" class="btn_delete" data-url='<s:property value="#deleteUrl"/>'>删除</a>
									<!-- 
										<s:a namespace="/" action="department_input"><s:param name="department.id" value="id"/>编辑</s:a>
										<s:a namespace="/" action="department_delete"><s:param name="department.id" value="id"/>删除</s:a>
									 -->
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<!-- 这是一种导入页面的方式 -->
				<!-- 
				<s:include value="/WEB-INF/views/common/common.jsp"></s:include>
				 -->
				 <%@include file="/WEB-INF/views/common/common.jsp" %>
			</div>
		</div>
	</s:form>
</body>
</html>