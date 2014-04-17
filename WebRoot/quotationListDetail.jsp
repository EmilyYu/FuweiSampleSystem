<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.fuwei.entity.QuotationList"%>
<%@page import="com.fuwei.util.FuweiSystemData"%>
<%@page import="com.fuwei.util.StringTODate"%>
<%@page import="com.fuwei.util.DateFormateUtil"%>
<%@page import="com.fuwei.DAO.CompanyPriceDAO"%>
<%@page import="com.fuwei.entity.CompanyPrice"%>
<%@page import="com.fuwei.DAO.SampleDAO"%>
<%@page import="com.fuwei.entity.Sample"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	QuotationList quotationList = (QuotationList) request
			.getAttribute("quotationList");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>桐庐富伟针织厂报价系统</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="js/jquery-1.10.2.min.js"></script>
		<style type="text/css">
.sss {
	text-align: center;
}

.biaoge {
	
}

.biaodan {
	text-align: left;
}
</style>

	</head>

	<body>
<%@ include file="head_left.jsp"%>
		<div id="Content">
<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li>
					<i class="icon-home home-icon"></i>
					<a href="index.jsp">首页</a>
				</li>
				<li>
					<i class=""></i>
					<a href="searchQuotationList.do">报价单列表</a>
				</li>
				<li class="active">
					报价详情
				</li>
			</ul>
		</div>
		<p>
			<br>
			<br>
			<span class="sss">
				<h2 align="center">
					桐庐富伟针织厂报价单
					</h3>
<div align="right">
					<a href="excel/<%=quotationList.getExcelName()%>.xls" style="margin-right: 200px;">下载对应excel表格</a>
</div>
			</span>
		</p>

		<table width="797" height="80" cellpadding="1" cellspacing="1"
			align="center">
			<tr>
				<td width="522" valign="top" class="biaodan">
					<font size="4"> №:<%=quotationList.getQuotationNumber()%><br>
						TO:<%=quotationList.getCompanyName()%> &nbsp;&nbsp;<%=quotationList.getSalesName()%><br>
						时间:<%=DateFormateUtil.formateDate(quotationList
									.getTime())%></font>
				</td>
			</tr>
		</table>

		<table width="797" cellpadding="1" cellspacing="1" align="center"
			border="1" bordercolor="#000000" style="border-collapse: collapse;">
			<thead>
				<tr>
					<th style="width: 80px;">
						序号
					</th>
					<th style="width: 120px;">
						图片
					</th>
					<th style="width: 125px;">
						款号
					</th>
					<th style="width: 125px;">
						材料
					</th>
					<th style="width: 125px;">
						尺寸
					</th>
					<th style="width: 90px;">
						重量
					</th>
					<th style="width: 90px;">
						价格
					</th>
				</tr>
			</thead>


			<%
				String idString = quotationList.getIdString();
				String[] ids = idString.split(",");
				Set<Integer> idSet = new HashSet<Integer>();
				for (String id : ids) {
					idSet.add(Integer.valueOf(id));
				}
				CompanyPriceDAO companyPriceDAO = new CompanyPriceDAO();
				List<CompanyPrice> companyPriceList = (List<CompanyPrice>) companyPriceDAO
						.getCompanyPriceBYIDSET(idSet);
				SampleDAO sampleDAO = new SampleDAO();
				for (int i = 0; i < companyPriceList.size(); i++) {
					CompanyPrice tempCompanyPrice = companyPriceList.get(i);
					Sample sample = sampleDAO.getSample(tempCompanyPrice
							.getSampleId());
			%>
			<tr>
				<td align="center"><%=i + 1%></td>
				<td align="center">
					<img src="image/ss<%=sample.getPicturePath()%>">
				</td>
				<td align="center">
					<%=tempCompanyPrice.getProductName()%>
				</td>
				<td align="center">
					<%=sample.getMaterial()%>
				</td>
				<td align="center">
					<%=sample.getSize()%>
				</td>
				<td align="center">
					<%=sample.getWeight()%>
				</td>
				<td align="center">
					<%=tempCompanyPrice.getPrice()%>
				</td>
			</tr>
			<%
				}
			%>

		</table>

		<p>
			&nbsp;
		</p>
</div>
	</body>
</html>
