<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@page import="com.fuwei.entity.Sample"%>
<%@page import="com.fuwei.util.FuweiSystemData"%>
<%@page import="com.fuwei.entity.CompanyPrice"%>
<%@page import="com.fuwei.entity.CompanyName"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.fuwei.util.DateFormateUtil"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Sample sample = (Sample) request.getAttribute("sample");
	System.out.println("dsadas2:"+sample.toString());
	List<CompanyPrice> companyPrices = (List<CompanyPrice>) request.getAttribute("companys");
	System.out.println("dsadas2:"+companyPrices.size());
	List<CompanyName> companyList=FuweiSystemData.getCompanyNameList();
	HashMap<String, List<String>> SalesNameByCompanyNameList = FuweiSystemData.getSalesNameByCompanyName();
	JSONObject jObject = new JSONObject();
	jObject.put("SalesNameByCompanyName", SalesNameByCompanyNameList);
	String SalesNameByCompanyName = jObject.toString();
%>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'sampleDetail.jsp' starting page</title>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

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
				<li><i class="icon-home home-icon"></i> <a href="index.jsp">首页</a>
				</li>
				<li><i class="icon-home home-icon"></i> <a
					href="searchSample.do">样品管理</a></li>
				<li class="active">样品详情</li>
			</ul>
		</div>

		<div class="detail_widget">
			<form id="batchform" name="batchform"
				action="createSampleSignServlet.do" method="post">
				<input type="hidden" id="id" name="id" value="<%=sample.getId() %>" />
				<input type="hidden" id="type" name="type" value="sample" /> <input
					type="submit" id="batch_create" value="生成样品标签" />
			</form>
			<div class="sample_widget">
				<div class="sample_display">
					<table>
						<tr valign=middle>
							<td><a href="image/<%=sample.getPicturePath()%>"><img
									src="image/s<%=sample.getPicturePath()%>"> </a></td>
						</tr>
					</table>
				</div>
				<div class="sample_data">
					<ul>
						<li><span class="li_name">款号</span>
						<div class="li_value_div">
								<span class="li_value"><%=sample.getProductNumber()%></span>
							</div></li>
						<li><span class="li_name">打样人</span>
						<div class="li_value_div">
								<span class="li_value"><%=sample.getDeveloper()%></span>
							</div></li>
						<li><span class="li_name">尺寸</span>
						<div class="li_value_div">
								<span class="li_value"><%=sample.getSize()%></span>
							</div></li>
						<li><span class="li_name">克重</span>
						<div class="li_value_div">
								<span class="li_value"><%=sample.getWeight()%>&nbsp;&nbsp;克</span>
							</div></li>
						<li><span class="li_name">材料</span>
						<div class="li_value_div">
								<span class="li_value"><%=sample.getMaterial()%></span>
							</div></li>
						<li><span class="li_name">机织</span>
						<div class="li_value_div">
								<span class="li_value"><%=sample.getMachine()%></span>
							</div></li>
						<li><span class="li_name">基础价格</span>
						<div class="li_value_div">
								<span class="li_value">
									<%if(3!=user.getAuthority()){ %><%=sample.getCost()%>&nbsp;&nbsp;元<%}else{ %><%="不可见" %>
									<%} %>
								</span>
							</div></li>
						<li><span class="li_name">录入时间</span>
						<div class="li_value_div">
								<span class="li_value"><%=DateFormateUtil.formateDate(sample.getDate())%></span>
							</div></li>

					</ul>
				</div>
				<%if(3!=user.getAuthority()){ %>
				<div class="sample_data">
					<fieldset>
						<legend> 报价详情 </legend>
						<textarea readonly><%=sample.getDetail()%></textarea>
						</li>
					</fieldset>
				</div>
				<%} %>

			</div>


			<div class="table_name">
				已报价公司列表
				<button class="btn" id="create" value="新建公司报价">新建公司报价</button>
			</div>
			<table align="center" class="wijmo">
				<thead>
					<tr>
						<th>序号</th>
						<th>公司名称</th>
						<th>公司款号</th>
						<th>业务员</th>
						<th>所报价格</th>
						<th>录入时间</th>
						<th>备注</th>
						<% if(1==user.getAuthority()){%>
						<th>操作</th>
						<% }%>
					</tr>
				</thead>

				<%
						for (int index = 0; index < companyPrices.size(); index++) {
							CompanyPrice companyPrice = companyPrices.get(index);
					%>
				<tr>
					<td><%=index+1%></td>
					<td><%=companyPrice.getCompanyName()%></td>

					<td><%=companyPrice.getProductName()%></td>
					<td><%=companyPrice.getSalesMan()%></td>
					<td>
						<%if(3!=user.getAuthority()){ %><%=companyPrice.getPrice()%>
						<%}else{ %><%="不可见"%>
						<% }%>
					</td>
					<td><%=DateFormateUtil.formateDate(companyPrice.getTime())%></td>
					<td><%=companyPrice.getNote()%></td>
					<% if(1==user.getAuthority()){%>
					<td><a class="add_to_pricelist" href="#"
						id="<%=companyPrice.getId()%>">添加到报价列表</a>
						<a class="print" href="#"
						companyPriceID="<%=companyPrice.getId() %>">打印样品详情</a>
						</td>
					<% }%>
				</tr>
				<%
						}
					%>
			</table>

			<!--弹出层-->
			<!--弹出层-->
			<div id="box_div" class="m_box dialog">

				<div class="header">
					<div class="work_head_cell">
						<span>新建</span>公司报价
					</div>
					<span class="close_m_box"> <img src="img/close1.png"
						class="button_close" onmouseover="this.src='img/close.png'"
						onMouseOut="this.src='img/close1.png'" />
					</span>
				</div>
				<div class="box_content">
					<form id="addcompanyform" action="addcompanyprice.do" method="post"
						class="form">
						<div class="editor-label error-label">
							<span class="error"></span>
						</div>
						<input type="hidden" name="sampleid" id="sampleid"
							value="<%=sample.getId()%>" />
						<div class="editor-label">
							<label> 公司名称: </label> <select name="companyName"
								id="companyName" data='<%=SalesNameByCompanyName%>'
								class="require">
								<%for(CompanyName companyName:companyList){ %>
								<option value="<%=companyName.getCompanyName() %>"><%=companyName.getCompanyName() %></option>
								<%} %>
							</select>
						</div>
						<div class="editor-label">
							<label> 业务人员: </label> <select name="salesman" id="salesman"
								class="require">
								<%
								if(companyList!=null & companyList.size()>0){
									CompanyName companyName = companyList.get(0);
									List<String> salesNameList = SalesNameByCompanyNameList.get(companyName.getCompanyName());
									if(salesNameList != null){
										for(String salesName:salesNameList){
									
									
							 %>
								<option value="<%=salesName %>"><%=salesName %></option>
								<%} }}%>
							</select>
						</div>
						<div class="editor-label">
							<label> 公司货号: </label> <input type="text" name="productNumber"
								id="productNumber" class="require" />
						</div>
						<div class="editor-label">
							<label> 样品价格: </label> <input type="text" name="price" id="price"
								class="double require" />
						</div>

						<div class="editor-label">
							<label> 报价备注: </label> <input type="text" name="note" id="note" />
						</div>
						<div class="Operatings">
							<input type="submit" id="Save" class="button_work" value="保存" />
							<input type="button" id="Cancel" class="button_work" value="取消" />
						</div>
					</form>
				</div>

			</div>
		</div>
		
		<div class="dialog" id="print_dialog">
				<div class="widget_head">
					<span>打印机选择</span>
					<span class="icon close_dialog"> <a href="###"></a> </span>
				</div>
				<div class="dialog_content">
					<form name="printform" id="printform">
						<input type="hidden" name="sampleID" id="sampleID" value="<%=sample.getId()%>"/>
						<input type="hidden" name="companyPriceID" id="companyPriceID"/>
						<div class="editor-label">
							<select name="printerName" id="printerName"></select>
						</div>
						<div class="editor-label">
							<input type="submit" value="打印" />
							<input type="button" id="Cancel" value="取消" />
						</div>
					</form>
				</div>
			</div>
	
	</div>
	<script type="text/javascript">
	//打印样品详情
	$.ajax({
        	type: 'get',
        	url: 'getSampleDetailPrinterName.do',
        	dataType: 'json',
        	success: function (result) {
        		Common.fillSelect($("#printform #printerName")[0],result.printerName); 
        	},
        	error:function(result){
        		alert("获取打印机列表失败，错误信息:"+result.responseText);
        	}
    	});
	$(".print").click(function(){
		var companyPriceID = $(this).attr("companyPriceID");
		$("#printform #companyPriceID").val(companyPriceID);
		$("#print_dialog").show();
		return false;
	});
	//打印样品详情
	//打印
	$("#printform").submit(function(){
		var form_data = $(this).serialize();
		$.ajax({
        	type: 'post',
        	url: 'printSampleDetail.do',
        	dataType: 'json',
        	data:form_data,
        	success: function (result) {
        		if(result.OK){
        			alert("打印成功！");
        		}else{
        			alert("打印失败！");
        		}
        	},
        	error:function(result){
        		alert("打印失败，错误信息:"+result.responseText);
        	}
    	});
		return false;
	});
	//打印
	
	//新建按钮 -- 开始
    $("#create").click(function() {
        $("#box_div .form")[0].reset();
        $("#box_div").show();

        //新建Save的点击事件
        $("#box_div .work_head_cell span").text("新建");
        $("#addcompanyform").submit(function(){
        	if(!checkform(this)){
        		return false;
        	}
        	$(this).ajaxSubmit({
            	type: 'post',
            	url: $(this).attr("action"),
            	dataType: 'json',
            	success: function (result) {
                	if(result.OK){
                    	alert("添加成功");
                    	location.reload();
                    }
                    else{
                    	alert("添加失败,错误信息:"+result.message);
                    }
            	}
        	});
			return false;
        });
        //新建Save的点击事件
    });
    //新建按钮 -- 结束
	
	//添加到报价列表
	$(".add_to_pricelist").click(function(){
		var id = $(this).attr("id");
		var operationType = $(this).attr("operationType");
		$.ajax({
            url: "quotationServlet.do",
            type: 'POST',
            dataType: 'json',
            data: {
            	companyPriceID:id,
            	operationType:"<%=FuweiSystemData.ADD_QUOTATION%>"
            }
        })
            .done(function(result) {
                if(result.OK){
                    	alert("添加到报价列表成功");
                    	location.reload();
                    }
                    else{
                    	alert("添加到报价列表失败,错误信息:"+result.message);
                    }
                /*隐藏添加div或者继续添加*/
                $("#box_div").hide();
            })
            .fail(function() {
                console.log("error");
                alert("添加到报价列表失败！");
            })
            .always(function() {
                console.log("complete");
            });
            return false;
	});
	
	//添加到报价列表
	
	//公司-业务员级联
	$("#companyName").change(function(){
		var companyName = $(this).val();
		var SalesNameByCompanyName = $(this).attr("data");
		SalesNameByCompanyName = $.parseJSON(SalesNameByCompanyName).SalesNameByCompanyName;
		var SalesNameList = SalesNameByCompanyName[companyName];
		$("#salesman").empty();
		var frag = document.createDocumentFragment();
		for(var i = 0 ; i < SalesNameList.length;++i ){
			var salesName = SalesNameList[i];
			var option = document.createElement("option");
			option.value = salesName;
			option.text = salesName;
			frag.appendChild(option);
		}
		$("#salesman").append(frag);
	});
	//公司-业务员级联
	
</script>
</body>
</html>
