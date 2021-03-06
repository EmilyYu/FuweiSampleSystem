
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.fuwei.util.FuweiSystemData"%>
<%@page import="com.fuwei.entity.Company"%>
<%@page import="com.fuwei.entity.Developer"%>
<%@page import="com.fuwei.entity.CompanySalesMan"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

	<head>
		<meta charset="utf-8" />
		<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
		<script src="js/common.js" type="text/javascript"></script>
		<script src="js/jquery.form.js" type="text/javascript"></script>
	</head>

	<body>
		<%@ include file="searchbaidu.jsp"%>
		<%@ include file="head_left.jsp"%>
		<div id="Content">
			<div class="breadcrumbs" id="breadcrumbs">
				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a href="index.jsp">首页</a>
					</li>
					<li class="active">
						系统信息管理
					</li>
				</ul>
			</div>
			<div class="tab">
				<ul>
					<li id="workflow_bills" tabid="tab1" class="active">
						<a href="###">公司</a>
					</li>
					<li id="mybills" tabid="tab2">
						<a href="###">业务员</a>
					</li>
					<li id="mybills" tabid="tab3">
						<a href="###">系统用户</a>
					</li>
					<li id="mybills" tabid="tab4">
						<a href="###">工序</a>
					</li>
				</ul>
			</div>

			<div id="tab1" class="tab_widget">
				<div class="block" style="margin-top: 50px;">
					<div class="table_head">
						添加公司
					</div>
					<div class="table_content">
						<form action="addCompanyName.do" method="post">
							<span class="error"></span>
							<div class="editor-label">
								<label>
									中文简称：
								</label>
								<input type="text" name="companyName" id="companyName"
									class="require" />
							</div>
							<div class="editor-label">
								<label>
									英文简称：
								</label>
								<input type="text" name="companyName_jc" id="companyName_jc"
									class="require" />
							</div>
							<div class="editor-label">
								<label>
									公司地址：
								</label>
								<input type="text" name="address" id="address" class="require" />
							</div>

							<div class="editor-label">
								<label>
									公司全称：
								</label>
								<input type="text" name="quanChen" id="quanChen" class="require" />
							</div>
							<div class="editor-label">
								<label>
									所在城市：
								</label>
								<input type="text" name="destination" id="destination"
									class="require" />
							</div>
							<div class="editor-label">
								<input type="submit" value="保存" />
								<input type="reset" value="重置" />
							</div>

						</form>
					</div>
				</div>
				<div class="table_list_widget block" style="margin-top: 50px;">
					<div class="table_head">
						公司列表
						<ul class="head_handles">
							<li>
								<a href="systeminfos.jsp?tabid=tab1"><i
									class="fa fa-refresh"></i>
								</a>
							</li>
						</ul>
					</div>
					<div class="table_content">
						<table class="wijmo">
							<thead>
								<tr>
									<th>
										序号
									</th>
									<th>
										公司名称
									</th>
									<th>
										简称
									</th>
								</tr>
							</thead>
							<tbody>
								<%
									List<Company> companyList=FuweiSystemData.getCompanyList();
									for(int i=0;i<companyList.size();i++){
								%>
								<tr>
									<td><%=i+1%></td>
									<td><%=companyList.get(i).getName()%></td>
									<td><%=companyList.get(i).getJianChen()%></td>
								</tr>
								<%
										}
									%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div id="tab2" class="tab_widget">
				<div class="block">
					<div class="table_head">
						添加业务员
					</div>
					<div class="table_content">
						<form action="addsalesman.do" method="post">
							<span class="error"></span>
							<div class="editor-label">
								<label>
									所属公司：
								</label>
								<select name="companyId" id="companyId">
									<%
								for(Company companyName:companyList){
							%>
									<option value="<%=companyName.getId() %>"><%=companyName.getName() %></option>
									<%} %>
								</select>
							</div>
							<div class="editor-label">
								<label>
									业务员姓名：
								</label>
								<input type="text" name="salesName" id="salesName"
									class="require" />
							</div>
							<div class="editor-label">
								<label>
									联系电话：
								</label>
								<input type="text" name="phone" id="phone" class="require" />
							</div>

							<div class="editor-label">
								<input type="submit" value="保存" />
								<input type="reset" value="重置" />
							</div>

						</form>
					</div>
				</div>
				<div class="table_list_widget block">
					<div class="table_head">
						业务员列表
						<ul class="head_handles">
							<li>
								<a href="systeminfos.jsp?tabid=tab2"><i
									class="fa fa-refresh"></i>
								</a>
							</li>
						</ul>
					</div>
					<div class="table_content">
						<table class="wijmo">
							<thead>
								<tr>
									<th>
										序号
									</th>
									<th>
										所属公司
									</th>
									<th>
										名称
									</th>
								</tr>
							</thead>
							<tbody>
								<%HashMap<String,List<CompanySalesMan>> salesNameByCompanyName=FuweiSystemData.getSalesNameByCompany();
								Set<String> keySet=salesNameByCompanyName.keySet();
								int i=1;
								for(String key:keySet){
									String companyname = FuweiSystemData.getCompanyNameById(Integer.parseInt(key));
									List<CompanySalesMan> salesNameList=salesNameByCompanyName.get(key);
									for(CompanySalesMan salesman:salesNameList){
								%>
								<tr>
									<td><%=i %></td>
									<td><%=companyname%></td>
									<td><%=salesman.getName() %></td>
								</tr>
								<%i++;}
								}%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div id="tab3" class="tab_widget">
				<div class="block">
					<div class="table_head">
						添加系统用户
					</div>
					<div class="table_content">
						<form action="addDeveloper.do" method="post">
							<span class="error"></span>
							<div class="editor-label">
								<label>
									姓名：
								</label>
								<input type="text" name="manName" id="manName" class="require" />
							</div>
							<div class="editor-label">
								<label>
									权限：
								</label>
								<select name="authority" id="authority">
									<option value="1">
										高级用户
									</option>
									<option value="2">
										中级用户
									</option>
									<option value="3">
										普通用户
									</option>
								</select>
							</div>
							<div class="editor-label">
								<input type="submit" value="保存" />
								<input type="reset" value="重置" />
							</div>

						</form>
					</div>
				</div>
				<div class="table_list_widget block">
					<div class="table_head">
						系统用户列表
						<ul class="head_handles">
							<li>
								<a href="systeminfos.jsp?tabid=tab3"><i
									class="fa fa-refresh"></i>
								</a>
							</li>
						</ul>
					</div>
					<div class="table_content">
						<table class="wijmo">
							<thead>
								<tr>
									<th>
										序号
									</th>
									<th>
										名称
									</th>
									<th>
										用户名
									</th>
									<th>
										权限
									</th>
								</tr>
							</thead>
							<tbody>
								<%
								List<Developer> developerList=FuweiSystemData.getDeveloperList();
								for(int i1=0; i1<developerList.size(); i1++){%>
								<tr>
									<td><%=i1+1 %></td>
									<td><%=developerList.get(i1).getName() %></td>
									<td><%=developerList.get(i1).getJianChen() %></td>
									<td><%=developerList.get(i1).getJianChen() %></td>
								</tr>
								<%}%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div id="tab4" class="tab_widget">
				<div class="block">
					<div class="table_head">
						添加工序
					</div>
					<div class="table_content">
						<form action="addGongXu.do" method="post">
							<span class="error"></span>
							<div class="editor-label">
								<label>
									工序名称：
								</label>
								<input type="text" name="gongxuName" id="gongxuName"
									class="require" />
							</div>
							<div class="editor-label">
								<input type="submit" value="保存" />
								<input type="reset" value="重置" />
							</div>

						</form>
					</div>
				</div>

				<div class="table_list_widget block">
					<div class="table_head">
						工序列表
						<ul class="head_handles">
							<li></li>
						</ul>
					</div>
					<div class="table_content">
						<table class="wijmo">
							<thead>
								<tr>
									<th>
										序号
									</th>
									<th>
										名称
									</th>
								</tr>
							</thead>
							<tbody>
								<%List<String> gongXuList=FuweiSystemData.getGongXuList();
									for(int i2=0;i2<gongXuList.size();i2++){%>
								<tr>
									<td><%=i2+1 %></td>
									<td><%=gongXuList.get(i2) %></td>
								</tr>
								<%}%>
							</tbody>
						</table>
					</div>
				</div>

			</div>
	</body>

</html>
<script type="text/javascript">
	$(".tab>ul>li").click( function() {
		$(this).siblings("li").removeClass('active');
		$(this).addClass('active');
		var tabid = $(this).attr("tabid");
		$(".tab_widget").hide();
		$("#" + tabid).show();
	});

	$(".tab>ul>li>a").click( function() {
		$(this).parent().siblings("li").removeClass('active');
		$(this).parent().addClass('active');
		var tabid = $(this).parent().attr("tabid");
		$(".tab_widget").hide();
		$("#" + tabid).show();
		return false;
	});
	$(".table_content form").submit( function() {
		if (!checkform(this)) {
			return false;
		}
		$(this).ajaxSubmit( {
			type :'post',
			url :$(this).attr("action"),
			dataType :'json',
			success : function(result) {
				if (result.OK) {
					alert("添加成功");
					var tabid = $(".tab>ul>li.active").attr("tabid");
					location = location.pathname + "?tabid=" + tabid;
				} else {
					alert("添加失败,错误信息:" + result.message);
				}
			}
		});
		return false;
	});

	/*根据url显示当前tab*/
	var tabid = Common.urlParams().tabid;
	if (typeof (tabid) == "undefined") {
		$(".tab>ul>li").first().click();
	} else {
		$(".tab>ul>li[tabid='" + tabid + "']").click();
	}
	/*根据url显示当前tab*/
</script>