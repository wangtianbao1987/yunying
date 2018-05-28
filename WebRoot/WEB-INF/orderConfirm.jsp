<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<title>信息确认</title>
<link type="text/css" rel="stylesheet" href="css/com.css"/>
<link type="text/css" rel="stylesheet" href="css/global.css"/>
<link type="text/css" rel="stylesheet" href="css/orderConfirm.css"/>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/windowOpen.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<%
String winId = request.getParameter("winId");
List<Map<String,Object>> items = (List<Map<String,Object>>)session.getAttribute(winId);
DecimalFormat df = new DecimalFormat("0.##");
%>
<script type="text/javascript">
var winId = '<%=winId%>';
</script>
<script type="text/javascript" src="js/orderConfirm.js"></script>


</head>
<body bgcolor="#FFFFCC">

<div class="tit01">信息确认</div>

<table class="tb">
<tr>
<th>编号</th><th>名称</th><th>单价</th><th>数量</th><th>总价</th>
</tr>
<%
double hj = 0D;
for(int i=0;i<items.size();i++){
Map<String,Object> item = items.get(i);
double itemHj = Double.parseDouble(item.get("price").toString())*Double.parseDouble(item.get("count").toString());
hj += itemHj;
%>
<tr>
<th><%=item.get("idx") %></th>
<th class="nameTH"><%=item.get("name") %></th>
<td><%=df.format(Double.parseDouble(item.get("price").toString())) %></td>
<td><%=df.format(Double.parseDouble(item.get("count").toString())) %></td>
<td><%=df.format(itemHj) %></td>
</tr>
<%} %>
<tr>
<th colspan="2">合计：</th>
<td colspan="3"><%=df.format(hj) %>元</td>
</tr>
</table>
<div class="msgBuquan">
	<div><label>如何称呼：</label><input type="text" id="userName"/></div>
	<div><label>联系电话：</label><input type="tel" id="phone"/></div>
	<div><label>配送地址：</label>
	<textarea style="width: 100%;height: 100px;" id="address"></textarea>
	</div>
	<div><label>忌口说明：</label>
	<textarea style="width: 100%;height: 100px;" id="notLike"></textarea>
	</div>
</div>

<div class="btmDiv">
<button class="button green" onclick="history.back()">返回修改</button>
<button class="button green subBtn">提交</button>
</div>


</body>
</html>