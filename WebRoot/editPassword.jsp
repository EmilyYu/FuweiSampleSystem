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
						修改密码
					</li>
				</ul>
			</div>
			<div class="mainContent">
				<div class="middle">
							<form method="post" class="form" action="updatePassword.do"
								id="editPasswordForm">
								<div class="editor-label error-label"><span class="error"></span></div>
								<div class="editor-label">
									<label>
										原密码:
									</label>
									<input type="password" name="password" id="password"
										class="require"/>
								</div>
								<div class="editor-label">
									<label>
										新密码:
									</label>
									<input type="password" name="new_password" id="new_password"
										class="require"/>
								</div>
								<div class="editor-label">
									<label>
										确认新密码:
									</label>
									<input type="password" name="new_password_two" id="new_password_two"/>
								</div>
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
   //修改密码
	$("#editPasswordForm").submit(function(){
			var formEle = this;
        	if(!checkform(formEle)){
        		return false;
        	}
        	var new_password = $("#new_password").val();
        	var new_password_two = $("#new_password_two").val();
        	if(new_password!=new_password_two){
        		$(formEle).find("span.error").text("两次密码输入不一致");
        		return false;
        	}
        	if(new_password.length < 8){
        		$(formEle).find("span.error").text("密码不得少于8位");
        		return false;
        	}
        	$(this).ajaxSubmit({
            	type: 'post',
            	url: 'updatePassword.do',
            	dataType: 'json',
            	success: function (result) {
                	if(result.OK){
                    	alert("成功修改密码");
                    	location.reload();
                    }
                    else{
                    	$(formEle).find("span.error").text(result.message);
                    	//alert("创建样品失败,错误信息:"+result.message);
                    }
            	}
        	});
			return false;
        });
        //修改密码
</script>
</html>