<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@page import="com.fuwei.entity.TransQuotation"%>
<%@page import="com.fuwei.util.FuweiSystemData"%>
<%
	HashMap<String, List<TransQuotation>> quotations = null;
 	Object quotations_obj = request.getAttribute("transQuotationHashMap");
 	if(quotations_obj != null){
 		quotations  = (HashMap<String, List<TransQuotation>>) quotations_obj;
 	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta charset="utf-8" />
		<title>报价列表 -- 富伟针织厂- 管理系统</title>
		<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
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
					报价列表
				</li>
			</ul>
		</div>
		<div class="mainContent">
			<div class="middle">
				<div class="Operatings">
					<div class="operations_left">
						<form action="addQuotationList.do" method="post" id="quotationform">
							<input type="submit" id="sure_quotation" value="确定报价" />
							<input type="hidden" name="companyName" id="companyName"/>
							<input type="hidden" name="salesName" id="salesName"/>
							<input type="hidden" name="ids" id="ids"/>
						</form>
					</div>
					<br />
				</div>
				
				<div class="TableHead-widget" style="width: 100%;">
					<table class="TableHead wijmo packageTable">
						<thead>
							<tr>
								<th width="5%">
									
								</th>
								<th width="5%">
									序号
								</th>
								<th width="5%" style="display: none;">
									报价id
								</th>
								<th width="10%">
									图片
								</th>
								<th width="10%">
									公司名称
								</th>
								<th width="10%">
									业务员
								</th>
								<th width="10%">
									工厂款号
								</th>
								<th width="9%">
									价格
								</th>
								<th width="9%">
									克重
								</th>
								<th width="9%">
								</th>
							</tr>
						</thead>
						<tbody>
							<%
							Set<String> keySet=quotations.keySet();
							for (String key : keySet) {
								String[] key_split = key.split(",");
								String companyname =  key_split[0];
								String salesmanname = key_split[1];
								List<TransQuotation> transQuotationgroup=quotations.get(key);
							%>
							<tr></tr>
							<tr class="package_head" companyname="<%= companyname%>" salesmanname="<%= salesmanname%>"><td colspan="9"><input type="checkbox" class="checkgroup" />
								<i class="icon-home home-icon"></i>公司：<%= companyname%>
								<i class="fa fa-user"></i>业务员：<%= salesmanname%>
							</td></tr>
							<tr  class="package">
								<td colspan="9">
								<div class="package_tr_widget">
									<table>
										<%
										int i = 1;
								for(TransQuotation transQuotation : transQuotationgroup){
							 %>
										<tr companyPriceId="<%=transQuotation.getCompanyPriceID()%>">
											<td width="5%"><input type="checkbox" class="checkitem" /></td>
											<td width="5%"><%=i %></td>
											<td width="5%" style="display: none;"><%=transQuotation.getCompanyPriceID() %></td>
											<td width="10%"
												style="max-width: 120px; height: 120px; max-height: 120px;">
												<a class="cellimg"
													href="image/<%=transQuotation.getPictureName()%>"><img
														src="image/<%=transQuotation.getPictureName()%>">
												</a>
											</td>
											<td width="10%"><%=FuweiSystemData.getCompanyNameById(transQuotation.getCompanyId())  %></td>
											<td width="10%"><%=FuweiSystemData.getSalesManNameById(transQuotation.getSalesManId())  %></td>
											<td width="10%"><%=transQuotation.getFwStyleNumber() %></td>
											<td width="9%"><%=transQuotation.getPrice() %></td>
											<td width="9%"><%=transQuotation.getKezhong() %></td>
											<td width="9%">
												<a href="sampledetail.do?id=<%=transQuotation.getSampleID() %>">详情</a>
												| <a href="removeTransQuotation.do?companyPriceId=<%=transQuotation.getSampleID()%>&companyName=<%=FuweiSystemData.getCompanyNameById(transQuotation.getCompanyId()) %>&salesName=<%=FuweiSystemData.getSalesManNameById(transQuotation.getSalesManId()) %> ">移除</a>
											</td>
										</tr>
										<%
										++i;
							}
							 %>
									</table></div>
								</td>
							</tr>
							<%
							}
							if(quotations.size() == 0){
 							%>
							<tr>
								<td colspan="9">
									抱歉，报价列表为空
								</td>
							</tr>
							<%
							}
 							%>
						</tbody>
					</table>
				</div>
			</div></div>
</div>
		<script type="text/javascript">
    //关闭编辑box按钮 -- 开始
    $(".close_m_box,#Cancel").click(function() {
        $(this).closest(".dialog").hide();
    });
    //关闭编辑box按钮 -- 结束

	InitWijtable();
	/*表格等显示相关*/
	function InitWijtable() {
    	// 表格样式
    	
    	$('.wijmo td tbody').on("click", "tr", function() {
        	$(this).siblings().removeClass("selected");
        	$(this).addClass("selected");
    	});
    	// 表格样式
	};
	/*表格等显示相关*/
	
    /*选择相关*/
    $(".checkgroup").change(function(){
    	var package_head= $(this).closest("tr.package_head");
    	var package_tr_widget_table= package_head.next(".package").find(".package_tr_widget table");
    	var table_checkitems = package_tr_widget_table.find("td .checkitem");
    	var checked = $(this)[0].checked;
    	if(checked){//选中
    		//取消其他的报价包
    		var package_head_siblings =  package_head.siblings("tr.package_head");
    		var checkgroup_siblings = package_head_siblings.find(".checkgroup");
    		for(var i = 0 ; i < checkgroup_siblings.length ; ++i){
    			checkgroup_siblings[i].checked = false;
    			$(checkgroup_siblings[i]).change();
    		}
    		//取消其他的报价包
    		table_checkitems.prop("checked",true);
    		table_checkitems.attr("checked",true);
    	}else{
    		table_checkitems.attr("checked",false);
    	}
    	
    });
    $(".checkitem").change(function(){
    	var package_head = $(this).closest(".package").prev("tr.package_head");
    	var package_tr_widget_table= package_head.next(".package").find(".package_tr_widget table");
    	var table_checkitems_checked = package_tr_widget_table.find("td .checkitem:checked");
    	var checkgroup = package_head.find(".checkgroup");
    	if(table_checkitems_checked.length <= 0){
    		checkgroup[0].checked = false;
    	}
    	else{
    		checkgroup[0].checked = true;
    	}
    	//取消其他的报价包
    	var package_head_siblings =  package_head.siblings("tr.package_head");
    	var checkgroup_siblings = package_head_siblings.find(".checkgroup");
    	for(var i = 0 ; i < checkgroup_siblings.length ; ++i){
    		checkgroup_siblings[i].checked = false;
    		$(checkgroup_siblings[i]).change();
    	}
    	//取消其他的报价包
    });
    /*选择相关*/
    /*报价*/
    $("#quotationform").submit(function() {
    	var checked_group = $(".checkgroup:checked");
    	if(checked_group.length <=0){
    		alert("错误:请至少选择其中一行报价");
    		return false;
    	}
    	if(checked_group.length >1){
    		alert("错误:公司与业务员必须一致!");
    		return false;
    	}
    	checked_group = checked_group.first();
    	var package_head= checked_group.closest("tr.package_head");
    	var package_tr_widget_table= package_head.next(".package").find(".package_tr_widget table");
    	var table_checkitems = package_tr_widget_table.find("td .checkitem:checked");
    	if(table_checkitems.length<=0){
    		alert("错误:请至少选择其中一行报价!");
    		return false;
    	}
    	var companyname = package_head.attr("companyname");
    	var salesmanname = package_head.attr("salesmanname");
    	var ids = "";
    	for(var i = 0 ; i < table_checkitems.length;++i){
    		var companyPriceId = $(table_checkitems[i]).closest("tr").attr("companyPriceId");
    		ids = ids + companyPriceId + ",";
    	}
    	ids = ids.substring(0,ids.length-1);
    	$("#companyName").val(companyname);
    	$("#salesName").val(salesmanname);
    	$("#ids").val(ids);
        return true;
    });
    /*报价*/
</script>
	</body>
</html>