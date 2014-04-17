<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@page import="com.fuwei.entity.*"%>
<%@page import="com.fuwei.util.FuweiSystemData"%>
<%@page import="com.fuwei.util.DateFormateUtil"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	List<UnPricedSample> unPricedSampleList = (List<UnPricedSample>) request.getAttribute("unPricedSampleList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta charset"utf-8" />
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
						待核算样品
					</li>
				</ul>
			</div>
			<div class="mainContent">
				<div class="middle">
					<div class="Operatings">

						<div class="operations_left">
							<input type="button" id="showall" value="显示全部" />
							<form id="batchform" name="batchform" action="createSampleSignServlet.do" method="post">
								<input type="hidden" id="id" name="id" />
								<input type="submit" id="batch_create" value="批量打印样品标签" />
							</form>
							<div class="editor-label">
								<label>
									打样人:
								</label>
								<select id="developer" name="developer">
									<option value="-1">
										全部
									</option>
									<%
									List<String> developerNameList = FuweiSystemData.getDeveloperNameList();
									String selected_developer = (String)request.getAttribute("developer");
									System.out.println(selected_developer);
									for(String developerName :developerNameList){
										System.out.println(developerName);
										if(developerName.equals(selected_developer)){
									 %>
									<option selected value="<%=developerName %>"><%=developerName %></option>
									<%
									}
									else{
									 %>
									<option value="<%=developerName %>"><%=developerName %></option>
									<%
									} 
									}%>
								</select>
							</div>

						</div>

					</div>

					<div class="TableHead-widget" style="width: 100%;">
						<table class="TableHead wijmo">
							<thead>
								<tr>
									<th width="5%">
										<!-- <input type="checkbox" name="checksample" />  -->
									</th>
									<th width="5%">
										序号
									</th>
									<th width="12%">
										图片
									</th>
									<th width="10%">
										款号
									</th>
									<th width="9%">
										材料
									</th>
									<th width="7%">
										克重
									</th>
									<th width="9%">
										尺寸
									</th>
									<th width="9%">
										打样人
									</th>
									<th width="9%">
										机织
									</th>
									<th width="15%">
										创建时间
									</th>
									<th width="9%">
										备注	
									</th>
								</tr>
							</thead>
							<tbody>
								<%
							for(int i = 0 ; i <unPricedSampleList.size();++i){
								UnPricedSample unPricedSample = unPricedSampleList.get(i);
							 %>
								<tr unpricedsample_id="<%=unPricedSample.getId() %>">
									<td>
										<input type="radio" name="checksample" />
									</td>
									<td><%=i+1 %></td>
									<td style="max-width: 120px; height: 120px; max-height: 120px;">
										<a class="cellimg"
											href="image/<%=unPricedSample.getPicturePath()%>"><img
												src="image/ss<%=unPricedSample.getPicturePath()%>">
										</a>
									</td>
									<td><%=unPricedSample.getProductNumber() %></td>
									<td><%=unPricedSample.getMaterial() %></td>
									<td><%=unPricedSample.getWeight() %></td>
									<td><%=unPricedSample.getSize() %></td>
									<td><%=unPricedSample.getDeveloper() %></td>
									<td><%=unPricedSample.getMachine() %></td>
									<td><%=DateFormateUtil.formateDate(unPricedSample.getDate()) %></td>
									<td>
										<%
										if(user.getAuthority() == FuweiSystemData.AUTHORITY_GENERAL){
										
										 %>
										<%=unPricedSample.getNote() %>
										<%
										}
										else{
										 %>
										<a class="calculate_sample_price" href="#"
											unpricedsample_id="<%=unPricedSample.getId() %>">核算价格</a>
										<%
										}
										 %>
									</td>
								</tr>
								<%
							}
							if(unPricedSampleList.size() == 0){
 							%>
								<tr>
									<td colspan="9">
										抱歉，搜索不到相关信息
									</td>
								</tr>
								<%
							}
 							%>
							</tbody>
						</table>
					</div>

					<!--弹出层  添加样品-->
					<div id="sample_add_widget" class="m_box dialog sample_add_widget">

						<div class="header">
							<div class="work_head_cell">
								<span>新建</span>样品
							</div>
							<span class="close_m_box"> <img src="img/close1.png"
									class="button_close" onmouseover="this.src='img/close.png'"
									onMouseOut="this.src='img/close1.png'" /> </span>
						</div>
						<div class="box_content">
							<form method="post" enctype="multipart/form-data" class="form"
								id="addSampleform">
								<input type="hidden" name="id" id="id" value="" />

								<div class="editor-label">
									<label>
										工厂货号:
									</label>
									<input type="text" name="productName" id="productName"
										class="require" readonly value="系统自动生成" />
								</div>
								<div class="editor-label">
									<label>
										打样人:
									</label>
									<select id="developer" name="developer">
										<%
										if(user.getAuthority() == FuweiSystemData.AUTHORITY_GENERAL){
										
										%>
									<option value="<%=user.getChineseName() %>"><%=user.getChineseName() %></option>
									<%
									}
									else
									{
										List<String> developerNameList2 = FuweiSystemData.getDeveloperNameList();
										for(String developerName :developerNameList2){
									 	%>
											<option value="<%=developerName %>"><%=developerName %></option>
											<%
										} 
									}%>
									</select>
								</div>
								<div class="editor-label six">
									<label>
										图片:
									</label>
									<input type="file" name="pic" id="pic" class="require" />
								</div>
								<div class="editor-label">
									<label>
										材料:
									</label>
									<input type="text" name="material" id="material"
										class="require" />
								</div>
								<div class="editor-label">
									<label>
										克重:
									</label>
									<input class="require double" type="text" name="kezhong"
										id="kezhong" />
								</div>
								<div class="editor-label">
									<label>
										尺寸:
									</label>
									<input type="text" name="size" id="size" class="require" />
								</div>
								<div class="editor-label">
									<label>
										机织:
									</label>
									<input type="text" name="machine" id="machine" class="require" />
								</div>
									<div class="editor-label">
									<label>
										备注:
									</label>
									<input type="text" name="note" id="note"  />
								</div>

								<div class="clear"></div>
								<div class="Operatings"
									style="text-align: right; margin-right: 50px; padding-bottom: 20px;">
									<input type="submit" id="Save" class="button_work" value="确定" />
									<input type="button" id="Cancel" class="button_work" value="取消" />
								</div>
							</form>
						</div>

					</div>
					<!--弹出层  添加样品-->
					<!--弹出层-->
					<div id="price_add_widget" class="m_box dialog sample_add_widget">

						<div class="header">
							<div class="work_head_cell">
								<span></span>样品价格核算
							</div>
							<span class="close_m_box"> <img src="img/close1.png"
									class="button_close" onmouseover="this.src='img/close.png'"
									onMouseOut="this.src='img/close1.png'" /> </span>
						</div>
						<div class="box_content">
							<form method="post" class="form" name="addPirceToSampleform"
								id="addPirceToSampleform">
								<div class="editor_area">
									<input type="hidden" name="id" id="id" value="" />
									<div class="editor-label">
										<label>
											基础价格:
										</label>
										<input type="text" name="price" id="price"
											class="require double" />
									</div>
								</div>
								<div class="editor_area" style="height: 250px;">
									<label>
										报价详情:
									</label>
									<a id="calculator">报价计算器</a>
									<div class="editor-label" style="height: 100%;">

										<textarea name="detail" id="detail" class="require"
											style="height: 200px; width: 300px;"></textarea>

									</div>
								</div>
								<div class="clear"></div>
								<div class="Operatings"
									style="text-align: right; margin-right: 50px; padding-bottom: 20px;">
									<input type="submit" id="Save" class="button_work" value="确定" />
									<input type="button" id="Cancel" class="button_work" value="取消" />
								</div>
							</form>
						</div>

					</div>


				</div>
			</div>

			<div class="dialog" id="calcutor_price_dialog">
				<div class="widget_head">
					<span>报价计算器</span>
					<span class="icon close_dialog"> <a href="###"></a> </span>
				</div>
				<div class="dialog_content">
					<div class="editor_area">
						<fieldset id="bj1_fieldsets" class="bj_fieldset">
							<legend>
								材料费
							</legend>
							<div class="editor-label">
								<label>
									克重：
								</label>
								<input type="text" name="bj_weight" id="bj_weight"
									class="double" />
								/克
							</div>
							
							<div class="editor-label">
								<label>
									损耗：
								</label>
								<input type="text" name="bj_sunhao" id="bj_sunhao"
									class="double" />
							</div>
							<div class="editor-label">
								<label>
									材料价格：
								</label>
								<input type="text" name="bj_mprice" id="bj_mprice"
									class="double"/>
							</div>
							<div class="editor-label">
								<label>
									倒纱系数：
								</label>
								<input type="text" name="bj_dsxishu" id="bj_dsxishu"
									class="double" disabled />
								<input type="checkbox" name="bj_ifds" id="bj_ifds" />
								<span class="bj_ifds_span">需要倒纱</span>
							</div>
						</fieldset>
						<!-- 材料费2 -->
						
						<fieldset id="bj2_fieldsets"  class="bj_fieldset">
							<legend>
								<input type="checkbox" name="bj_2_enable" id="bj_2_enable" />材料费2
							</legend>
							<div class="editor-label">
								
								<input type="text" name="bj_weight_2" id="bj_weight_2"
									class="double" />
								/克
							</div>
							
							<div class="editor-label">
							
								<input type="text" name="bj_sunhao_2" id="bj_sunhao_2"
									class="double" />
							</div>
							<div class="editor-label">
							
								<input type="text" name="bj_mprice_2" id="bj_mprice_2"
									class="double"/>
							</div>
							<div class="editor-label">
								
								<input type="text" name="bj_dsxishu_2" id="bj_dsxishu_2"
									class="double" disabled />
								<input type="checkbox" name="bj_ifds_2" id="bj_ifds_2" />
								<span class="bj_ifds_span">需要倒纱</span>
							</div>
						</fieldset>
						<!-- 材料费2 -->

						<fieldset>
							<legend>
								增值税率和利润率
							</legend>
							<div class="editor-label">
								<label>
									附加值：
								</label>
								<input type="text" name="bj_ps" id="bj_ps" class="double" />
							</div>
							<div class="editor-label">
								<label>
									增值税率：
								</label>
								<input type="text" name="bj_zzrate" id="bj_zzrate"
									class="double" />
							</div>
							<div class="editor-label">
								<label>
									利润率：
								</label>
								<input type="text" name="bj_lrate" id="bj_lrate" class="double" />
							</div>
						</fieldset>
					</div>
					<div class="editor_area gongxu">
						<fieldset>
							<legend>
								工序费
							</legend>
							<div>
								<span class="bj_gongxu_add"></span>添加工序
							</div>
							<div class="editor-label">
								<label>
									工序:
								</label>
								<select name="bj_gongxu" id="bj_gongxu">
									<%
								for(String gongxu : FuweiSystemData.getGongXuList()){
								
								 %>
									<option value="<%=gongxu %>"><%=gongxu %></option>
									<%} %>
								</select>
								<label>
									价格:
								</label>
								<input type="text" name="bj_price" id="bj_price" class="double" />
								<span class="bj_gongxu_delete"></span>
							</div>
						</fieldset>

					</div>
					<div class="editor_area cal_result">
						<fieldset>
							<legend>
								报价计算结果
							</legend>
							<textarea id="bj_result"></textarea>
						</fieldset>
					</div>
					<div class="clear"></div>
					<div class="Operatings">
						<input type="button" class="button_work" id="bj_save" value="保存" />
					</div>

				</div>
			</div>
			
		</div>
<script type="text/javascript">
	/*材料费2*/
	//材料费2的倒纱
	$("#bj_ifds_2").unbind("change");
    $("#bj_ifds_2").change(function(){
    	if(this.checked){
    		$("#bj_dsxishu_2").removeAttr("disabled");
    	}else{
    		$("#bj_dsxishu_2").attr("disabled","disabled");
    	}
    	bj_result_fill();
    });
    //材料费2的倒纱
    
    //材料费2的所有input
    $("#bj_2_enable").change(function(){
    	if(this.checked){
    		$("#bj2_fieldsets .editor-label input").removeAttr("disabled");
    		$("#bj_ifds_2").change();
    	}else{
    		$("#bj2_fieldsets .editor-label input").attr("disabled","disabled");
    		$("#bj_ifds_2").change();
    	}
    	bj_result_fill();
    });
    $("#bj_2_enable").change();
    //材料费2的所有input
	/*材料费2*/
	
	//批量选中
	$("th input[type='checkbox']").change(function(){
		var allcheck = this.checked;
		if(allcheck){
			$("td input[type='checkbox']").prop("checked",true);
			$("td input[type='checkbox']").attr("checked",true);
		}else{
			$("td input[type='checkbox']").attr("checked",false);
		}
	});
	$("#batchform").submit(function(){
		var $checkeds = $(".TableHead-widget>table>tbody>tr input[name='checksample']:checked");
		if($checkeds.length <=0){
			alert("请至少选中一行！");
			return false;
		}
		var $checkedTrs = $checkeds.closest("tr");
		var ids = "";
		for(var i = 0 ; i < $checkedTrs.length;++i){
			var checkedTr = $checkedTrs[i];
			var unpricedsample_id = $(checkedTr).attr("unpricedsample_id");
			ids = ids + unpricedsample_id + ",";
		}
		ids = ids.substring(0,ids.length-1);
		$("#id").val(ids);
		
		$.ajax({
            	type: 'post',
            	url: 'createSampleSignServlet.do',
            	dataType: 'json',
            	data:{id:ids},
            	success: function (result) {
                	if(result.OK){        
                		alert("批量生成标签成功!");	
                    	location.reload(); 	
                    }
                    else{
                    	alert("批量生成标签失败,错误信息:"+result.message);
                    }    
            	},
            	error:function(result){
            		alert("失败，错误信息:"+result.responseText);
            	}
        	});
        	
		return false;
	});
	//批量选中 
	
	//打样人改变事件
	$(".Operatings #developer").change(function(){
		if(this.value == "-1"){
			location = location.pathname;
			return;
		}
		location = location.pathname+"?developer="+this.value;
	});
	//打样人改变事件
	
	$(".btn-left_create").click(function() {
		$(".dialog").hide();
        $("#sample_add_widget .form")[0].reset();
        $("#sample_add_widget").show();
    });
 
    //修改价格核算
    $(".calculate_sample_price").click(function(){
		$(".dialog").hide();
		$("#price_add_widget .form")[0].reset();
		var id = $(this).attr("unpricedsample_id");
		$("#addPirceToSampleform #id").val(id);
		$("#price_add_widget").show();
		return false;
	});
	$("#addPirceToSampleform").submit(function(){
		var form_data = $(this).serialize();
        	if(!checkform(this)){
        		return false;
        	}
        	$.ajax({
            	type: 'post',
            	url: 'addPirceToSample.do',
            	dataType: 'json',
            	data:form_data,
            	success: function (result) {
                	if(result.OK){
                    	//alert("修改样品核算价格成功");
                    	var sample_id = result.sample_id;
                    	location = "sampledetail.do?id="+sample_id;
                    	//隐藏添加div或者继续添加
                    	//$("#box_div").hide();
                    	//location.reload();
                    }
                    else{
                    	alert("修改样品核算价格,错误信息:"+result.message);
                    }
                    
            	},
            	error:function(result){
            		alert("失败，错误信息:"+result.responseText);
            	}
        	});
			return false;
        });
        //修改价格核算 
    /*报价详情计算器*/
    $("#calculator").click(function(){
    	$("#calcutor_price_dialog").show();
    	$(".background").show();
    	return false;
    });
    $(".bj_gongxu_add").click(function(){
    	var editor_lable = $(".gongxu .editor-label").first();
    	var new_editor_lable = editor_lable.clone(true);
    	new_editor_lable.find(".bj_gongxu_delete").css("display","inline-block");
    	$(".gongxu fieldset").append(new_editor_lable);
    	bj_result_fill();
    });
    $(".bj_gongxu_delete").click(function(){
    	$(this).closest(".editor-label").remove();
    	bj_result_fill();
    });
    
    
    $("#calcutor_price_dialog .editor_area").on("input propertychange change","input,select",function(){
    	bj_result_fill();
    });
    $("#bj_ifds").unbind("change");
    $("#bj_ifds").change(function(){
    	if(this.checked){
    		$("#bj_dsxishu").removeAttr("disabled");
    	}else{
    		$("#bj_dsxishu").attr("disabled","disabled");
    	}
    	bj_result_fill();
    });
	function getDoubleValById(id){
		var value = $("#"+id).val();
		if(value == ""){
			return "0";
		}else{
			return value;
		}
	}
	function getDoubleValByVal(value){
		if(value == ""){
			return "0";
		}else{
			return value;
		}
	}
    function bj_result_fill(){
    	/*材料费1*/
    	var bj_weigth_val = parseFloat(getDoubleValById("bj_weight"));//克重
    	var bj_sunhao_val = parseFloat(getDoubleValById("bj_sunhao"));//损耗
    	var bj_mprice_val = parseFloat(getDoubleValById("bj_mprice"));//
    	var bj_dsxishu_val = parseFloat(getDoubleValById("bj_dsxishu"));//倒纱系数
    	var bj_1 =  bj_weigth_val * bj_sunhao_val *12 * bj_mprice_val /1000;//材料1价格
    	bj_1 = parseFloat(bj_1.toFixed(3));
    	var bj_1_ds = bj_weigth_val * bj_sunhao_val *12 * bj_dsxishu_val/1000;//材料1倒纱
    	bj_1_ds = parseFloat(bj_1_ds.toFixed(3));
    	/*材料费1*/
    	
    	/*材料费2*/
    	var bj_weigth_2_val = parseFloat(getDoubleValById("bj_weight_2"));//克重
    	var bj_sunhao_2_val = parseFloat(getDoubleValById("bj_sunhao_2"));//损耗
    	var bj_mprice_2_val = parseFloat(getDoubleValById("bj_mprice_2"));//
    	var bj_dsxishu_2_val = parseFloat(getDoubleValById("bj_dsxishu_2"));//倒纱系数
    	var bj_2 =  bj_weigth_2_val * bj_sunhao_2_val *12 * bj_mprice_2_val /1000;//材料2价格
    	bj_2 = parseFloat(bj_2.toFixed(3));
    	var bj_2_ds = bj_weigth_2_val * bj_sunhao_2_val *12 * bj_dsxishu_2_val/1000;//材料2倒纱
    	bj_2_ds = parseFloat(bj_2_ds.toFixed(3));
    	/*材料费2*/
   
    
    	var bj_2_enable_checked = $("#bj_2_enable")[0].checked;//是否需要计算材料费2
    	
    	var bj_ps_val = parseFloat(getDoubleValById("bj_ps"));//附加值
    	
    	var result1 = bj_1 ;//结果1 = 材料1价格 + 材料2价格（若选中）
    	if(bj_2_enable_checked){
    		result1 = result1 + bj_2;
    	}
    	result1 = parseFloat(result1.toFixed(3));
    	var result2 = result1 + bj_ps_val;//结果2 = 结果1  + 附加值
    	result2 = parseFloat(result2.toFixed(3));
    	var html1 = "";
    	if(bj_2_enable_checked){
    		html1 = html1 + "材料1：" + bj_weigth_val+"*" + bj_sunhao_val + "*12*"+bj_mprice_val +"÷ 1000"
    				+ "="+bj_1;
    		html1 = html1 + "\n" + 
    				"材料2：" + bj_weigth_2_val+"*" + bj_sunhao_2_val + "*12*"+bj_mprice_2_val +"÷ 1000"
    				+ "="+bj_2;
    		html1 = html1 + "\n" + bj_1 + "+" + bj_2 + "=" + result1 + "+" + bj_ps_val + "=" + result2;
    						
    	}else{
    		html1 = bj_weigth_val+"*" + bj_sunhao_val + "*12*"+bj_mprice_val +"÷ 1000"
    				+ "="+result1 +"+"+bj_ps_val + "="+result2;
    	}
    	
    	var html2 = "\n";
    	var result_bjds = 0;//倒纱价格
    	if($("#bj_ifds")[0].checked){//倒纱价格1
    		result_bjds = bj_1_ds;
    		result_bjds = parseFloat(result_bjds.toFixed(3));
    		html2 =  html2 + result_bjds;
    	}
    	if($("#bj_ifds_2")[0].checked){//倒纱价格2
    		result_bjds = result_bjds + bj_2_ds;
    		result_bjds = parseFloat(result_bjds.toFixed(3));
    		html2 =  html2+ "+" + bj_2_ds + "=" + result_bjds;
    	}
    	
    	var html3 = "\n";
    	var bj_gongxu_eles = $("select[name='bj_gongxu']");
    	var result3 = 0;
    	for(var i = 0 ; i < bj_gongxu_eles.length ; ++i){
    		var bj_gongxu_ele = bj_gongxu_eles[i];
    		var gongxuname = bj_gongxu_ele.value;
    		var gongxuprice = $(bj_gongxu_ele).closest(".editor-label").find("input[name='bj_price']").val();
    		gongxuprice = getDoubleValByVal(gongxuprice);
    		gongxuprice = parseFloat(gongxuprice).toFixed(3);
    		html3 = html3 + gongxuname + ":" + gongxuprice + "\n";
    		result3 = result3 + parseFloat(gongxuprice);
    	}
    	result3 = parseFloat(result3.toFixed(3));
    	html3 = html3 + "____________________________\n";
    	var bj_zzrate_val = parseFloat(getDoubleValById("bj_zzrate"));
    	var bj_lrate_val = parseFloat(getDoubleValById("bj_lrate"));
    	var result4 = result3 + result_bjds;
    	result4 = parseFloat(result4.toFixed(3));
    	var result5 = result4 * bj_zzrate_val;
    	result5 = parseFloat(result5.toFixed(3));
    	var result6 = result5 + result2;
    	result6 = parseFloat(result6.toFixed(3));
    	var result7 = result6/12;
    	result7 = parseFloat(result7.toFixed(3));
    	var result8 = result7 * bj_lrate_val;
    	result8 = parseFloat(result8.toFixed(3));
    	var html4 = result3 + "+" + result_bjds + "\n="
    				+ result4 + "*" + bj_zzrate_val + "\n="
    				+ result5 + "+" + result2 + "\n="
    				+ result6 + "÷12" + "\n="
    				+ result7 + "*" + bj_lrate_val  + "\n="
    				+ result8 + "\n";
    	$("#bj_result").val( html1 + html2 + html3 + html4);
    }
    
    $("#bj_save").click(function(){
    	var value = $("#bj_result").val();
    	$("#detail").val(value);
    	$("#calcutor_price_dialog").hide();
    	$(".background").hide();
    });
    /*报价详情计算器*/
    
    
</script>
	</body>
</html>