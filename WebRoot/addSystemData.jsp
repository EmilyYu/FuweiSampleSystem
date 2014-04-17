<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'addSystemData.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<a href="addCompanyInfo.jsp"> 添加公司</a>
		<br>
		<br>
		<a href="addSalesMan.jsp"> 添加业务员</a>
		<br>
		<br>
		<a href="addGongxu.jsp"> 添加加工工序</a>
		<br>
		<br>
		<a href="addDevelop.jsp"> 添加跟单人员</a>
		<br>
		<br>
	</body>
</html>
