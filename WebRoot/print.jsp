<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@page import="com.fuwei.entity.Sample"%>
<%@page import="com.fuwei.util.FuweiSystemData"%>
<%@page import="com.fuwei.entity.CompanyPrice"%>
<%@page import="com.fuwei.entity.CompanyName"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Sample sample = (Sample) request.getAttribute("sample");
	List<CompanyPrice> companyPrices = (List<CompanyPrice>) request.getAttribute("companys");
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
				<li>
					<i class="icon-home home-icon"></i>
					<a href="index.jsp">首页</a>
				</li>
				<li class="active">
					快递单打印
				</li>
			</ul>
		</div>

		<div class="detail_widget">
					<form id="printform" action="printSTO.do" method="post"
						class="form">
						<div class="editor-label error-label">
							<span class="error"></span>
						</div>
						<div class="editor-label">
							<label>
								公司名称:
							</label>
							<select name="companyName" id="companyName" data='<%=SalesNameByCompanyName%>' class="require">
							<%for(CompanyName companyName:companyList){ %>
								<option value="<%=companyName.getCompanyName() %>" ><%=companyName.getCompanyName() %></option>
							<%} %>
							</select>
						</div>
						<div class="editor-label">
							<label>
								业务人员:
							</label>
							<select name="salesman" id="salesman" class="require">
							<%
							CompanyName companyName = companyList.get(0);
							List<String> salesNameList = SalesNameByCompanyNameList.get(companyName.getCompanyName());
							for(String salesName:salesNameList){ %>
								<option value="<%=salesName %>" ><%=salesName %></option>
							<%} %>
							</select>
						</div>
						
						<div class="Operatings">
							<input type="submit" id="Save" class="button_work" value="开始打印" />
						</div>
</form>
		</div>
</div>
		<script type="text/javascript">
        //新建Save的点击事件
        $("#printform").submit(function(){
        	var formEle = this;
        	if(!checkform(formEle)){
        		return false;
        	}
        	$(this).ajaxSubmit({
            	type: 'post',
            	url: $(this).attr("action"),
            	dataType: 'json',
            	success: function (result) {
                	if(result.OK){
                    	alert("打印成功");
                    	location.reload();
                    }
                    else{
                    	$(formEle).find("span.error").text(result.message);
                    	alert("打印失败,错误信息:"+result.message);
                    }
            	}
        	});
			return false;
        });
        //新建Save的点击事件
	
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
