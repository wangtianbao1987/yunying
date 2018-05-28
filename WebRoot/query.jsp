<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<title>订餐查询</title>

<link type="text/css" rel="stylesheet" href="css/com.css"/>
<link type="text/css" rel="stylesheet" href="css/global.css"/>
<link type="text/css" rel="stylesheet" href="css/query.css"/>

<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/windowOpen.js"></script>
<script type="text/javascript" src="js/query.js"></script>

</head>
<body>

<table class="queryTb">
<tr>
<th>手机号码</th>
<td><input type="tel" id="phone"/></td>
</tr>
<tr>
<th>订餐日期</th>
<td><input type="date" id="startDate"/></td>
</tr>
<tr>
<td style="padding:10px" colspan="2" align="right"><label class="button queryBtn">查询</label></td>
</tr>
</table>

<div class="queryListDiv"></div>


</body>
</html>