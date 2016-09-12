<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.artDialog.js?skin=blue"></script>
    <script language="javascript" type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
	 <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {	
        	
        	$("#edit_table_body").on("click",".searchproduct",function(){
                var json = window.showModalDialog("product_selectProductList.action", "", "dialogWidth=800px;dialogHeight=500px");
                console.debug(json);
                var tr = $(this).closest("tr");
                tr.find("[tag=name]").val(json.name);
                tr.find("[tag=pid]").val(json.id);
                tr.find("[tag=costPrice]").val(json.costPrice);
                tr.find("[tag=brand]").text(json.brandName);
        		
        	}).on("change","[tag=number],[tag=costPrice]",function(){
                var tr = $(this).closest("tr");
                var costPrice = parseFloat(tr.find("[tag=costPrice]").val());
                var number = parseFloat(tr.find("[tag=number]").val());
                if (costPrice && number) {
                    tr.find("[tag=amount]").text((costPrice*number).toFixed(2));
                }
        	}).on("click",".removeItem",function(){
        		var tr=$(this).closest("tr");
        		if($("#edit_table_body tr").size() == 1){
        			tr.find("[tag=name]").val("");
                    tr.find("[tag=pid]").val("");
                    tr.find("[tag=costPrice]").val("");
                    tr.find("[tag=number]").val("");
                    tr.find("[tag=remark]").val("");
                    tr.find("[tag=brand]").text("");
                    tr.find("[tag=amount]").text("");
        		}else{
        			tr.remove();
        		}
        	});
        	$(".appendRow").click(function(){
        		//拿取第一个 然后进行克隆添加
        		//添加
        		var tr=$("#edit_table_body tr:first").clone();
        		tr.find("[tag=name]").val("");
                tr.find("[tag=pid]").val("");
                tr.find("[tag=costPrice]").val("");
                tr.find("[tag=number]").val("");
                tr.find("[tag=remark]").val("");
                tr.find("[tag=brand]").text("");
                tr.find("[tag=amount]").text("");
                tr.appendTo($("#edit_table_body"));
        	});
        	//提交表单
        	$(".btn_submit").click(function(){
        		$.each($("#edit_table_body tr"),function(index,item){
        			var tr = $(item);
        			tr.find("[tag=pid]").prop("name","stockIncomeBill.items["+index+"].product.id");
                    tr.find("[tag=costPrice]").prop("name","stockIncomeBill.items["+index+"].costPrice");
                    tr.find("[tag=number]").prop("name","stockIncomeBill.items["+index+"].number");
                    tr.find("[tag=remark]").prop("name","stockIncomeBill.items["+index+"].remark");
        		});
        		$("#editForm").submit();
        	});
        });
      
    </script>
    <script type="text/javascript">
    	$(function(){
    		  $("input[name='stockIncomeBill.vdate']").addClass('Wdate').click(function(){
    	         	WdatePicker({
    	 				skin:'whyGreen'
    	 			});
    	         })
    	})
    </script>
</head>
<body>
<s:debug/>
<!--====================================-->
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<!--====================================-->
<s:form name="editForm" action="stockIncomeBill_saveOrUpdate" method="post" id="editForm" theme="simple">
    <s:hidden name="stockIncomeBill.id"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">入库单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">入库编号</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockIncomeBill.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">仓库</td>
                   <td>
           			<s:select list="#depots" name="stockIncomeBill.depot.id" listKey="id" listValue="name"
           			headerKey="-1" headerValue="全部仓库" cssClass="ui_select01">
           			</s:select>
                   </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockIncomeBill.vdate" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <!-- 需要进行判断 根据id进行判断是添加信息还是编辑 -->
                            <s:if test="stockIncomeBill.id==null">
	                            <tr>
	                                <td></td>
	                                <td>
	                                    <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02" tag="name"/>
	                                    <img src="/images/common/search.png" class="searchproduct"/>
	                                    <s:hidden name="stockIncomeBill.items.product.id" tag="pid"/>
	                                </td>
	                                <td><span tag="brand"></span></td>
	                                <td><s:textfield tag="costPrice" name="stockIncomeBill.items.costPrice"
	                                                 cssClass="ui_input_txt04"/></td>
	                                <td><s:textfield tag="number" name="stockIncomeBill.items.number"
	                                                 cssClass="ui_input_txt04"/></td>
	                                <td><span tag="amount"></span></td>
	                                <td><s:textfield tag="remark" name="stockIncomeBill.items.remark"
	                                                 cssClass="ui_input_txt02"/></td>
	                                <td>
	                                    <a href="javascript:;" class="removeItem">删除明细</a>
	                                </td>
	                            </tr>
                            </s:if>
                            <s:else>
                            <s:iterator value="stockIncomeBill.items">
		                              <tr>
		                                <td></td>
		                                <td>
		                                    <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02" 
		                                    tag="name" name="product.name"/>
		                                    <img src="/images/common/search.png" class="searchproduct"/>
		                                    <s:hidden name="product.id" tag="pid"/>
		                                </td>
		                                <td><span tag="brand"><s:property value="product.brand.name"/></span></td>
		                                <td><s:textfield tag="costPrice" name="costPrice"
		                                                 cssClass="ui_input_txt04"/></td>
		                                <td><s:textfield tag="number" name="number"
		                                                 cssClass="ui_input_txt04"/></td>
		                                <td><span tag="amount"><s:property value="amount"/></span></td>
		                                <td><s:textfield tag="remark" name="remark"
		                                                 cssClass="ui_input_txt02"/></td>
		                                <td>
		                                    <a href="javascript:;" class="removeItem">删除明细</a>
		                                </td>
		                            </tr>
	                            </s:iterator>
                            </s:else>
                            </tbody>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="button" value="确定保存" class="ui_input_btn01 btn_submit"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>