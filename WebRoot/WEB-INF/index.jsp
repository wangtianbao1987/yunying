<%@page import="java.util.UUID"%>
<%@page import="com.wtb.fuwu.data.InitData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<title>在线点餐</title>
<link type="text/css" rel="stylesheet" href="css/global.css"/>
<link type="text/css" rel="stylesheet" href="css/index.css"/>

<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/windowOpen.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
var winId = '<%=UUID.randomUUID()%>';
var caidanDatas = <%=InitData.toJson()%>
</script>
<script type="text/javascript" src="js/index.js"></script>
</head>
<body bgcolor="#FFFFCC">

<div style="padding:10px">
<div style="color:red">此功能处于开发阶段，在此期间点餐请拨打订餐电话：</div>
<br/>
<div style="color:red">
<a href="tel:17601339729">17601339729</a> 或  <a href="tel:17721000136">17721000136</a></div>
</div>

<div class="tit">云英沙县小吃牛肉汤馆</div>
<table class="address">
<tr>
<th>地址：</th>
<td>上海市嘉定区迎园路和政路路口迎园路306号</td>
</tr>
</table>
<div>
<div class="mainContent"></div>
<div class="bottomZhanwei"></div>
<div class="dingcan">
<div>&nbsp;</div>
<div>&nbsp;</div>
<div style="position: fixed;bottom: 0px; width: 100%;">
<div style="text-align:right">共计<span class="totMoney">0</span>元</div>
<div class="ddokbtn" style="text-align:center;background-color: #CCFFFF">确定</div>
</div>
</div>
</div>

</body>
</html>