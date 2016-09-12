<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <link href="/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.validate.min.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/plugins/fancybox/jquery.fancybox.pack.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$(".fancybox").fancybox();
    	})
    	//当点击按钮时进行发送数据
    	$(function(){
    		$(".btn_serch").click(function(){
    			window.returnValue=$(this).data("product");
    			
    			window.close()
    		})
    	})
    </script>
    <title>WMS-选购商品列表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<!--====================================-->
<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
<!--====================================-->
<s:form id="searchForm" namespace="/" action="product_selectProductList" method="post" theme="simple">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
							商品名称
							<s:textfield name="qo.keyword" class="ui_input_txt02"/>
							商品分类
							<s:select list="#brands" name="qo.brandid" 
							headerKey="-1" headerValue="商品类别"
							listKey="id" listValue="name" cssClass="ui_select01">
							</s:select>
					</div>
					<div id="box_bottom">
                    	<input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/> 
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                   <tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>货品图片</th>
							<th>货品名称</th>
							<th>货品编码</th>
							<th>货品品牌</th>
							<th>成本价</th>
							<th>销售价</th>
							<th></th>
						</tr>
                    <tbody>
                    <s:iterator value="#pageResult.pageResult">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" autocomplete="off" class="acb" data-eid="<s:property value="id"/>"/></td>
                                <td>
                                	<a class="fancybox" href="<s:property value="imagePath"/>"  title="<s:property value="name"/>">
								   	 <img src="<s:property value="smallImagePath"/>" class="list_img"/>
								    </a>
                                </td>
                                <td><s:property value="name"/></td>
                                <td><s:property value="sn"/></td>
                                <td><s:property value="brand.name"/></td>
                                <td><s:property value="costPrice"/></td>
                                <td><s:property value="salePrice"/></td>
                            <td>
                            	
                               <input type="button" value="选择该商品" class="left2right btn_serch" data-product="<s:property value="productJson"/>"/>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <!--引入分页条-->
            <%@include file="/WEB-INF/views/common/common.jsp"%>
        </div>
    </div>
</s:form>
</body>
</html>
