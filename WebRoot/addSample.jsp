<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.fuwei.util.FuweiSystemData"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

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
						新增样品
					</li>
				</ul>
			</div>
			<div class="mainContent" style="margin:0;">
				<div class="middle">
<div class="header">
					<div class="work_head_cell">
						<span>新增样品</span>
					</div>
				</div>
							<form method="post" enctype="multipart/form-data" class="form"
								id="addSampleform">
								<div class="editor-label error-label"><span class="error"></span></div>
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
								<div class="editor-label">
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
									<input type="text" name="note"
										id="note" value="" style='width:400px;'/>
								</div>

								<div class="clear"></div>
								<div class="Operatings">
									<input type="submit" id="Save" class="button_work" value="确定" />
									<input type="button" id="Cancel" class="button_work" value="取消" />
								</div>
							</form>
						</div>
				</div>
		</div>

	</body>
<script type="text/javascript">
   //新建样品
	$("#addSampleform").submit(function(){
        	if(!checkform(this)){
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
                    	location = location.pathname;
                    }
                    else{
                    	alert("创建样品失败,错误信息:"+result.message);
                    }
                    
            	}
        	});
			return false;
        });
        //新建样品
</script>
</html>