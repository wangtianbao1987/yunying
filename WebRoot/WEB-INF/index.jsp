<%@page import="java.util.UUID"%>
<%@page import="com.wtb.fuwu.data.InitData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<title>在线点餐</title>
<link type="text/css" rel="stylesheet" href="css/com.css"/>
<link type="text/css" rel="stylesheet" href="css/global.css"/>
<link type="text/css" rel="stylesheet" href="css/index.css"/>

<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/windowOpen.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
var winId = '<%=UUID.randomUUID()%>';
var caidanDatas = <%=InitData.toJson()%>
</script>
<!-- <script type="text/javascript" src="js/index.js"></script> -->

<style type="text/css">
body{
	background-color: #FFF;
}
.daohang{
	position: relative;
	width: 100%;
}
.daohang>.dhItem{
	float: left;
	width: 25%;
	text-align: center;
	box-sizing: border-box;
	padding:15px 10px;
	background:#CCC;
	text-shadow: 0 1px #FFF, 1px 0 #FFF, -1px 0 #FFF, 0 -1px #FFF;
	cursor: pointer;
}
.daohang>.active{
	border-bottom: 0px;
	background: -webkit-linear-gradient(#CCC, #FFF);
	background: -o-linear-gradient(#CCC, #FFF);
	background: -moz-linear-gradient(#CCC, #FFF);
	background: linear-gradient(#CCC, #FFF);
}
</style>

<script type="text/javascript">
$(document).ready(function(){
	var datas = <%=InitData.toJson()%>
	var showItem = function(i){
		$('.mainContent').html('');
		var caidanData = datas[i];
		var table = $('<table>');
		table.addClass('tb');
		for(var ii=0;ii<caidanData.values.length;ii++){
			var tr = $('<tr>');
			tr.attr('id','K_'+i+'_'+ii);
			var val1 = caidanData.values[ii];
			var showName = val1.idx+'、'+val1.name;
			var th = $('<th>');
			th.html(showName);
			tr.append(th);
			for(var iii=0;iii<val1.values.length;iii++){
				var val2 = val1.values[iii];
				var td = $('<td>');
				td.attr('index1',i);
				td.attr('index2',ii);
				td.attr('index3',iii);
				if(val2.price){
					td.html(val2.price+'元'+val2.remark);
				}else if(val2.minPrice && val2.maxPrice){
					td.html(val2.minPrice+'-'+val2.maxPrice+'元'+val2.remark);
				}else if(val2.perPrice){
					td.html(val2.perPrice+'元/'+val2.unit+val2.remark);
				}
				tr.append(td);
			}
			table.append(tr);
		}
		$('.mainContent').append(table);
		$('table tr').attr('valign','middle');
	}
	$('.daohang>.dhItem').unbind('click');
	$('.daohang>.dhItem').bind('click',function(){
		$('.daohang>.active').removeClass('active');
		$(this).addClass('active');
		showItem($(this).attr('index'));
	});
	showItem(0);
});
</script>

</head>
<body bgcolor="#FFF">

<div style="text-align: right;padding:10px"><a class="button" href="query.jsp">订餐查询</a></div>

<div class="tit">云英沙县小吃牛肉汤馆</div>
<table class="address">
<tr>
<th>地址：</th>
<td>上海市嘉定区迎园路和政路路口迎园路306号</td>
</tr>
</table>
<div>

<div>
<div class="daohang">
<div class="dhItem active" index="0">汤面</div>
<div class="dhItem" index="1">老板推荐</div>
<div class="dhItem" index="2">精美小吃</div>
<div class="dhItem" index="3">另加</div>
</div>

<div class="mainContent"></div>

</div>



</div>

</body>
</html>