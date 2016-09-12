<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<link href="/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript"
	src="/js/plugins/jquery.validate.min.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript"
	src="/js/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript">
	$(function() {
		$(".btn_select").click(function(){
			$("#searchForm").submit();
		})
	})
</script>
<title>WMS-即时库存管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<s:debug />
	<s:form id="searchForm" namespace="/" action="chart_orderChart" method="post"
		theme="simple">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间
							<s:textfield name="oqo.beginTime" class="ui_input_txt02"></s:textfield>--
							<s:textfield name="oqo.endTime" class="ui_input_txt02"></s:textfield>
							货品
							<s:textfield name="oqo.keyWord" class="ui_input_txt02"/>
							品牌
							<s:select list="#brands" listKey="id" listValue="name"
							headerKey="-1" headerValue="请选择品牌" name="oqo.brand" cssClass="ui_select02"
							></s:select>
							供应商
							<s:select list="#suppliers" listKey="id" listValue="name"
							headerKey="-1" headerValue="请选择供应商" name="oqo.supplierid" cssClass="ui_select02"
							></s:select>
							分组
							<s:select list="#orderGroupByType"  name="oqo.groupType" listKey="name()" listValue="groupType"  class="ui_select02" />
							
							<input type="button" value="查询" class="ui_input_btn01 btn_page"/>
						</div>
					
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%"
						align="center" border="0">
						<tr>
							<th>分组类型</th>
							<th>订货数量</th>
							<th>订货金额</th>
						</tr>
						<tbody>
							<s:iterator value="#orderCharts">
								<tr>
									<td><s:property value="groupType" /></td>
									<td><s:property value="totalNumber" /></td>
									<td><s:property value="totlalAmout" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html>
