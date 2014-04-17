<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta charset="utf-8" />
	<script src="js/jquery-1.10.2.min.js"></script>
	</head>

	<body>
		<%@ include file="searchbaidu.jsp"%>
		<%@ include file="head_left.jsp"%>
		<div id="Content">
			<div></div>
			<table class="noticetable">
				<thead><tr><td>桐庐富伟针织厂生产通知单</td></tr>
				<tr><td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th></th>
				</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>
		</div>


	</body>

</html>