<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.fuwei.entity.Sample"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Sample sample = new Sample();
	request.setAttribute("sampleid","sampleid");
	int sampleID=1;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'test.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta charset="utf-8" />
		<title>ERP-BASE</title>
		<link href="css/basedata.css" rel="stylesheet" type="text/css" />
		<link href="css/button.css" rel="stylesheet" type="text/css" />

		<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
		<script src="My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function mysubmit()
{
	document.getElementById("addcompanyform").submit();
}
</script>

	</head>

	<body>
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<ul class="breadcrumb">
					<li>
						<i></i>
						<a href="#">员工资料</a>
					</li>
					<li class="active">
						查看
					</li>
				</ul>
				<!-- .breadcrumb -->
			</div>
			<div>
				<br />
				<button class="btn-left btn-left_create" id="dx1">
					<a><img src="img/新建.png" />
					</a>
					<!--新增--#7DBEFF-->
				</button>
				<button class="btn-left btn-left_edit" id="dx2">
					<a><img src="img/编辑3.png" /> </a>
					<!--修改-->
				</button>
				<button class=" btn-left btn-left_delete"
					onclick="return confirm('你确定要删除吗?')">
					<a><img src="img/删除.png" /> </a>
					<!--删除-->
				</button>
				<div class="d1">
					<div class="d2">
						<table class="table table-bordered table-striped">
							<thead class="thin-border-bottom">
								<tr>
									<th width="15%">
										序号
									</th>
									<th width="17%">
										姓名
									</th>
									<th width="20%">
										工号
									</th>
									<th width="13%">
										性别
									</th>
									<th width="13%">
										在职状态
									</th>
									<th>
										手机号码
									</th>
								</tr>
							</thead>
						</table>
					</div>
					<div class="d3">
						<table id="tb" class="table table-bordered table-striped">
							<tbody id="tby" align="right" class="d4">
								<tr>
									<td width="15%">
										<a href="#">111</a>
									</td>
									<td width="17%">
										张三
									</td>
									<td width="20%">
										23113
									</td>
									<td width="13%">
										男
									</td>
									<td width="13%">
										<input id="Checkbox1" type="checkbox" checked="checked" />
									</td>
									<td>
										13532454356
									</td>
								</tr>
								<tr>
									<td>
										<a href="#">112</a>
									</td>
									<td>
										王柳
									</td>
									<td>
										23165
									</td>
									<td>
										女
									</td>
									<td>
										<input id="Checkbox2" type="checkbox" checked="checked" />
									</td>
									<td>
										18946573325
									</td>
								</tr>
								<tr>
									<td>
										<a href="#">113</a>
									</td>
									<td>
										王柳
									</td>
									<td>
										23165
									</td>
									<td>
										女
									</td>
									<td>
										<input id="Checkbox3" type="checkbox" checked="checked" />
									</td>
									<td>
										18946573325
									</td>
								</tr>
								<tr>
									<td>
										<a href="#">114</a>
									</td>
									<td>
										王柳
									</td>
									<td>
										23165
									</td>
									<td>
										女
									</td>
									<td>
										<input id="Checkbox4" type="checkbox" checked="checked" />
									</td>
									<td>
										18946573325
									</td>
								</tr>
								<tr>
									<td>
										<a href="#">115</a>
									</td>
									<td>
										王柳
									</td>
									<td>
										23165
									</td>
									<td>
										女
									</td>
									<td>
										<input id="Checkbox5" type="checkbox" checked="checked" />
									</td>
									<td>
										18946573325
									</td>
								</tr>
								<tr>
									<td>
										<a href="#">116</a>
									</td>
									<td>
										王柳
									</td>
									<td>
										23165
									</td>
									<td>
										女
									</td>
									<td>
										<input id="Checkbox6" type="checkbox" checked="checked" />
									</td>
									<td>
										18946573325
									</td>
								</tr>
							</tbody>

						</table>
					</div>
				</div>
				<!--弹出层-->
				
				<div id="box_div" class="m_box" style="filter:alpha(opacity=90); background:#0be2da; ">
				<form id="addcompanyform"  action="test" method="post">
					<div class="header" onmousedown="MDown(massage_box)">
						<div class="work_head_cell">
							会员资料管理
						</div>
						<span id="dy1" class="layer_clikc"> <img
								src="login/close1.png" class="button_close"
								onmouseover="this.src='login/close.png'"
								onMouseOut="this.src='login/close1.png'" />
						</span>
					</div>
					<div style="margin-left: 350px;">

						<p>
							<font size="4">公司名称:<font size="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input id="nan"  type="text" class="text_background" name="productNumber" style="background-color: white;"/>
					
							&nbsp;&nbsp;<font size="4">公司货号:<font size="4">&nbsp;&nbsp;&nbsp;
							<input id="Checkbox7" type="text" class="text_background" name="salesman" style="background-color: white;"/>
						</p>
						<p>
							<font size="4">样品价格:<font size="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input id="nan" type="text" class="text_background" name="productNumber" style="background-color: white;"/>
					
							&nbsp;&nbsp;<font size="4">业务人员:<font size="4">&nbsp;&nbsp;&nbsp;
							<input id="Checkbox7" type="text" class="text_background" name="salesman" style="background-color: white;"/>
						</p>
						<p >
							<font size="4">报价备注:</font>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea rows="5" cols="40" class="text_background2" style="background-color: white;"></textarea>
						</p>
						<p>
							<br>
							<input type="image" src="img/ok.png" onclick="mysubmit()" style="margin-left: 515">
						</p>
					</div>
				</form>
				</div>
				
			</div>
		</div>

		<script type="text/javascript">
	$(document)
			.ready( function() {
				$("tbody>tr:odd").addClass("odd"); //先排除第一行,然后给奇数行添加样式
					$('tbody>tr').click(
							function() {
								$(this).addClass('selected').siblings()
										.removeClass('selected').end();
							});
					$("#dx1").click( function() {
						$("#box_div").show("slow");

					});
					$("#dx2").click( function() {
						$("#box_div").show("slow");

					});
					$("#dy1").click( function() {
						$("#box_div").hide("slow");

					});

					var tab = document.getElementById("tb");
					var a = tab.rows.length;

					$("#Save")
							.click(
									function() {
										var str = "";
										a++;
										str += "<tr><td><a href='#'>000"
												+ a
												+ "</a></td><td>"
												+ $("#Name").val()
												+ "</td><td>"
												+ $("#Sum").val()
												+ "</td><td>男</td><td><input id='Checkbox7' type='checkbox' checked='checked' /></td><td>"
												+ $("#Phone").val() + "</td>";
										$("#tby").append(str);
										return false;

									});

				})
</script>

	</body>
</html>
