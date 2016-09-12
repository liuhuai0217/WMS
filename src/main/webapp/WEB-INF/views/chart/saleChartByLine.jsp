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
	
$(function () {
    $('#container').highcharts({
        title: {
            text: '销售业绩线形图',
            x: -20 //center
        },
        subtitle: {
            text: "按照<s:property value='#groupBy'/>分组",
            x: -20
        },
        xAxis: {
            categories:<s:property value="#groupType" escapeHtml="false"/>
        },
        yAxis: {
            title: {
                text: '销售总金额(元)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '元'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '销售总金额',
            data: <s:property value="#totalAmount" escapeHtml="false"/>
        }]
    });
});
</script>
<title>WMS-销售柱状图</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>

<script src="/js/plugins/highcharts/highcharts.js"></script>
<script src="/js/plugins/highcharts/modules/exporting.js"></script>

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>
