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
		$(".btn_select1").click(function(){
			$("#searchForm").submit();
		})
	})
	$(function(){
		$(".type").change(function(){
			var type = $(this).val();
			if(type=='line'){
				$("#searchForm").prop("action",'chart_saleChartByLine.action');
				$("#searchForm").submit();
			}else if(type=='pie'){
				$("#searchForm").prop("action",'chart_saleChartByPie.action');
				$("#searchForm").submit();
			}
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
	<s:form id="searchForm" namespace="/" action="chart_saleChart" method="get"
		theme="simple">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间
							<s:textfield name="qo.beginTime" class="ui_input_txt02"></s:textfield>--
							<s:textfield name="qo.endTime" class="ui_input_txt02"></s:textfield>
							货品
							<s:textfield name="qo.keyWord" class="ui_input_txt02"/>
							客户
							<s:select list="#clients" listKey="id" listValue="name"
							headerKey="-1" headerValue="请选择客户" name="qo.client" cssClass="ui_select02"
							></s:select>
							品牌
							<s:select list="#brands" listKey="id" listValue="name"
							headerKey="-1" headerValue="请选择品牌" name="qo.brand" cssClass="ui_select02"
							></s:select>
							分组
							<s:select list="#saleGroupByType"  name="qo.groupType" listKey="name()" listValue="groupType" class="ui_select02" />
							<input type="button" value="查询" class="ui_input_btn01 btn_select1"/>
							
						
							查看图表<s:select list="#{'':'选择图表','line':'线形图','pie':'饼图'}" name="xx" class="ui_select02 type"></s:select>
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
							<th>销售数量</th>
							<th>销售金额</th>
							<th>净利润</th>
						</tr>
						<tbody>
							<s:iterator value="#saleCharts">
								<tr>
									<td><s:property value="groupType" /></td>
									<td><s:property value="totalNumber" /></td>
									<td><s:property value="totlalAmout" /></td>
									<td><s:property value="totalLirun" /></td>
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
