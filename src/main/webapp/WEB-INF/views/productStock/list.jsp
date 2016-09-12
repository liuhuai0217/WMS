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
	<s:form id="searchForm" namespace="/" action="productStock" method="post"
		theme="simple">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							货品
							<s:textfield name="qo.keyword" class="ui_input_txt02"/>
							仓库
							<s:select list="#depots" listKey="id" listValue="name"
							headerKey="-1" headerValue="请选择仓库" name="qo.depot" cssClass="ui_select01"
							></s:select>
							品牌
							<s:select list="#brands" listKey="id" listValue="name"
							headerKey="-1" headerValue="请选择品牌" name="qo.brand" cssClass="ui_select01"
							></s:select>
							阈值
							<s:textfield name="qo.storeLimit" class="ui_input_txt02"/>
							
							
							<input type="button" value="查询" class="ui_input_btn01 btn_select"
								data-page="1" />
						</div>
					
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%"
						align="center" border="0">
						<tr>
							<th>货品</th>
							<th>仓库</th>
							<th>品牌</th>
							<th>库存价格</th>
							<th>库存数量</th>
							<th>库存总金额</th>

						</tr>
						<tbody>
							<s:iterator value="#pageResult.pageResult">
								<tr>
									<td><s:property value="product.sn" /></td>
									<td><s:property value="depot.name" /></td>
									<td><s:property value="product.brand.name" /></td>
									<td><s:property value="price" /></td>
									<td><s:property value="stroeNumber" /></td>
									<td><s:property value="amount" /></td>
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
