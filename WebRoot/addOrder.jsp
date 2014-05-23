<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.fuwei.util.FuweiSystemData"%>
<%@page import="com.fuwei.entity.Company"%>
<%@page import="com.fuwei.entity.CompanySalesMan"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.fuwei.entity.FWOrder"%>
<%@page import="com.fuwei.entity.QuotationList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	
	List<Company> companyNameList = FuweiSystemData.getCompanyList();
	HashMap<String, List<CompanySalesMan>> SalesNameByCompanyNameList = FuweiSystemData.getSalesNameByCompany();
	JSONObject jObject = new JSONObject();
	jObject.put("SalesNameByCompanyName", SalesNameByCompanyNameList);
	String SalesNameByCompanyName = jObject.toString();
	
	
	//获取当前信息
	FWOrder order = (FWOrder)request.getAttribute("order");
	QuotationList quote = order==null?null: order.getQuote();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta charset="utf-8" />
		<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
		<script src="js/WdatePicker.js" type="text/javascript"></script>
		<script src="js/common.js" type="text/javascript"></script>
		<script src="js/jquery.form.js" type="text/javascript"></script>
		<style type="text/css">
#addOrder {
	width: 400px;
	position: absolute;
	left: 0;
}

#tablewidget {
	position: absolute;
	left: 400px;
	right: 50px;
	top: 50px;
}

table {
	border-collapse: collapse;
}
</style>
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
						创建订单
					</li>
				</ul>
			</div>
			<div class="mainContent" style="margin: 0;">
				<div class="middle">
					<div class="header">
						<div class="work_head_cell">
							<span>创建订单</span>
						</div>
					</div>
					<form method="post" class="form" id="addOrder">
						<div class="editor-label error-label">
							<span class="error"></span>
						</div>
						<input type="hidden" name="companyId" value="<%=  quote.getCompanyName() %>"/>
						<input type="hidden" name="salesmanId" value=""<%=quote.getSalesName() %> />
						<div class="editor-label">
							<label>
								报价单:
							</label>
							<input type="hidden" name="quoteId" id="quoteId" value="<%=quote.getId() %>" />
							<%
							if(quote.getQuotationNumber()!=null && quote.getQuotationNumber()!=""){
							%>
								
								<input type="text" name="quoteNumber" id="quoteNumber"
								class="require" value="<%=quote.getQuotationNumber() %>" tip="请输入报价单号" />
							<%
							}
							else{
							%>
								<input type="text" name="quoteNumber" id="quoteNumber"
								class="require tip" value="请输入报价单号" tip="请输入报价单号" />
							<%
							}
							 %>
							
							<a href="javascript:;"><i class="fa fa-search"></i>
							</a>
						</div>
						<div class="editor-label">
							<label>
								订单总价:
							</label>
							<input type="text" name="price" id="price" class="require double" />
						</div>
						<div class="editor-label">
							<label>
								公司合同号:
							</label>
							<input type="text" name="companyOrderNumber"
								id="companyOrderNumber" class="require" />
						</div>

						<div class="editor-label">
							<label>
								合同签订时间:
							</label>
							<input type="text" name="start_at" id="start_at"
								class="require wdatepicker" 
								value="<%= new SimpleDateFormat("yyyy-mm-dd").format(new Date()) %>"/>
						</div>
						<div class="editor-label">
							<label>
								交货时间:
							</label>
							<input type="text" name="end_at" id="end_at"
								class="require wdatepicker" />
						</div>
						<div class="editor-label">
							<label>
								备注:
							</label>
							<textarea name="memo" id="memo"></textarea>
						</div>

						<div class="clear"></div>
						
						<div class="Operatings">
							<input type="submit" id="Save" class="button_work" value="确定" />
							<input type="button" id="Cancel" class="button_work" value="取消" />
						</div>
					</form>
					<div id="tablewidget">
						<label>公司名称：</label><span></span>
						<table border=1 class="wijmo">
							<thead>
								<tr>
									<th>
										样品Id
									</th>
									<th>
										样品名称
									</th>
									<th>
										数量
									</th>
									<th>
										价格
									</th>
									<th>
										备注
									</th>
							</thead>
							<tbody>
								<tr></tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</body>
	<script type="text/javascript">
	//创建订单
	$("#addOrder").submit( function() {
		if (!checkform(this)) {
			return false;
		}
		$(this).ajaxSubmit( {
			type :'post',
			url :'addOrder.do',
			dataType :'json',
			success : function(result) {
				if (result.OK) {
					alert("创建订单成功");
					//隐藏添加div或者继续添加
			$("#box_div").hide();
			location = location.pathname;
		} else {
			alert("创建订单失败,错误信息:" + result.message);
		}

	}
		});
		return false;
	});
	//创建订单
	
	/*订单商品列表*/
	//公司-业务员级联
	/*$("#companyId").change(function(){
		var companyName = $(this).find("option:selected").text();
		var SalesNameByCompanyName = $(this).attr("data");
		SalesNameByCompanyName = $.parseJSON(SalesNameByCompanyName).SalesNameByCompanyName;
		var SalesNameList = SalesNameByCompanyName[companyName];
		$("#salesmanId option:gt(0)").remove();
		if(SalesNameList == undefined){
			return;
		}
		var frag = document.createDocumentFragment();
		for(var i = 0 ; i < SalesNameList.length;++i ){
			var salesName = SalesNameList[i];
			var option = document.createElement("option");
			option.value = salesName;
			option.text = salesName;
			frag.appendChild(option);
		}
		$("#salesmanId").append(frag);
	});*/
	//公司-业务员级联
</script>
</html>