<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@page import="com.fuwei.entity.Sample"%>
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
	List<Sample> samples = (List<Sample>) request.getAttribute("samples");
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
					样品管理
				</li>
			</ul>
		</div>
		<div class="mainContent">
			<div class="middle">
				<div class="Operatings">
					<input type="button" id="showall" value="显示全部" />
					<div class="operations_left">
						
						<a class="page_btn" href="?page=1&key=<%=key%>&type=<%=type%>&atime=<%=atime %>&btime=<%=btime %>">首页</a>
						<%if(currpage==1){ %>
						<a class="page_btn disabled">上一页</a>
						<%
						}else{ %>
						<a class="page_btn"
							href="?page=<%=currpage-1 %>&key=<%=key%>&type=<%=type%>&atime=<%=atime %>&btime=<%=btime %>">上一页</a>
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
							href="?page=<%=totalPages %>&key=<%=key%>&type=<%=type%>&atime=<%=atime %>&btime=<%=btime %>">最后一页</a>
						<form action="" class="page_go_form">
							<input type="hidden" name="key" value="<%=key %>" />
							<input type="hidden" name="type" value="<%=type %>" />
							<input type="hidden" name="atime" value="<%=atime %>" />
							<input type="hidden" name="btime" value="<%=btime %>" />
							<label class="page_go_label">
								去第
							</label>
							<input type="text" value="<%=currpage %>" name="page"
								class="page_go" />
							<label class="page_go_label">
								页
							</label>
							<input type="submit" class="page_go_btn" value="确定"></input>
						</form>
						<span class="page_totalrecords">共<%=totalRecords %>条记录</span>
						<form action="" class="searchform">
							<input type="hidden" name="key" value="<%=key %>" />
							<input type="hidden" name="type" value="<%=type %>" />
							<input type="hidden" name="page" value="<%=currpage %>" />
							<label>
								设置时间段:
							</label>
							<input type="text" name="atime" id="atime" value="<%=atime %>"
								onClick="WdatePicker()" />
							--
							<input type="text" name="btime" id="btime" value="<%=btime %>"
								onClick="WdatePicker()" />
							<input class="search" type="submit" value="" />
						</form>
					</div>
					<br />
				</div>

				<div class="TableHead-widget" style="width: 100%;">
					<table class="TableHead wijmo">
						<thead>
							<tr>
								<th width="5%">
									序号
								</th>
								<th width="10%">
									图片
								</th>
								<th width="9%">
									货号
								</th>
								<th width="9%">
									材料
								</th>
								<th width="9%">
									克重
								</th>
								<th width="9%">
									尺寸
								</th>
								<th width="9%">
									打样人
								</th>
								<th width="15%">
									创建时间
								</th>
								<th width="9%">
								</th>
							</tr>
						</thead>
						<tbody>
							<%
							for(int i = 0 ; i <samples.size();++i){
								Sample sample = samples.get(i);
							 %>
							<tr>
								<td><%=i+1 %></td>
								<td style="max-width: 120px; height: 120px; max-height: 120px;">
									<a class="cellimg" href="image/<%=sample.getPicturePath()%>"><img
											src="image/ss<%=sample.getPicturePath()%>">
									</a>
								</td>
								<td><%=sample.getProductNumber() %></td>
								<td><%=sample.getMaterial() %></td>
								<td><%=sample.getWeight() %></td>
								<td><%=sample.getSize() %></td>
								<td><%=FuweiSystemData.getDeveloperNameById(sample.getDeveloperId())  %></td>
								<td><%=DateFormateUtil.formateDate(sample.getDate()) %></td>
								<td>
									<a href="sampledetail.do?id=<%=sample.getId() %>">详情</a>
								</td>
							</tr>
							<%
							}
							if(samples.size() == 0){
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

			</div>
		</div>
</div>
		<script type="text/javascript">
	
	//新建按钮 -- 开始
    $(".btn-left_create").click(function() {
        $("#box_div .form")[0].reset();
        $("#box_div").show();

        //新建Save的点击事件
        $("#box_div .work_head_cell span").text("新建");
        $("#addSampleform").submit(function(){
        	if(!checkform()){
        		return false;
        	}
        	$(this).ajaxSubmit({
            	type: 'post',
            	url: 'addUnPricedSample.do',
            	dataType: 'json',
            	success: function (result) {
                	if(result.OK){
                    	alert("创建样品成功");
                    	//隐藏添加div或者继续添加
                    	$("#box_div").hide();
                    	location.reload();
                    }
                    else{
                    	alert("创建样品失败,错误信息:"+result.message);
                    }
                    
            	}
        	});
			return false;
        });
        //新建Save的点击事件
    });
    //新建按钮 -- 结束

    //编辑按钮 -- 开始
    $(".btn-left_edit").click(function() {
        var table = $(this).closest('.Operatings').siblings('.wijmo-table-widget').children('.workspacetable');
        var selectedTr = table.find('tbody tr.selected');
        if (selectedTr.length == 0) {
            alert("请至少选中一行！");
            return;
        }
        var row_data_json = selectedTr.attr("data");
        var row_data = $.parseJSON(row_data_json);
        $("#box_div").hide();
        $("#box_div .form")[0].reset();
        $("#box_div").show();

		fillEdit(row_data);

        //编辑Save的点击事件
        $("#box_div .work_head_cell span").text("编辑");
        $("#addSampleform").submit(function(){
        	if(!checkform()){
        		return false;
        	}
        	$(this).ajaxSubmit({
            	type: 'post',
            	url: $(this).attr("action"),
            	dataType: 'json',
            	success: function (result) {
                	if(result.OK){
                    	alert("修改样品信息成功");
                    	location.reload();
                    }
                    else{
                    	alert("修改样品信息失败,错误信息:"+result.message);
                    }
                    //隐藏添加div或者继续添加
                    $("#box_div").hide();
            	}
        	});
			return false;
        });
        //编辑Save的点击事件
    });
    //编辑按钮 -- 结束

    //删除按钮 -- 开始
    $(".btn-left_delete").click(function() {
        var table = $(this).closest('.Operatings').siblings('.wijmo-table-widget').children('.workspacetable');
        var selectedTr = table.find('tbody tr.selected');

        if (selectedTr.length == 0) {
            alert("请至少选中一行！");
            return;
        }
        var row_data_json = selectedTr.attr("data");
        var row_data = $.parseJSON(row_data_json);
        if (confirm("你确定要删除吗?") == false) {
            return;
        }

        var deleteId = getId(selectedTr[0]);
        $.ajax({
            url: url + "/" + deleteId,
            type: 'POST',
            data: JSON.stringify({})
        })
            .done(function(result) {
                if(result.OK){
                    	alert("删除样品成功");
                    	location.reload();
                    }
                    else{
                    	alert("删除样品失败,错误信息:"+result.message);
                    }
                /*隐藏添加div或者继续添加*/
                $("#box_div").hide();
            })
            .fail(function() {
                console.log("error");
                alert("删除失败！");
            })
            .always(function() {
                console.log("complete");
            });

    });
    //删除按钮 -- 结束
	
	//设置搜素内容
	$("select[name='type'] option[value= '<%=type%>' ] ").attr("selected",true);//使得select默认选中后台查询到的val
	var key="<%=key%>";
	if(key!=""){
		$("input[name='key']").val(key);
	}
	//设置搜素内容
	
    
    /*报价详情计算器*/
    /*$("#calculator").click(function(){
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
    	var bj_weigth_val = parseFloat(getDoubleValById("bj_weight"));
    	var bj_sunhao_val = parseFloat(getDoubleValById("bj_sunhao"));
    	var bj_mprice_val = parseFloat(getDoubleValById("bj_mprice"));
    	var bj_ps_val = parseFloat(getDoubleValById("bj_ps"));
    	var bj_dsxishu_val = parseFloat(getDoubleValById("bj_dsxishu"));
    	
    	var result1 = bj_weigth_val * bj_sunhao_val *12 * bj_mprice_val /1000;
    	result1 = parseFloat(result1.toFixed(3));
    	var result2 = result1 + parseFloat(getDoubleValById("bj_ps"));
    	result2 = parseFloat(result2.toFixed(3));
    	var html1 = bj_weigth_val+"*" + bj_sunhao_val + "*12*"+bj_mprice_val +"÷ 1000"
    				+ "="+result1 +"+"+bj_ps_val + "="+result2;
    	var html2 = "\n";
    	var result_bjds = 0;
    	if($("#bj_ifds")[0].checked){
    		result_bjds = bj_weigth_val * bj_sunhao_val *12 * bj_dsxishu_val/1000;
    		result_bjds = parseFloat(result_bjds.toFixed(3));
    		html2 =  html2 + result_bjds;
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
    });*/
    /*报价详情计算器*/
    
    
</script>
	</body>
</html>