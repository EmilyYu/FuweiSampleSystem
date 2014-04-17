<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@page import="com.fuwei.entity.QuotationList"%>
<%@page import="com.fuwei.util.FuweiSystemData"%>
<%@page import="com.fuwei.util.DateFormateUtil"%>
<%
	int currpage = (Integer)request.getAttribute("page");
	int totalPages = (Integer)request.getAttribute("totalPages");
	int totalRecords = (Integer)request.getAttribute("totalRecords");
	int perPage = (Integer)request.getAttribute("perPage");
	String key = (String)request.getAttribute("key");
	String atime = (String)request.getAttribute("atime");
	String btime = (String)request.getAttribute("btime");
	int type = (Integer)request.getAttribute("type");
	if(type<0||type>4){
		type = 0;
	}
	List<QuotationList> quotationLists = (List<QuotationList>) request.getAttribute("quotationList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta charset="utf-8" />
		<title>富伟针织厂- 管理系统</title>
		<!--web字体-->
		<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
		<script src="js/common.js" type="text/javascript"></script>
		<script src="js/jquery.form.js" type="text/javascript"></script>
	
	</head>

	<body>
		<%@ include file="searchbaidu.jsp"%>
<%@ include file="head_left.jsp"%>
		<div id="Content">
		<div style="display: none;" class="background"></div>
		<div style="display: none;" id="loading">
			数据加载中，请稍等......
		</div>

		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li>
					<i class="icon-home home-icon"></i>
					<a href="index.jsp">首页</a>
				</li>
				<li class="active">
					报价单列表
				</li>
			</ul>
		</div>
		<div class="mainContent">
			<div class="middle">
				<div class="Operatings">
					<div class="operations_left">
					<form name="head_search_form" id="head_search_form"
							action="searchQuotationList.do">
							<div class="editor-label search_triggers">
								<input type="button" id="showall" value="显示全部" />
								<select name="type" class="search_triggers_select">
									<option value="1" selected>
										按报价单号搜索
									</option>
									<option value="2">
										按公司搜索
									</option>
									<option value="4">
										按公司业务员搜索
									</option>
								</select>

								<input type="text" autocomplete="off" name="key"
									id="searchQuery" class="autocomplete tip searchText"
									tip="请输入搜索内容" value="请输入搜索内容" />

								<div class="autocompletediv">
									<ul class="autocomplete_ul">
									</ul>
								</div>
							<input type="hidden" name="page" value="<%=currpage %>" />
							<input type="hidden" name="atime" value="<%=atime %>" />
							<input type="hidden" name="btime" value="<%=btime %>" />
							<input class="search" type="submit" value="" />
							</div>
						</form>

						<a class="page_btn" href="?page=1&key=<%=key%>&type=<%=type%>&atime=<%=atime%>&btime=<%=btime %>">首页</a>
						<%if(currpage==1){ %>
						<a class="page_btn disabled">上一页</a>
						<%
						}else{ %>
						<a class="page_btn"
							href="?page=<%=currpage-1 %>&key=<%=key%>&type=<%=type%>&atime=<%=atime%>&btime=<%=btime %>">上一页</a>
						<%
						} %>
						<span>第<%=currpage %>页/共<%=totalPages %>页</span>
						
						<%if(currpage+1>totalPages){ %>
						<a class="page_btn disabled">下一页</a>
						<%
						}else{ %>
						<a class="page_btn"
							href="?page=<%=currpage+1 %>&key=<%=key%>&type=<%=type%>&atime=<%=atime %>&btime=<%=btime %>">下一页</a>
						<%
						} %>

						<a class="page_btn"
							href="?page=<%=totalPages %>&key=<%=key%>&type=<%=type%>&atime=<%=atime%>&btime=<%=btime %>">最后一页</a>
						<form action="searchQuotationList.do" class="page_go_form">
							<label class="page_go_label">
								去第
							</label>
							<input type="text" value="<%=currpage %>" name="page"
								class="page_go" />
							<label class="page_go_label">
								页
							</label>
							<input type="hidden" name="key" value="<%=key %>" />
							<input type="hidden" name="type" value="<%=type %>" />
							<input type="hidden" name="atime" value="<%=atime %>" />
							<input type="hidden" name="btime" value="<%=btime %>" />
							<input type="submit" class="page_go_btn" value="确定"></input>
						</form>
						<span class="page_totalrecords">共<%=totalRecords %>条记录</span>
						<form class="time_search_form"
							action="searchQuotationList.do">
								<div class="editor-label search_triggers">
							<input type="hidden" name="key" value="<%=key %>" />
							<input type="hidden" name="type" value="<%=type %>" />
							<input type="hidden" name="page" value="<%=currpage %>" />
							<label>
								设置时间段:
							</label>
							<input type="text" name="atime" id="atime" value="<%=atime %>"
								onClick="WdatePicker()" readonly="readonly"/>
							--
							<input type="text" name="btime" id="btime" value="<%=btime %>"
								onClick="WdatePicker()" readonly="readonly"/>
							<input class="search" type="submit" value="" />
</div>
						</form>
					</div>

					<br />
				</div>

				<div class="TableHead-widget" style="width: 100%;">
					<table class="TableHead wijmo">
						<thead>
							<tr>
								<th width="10%">
									序号
								</th>
								<th width="5%" style="display: none;">
									Id
								</th>
								<th width="20%">
									报价单号
								</th>
								<th width="20%">
									公司名称
								</th>
								<th width="20%">
									业务员名称
								</th>
								<th width="15%">
									创建时间
								</th>
								<th width="15%">
								</th>
							</tr>
						</thead>
						<tbody>
							<%
							for(int i = 0 ; i <quotationLists.size();++i){
								QuotationList quotationList = quotationLists.get(i);
							 %>
							<tr>
								<td><%=i+1 %></td>
								<td style="display: none;"><%=quotationList.getId() %></td>
								<td><%=quotationList.getQuotationNumber() %></td>
								<td><%=quotationList.getCompanyName() %></td>
								<td><%=quotationList.getSalesName() %></td>
								<td><%=DateFormateUtil.formateDate(quotationList.getTime()) %></td>
								<td>
									<a href="quotationListDetail.do?id=<%=quotationList.getId() %>">详情</a>
								</td>
							</tr>
							<%
							}
							if(quotationLists.size() == 0){
 							%>
							<tr>
								<td colspan="7">
									抱歉，搜索不到报价单记录
								</td>
							</tr>
							<%
							}
 							%>
						</tbody>
					</table>
				</div>


			</div>
		</div>
</div>
<script type="text/javascript">
	
	//设置搜素内容
	$("select[name='type'] option[value= '<%=type%>' ] ").attr("selected",true);//使得select默认选中后台查询到的val
	var key="<%=key%>";
	if(key!=""){
		$("input[name='key']").val(key);
	}
	//设置搜素内容   
</script>
	</body>
</html>