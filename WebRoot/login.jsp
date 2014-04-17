<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
	Object obj = request.getAttribute("message");
	String message = "";
	if(obj != null){
		message = (String)request.getAttribute("message");
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<link href="css/login-cleaned.css" rel="stylesheet" type="text/css" />
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
 <script src="js/jquery-1.10.2.min.js"></script>
  <script src="js/common.js" type="text/javascript"></script>
<title>登录</title>
</head>
<body>
<div style="display:none;" class="background"></div>
<div style="display:none;" id="loading">正在登录，请稍等......</div>

<div class="login-container" id="container-height">
<div class="logo">
<h1>
							<i class="fa fa-leaf"></i>
							桐庐富伟针织厂管理系统
						</h1>
</div>
    <div class="png login-cover-layer">
    
        <div class="login-content" id="main">
            <div class="login-left">
                <div class="login-left-header">
                    <div class="title"></div>
                </div>
                <div class="png login-left-body-common"></div>
            </div>
            <div class="login-right">
                <div class="login-box">
                    <div class="login-box-header">
                        <div class="login-logo"><a href="#"></a></div>
                    </div>
                    <div class="login-box-body">
                        <form class="login-form" id="loginform" name="loginform" action="login" method="post">
                            <div class="logineditor">
                            <span class="login-error" id="error"><%=message %></span>
                        </div>
                        <div class="logineditor">
                            <input type="text" name="userName" id="user_name" tip="请输入用户名" class="login_user_name" value="请输入用户名"/>
                        </div>
                        <div class="logineditor">
                            <input type="password" name="password" id="password" class="login_password" value="" style="display:none;"/>
                            <input type="text" name="empty_password" id="empty_password" class="login_password" value="请输入密码"/>
                        </div>
                        <div class="logineditor">
                            <button type="submit" id="login" class="btn login_login">
                                <span class="btn-left"><span class="btn-right">
                                    <span class="btn-inner">登录</span></span></span>
                            </button>
                        </div>                    
                        </form>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<div class="footer" id="footer">
    
    <p class="copyright"><a href="#" target="_blank" style="margin-right:5px"></a> Copyright © 2014 桐庐富伟针织厂</p>
</div>

<script type="text/javascript">
$("#user_name").bind("focus click",function(){
    var value = $(this).val();
    var tip = $(this).attr("tip");
    if(value == tip){
        $(this).val("");
    }
});
$("#user_name").bind("blur",function(){
    var value = $(this).val();
    var tip = $(this).attr("tip");
    if(value == ""){
        $(this).val(tip);
    }
});
$("#password").bind("blur",function(){
    var value = $(this).val();
    if(value == ""){
        $(this).hide();
        $("#empty_password").show();
    }
});
$("#empty_password").bind("click focus",function(){
    $(this).hide();
    $("#password").show();
    $("#password").focus();
});

$("#loginform").submit(function(event) {
    //alert("ddd");
    var name=document.loginform.user_name;
    var password=document.loginform.password;
    var company=document.loginform.enterpriseId;
    if(name.value==""||name.value=="请输入用户名"){
        document.getElementById("error").style.display="block";
        document.getElementById("error").innerHTML="请输入用户名";
        name.focus();
        return false;
    }
    else if(password.value==""||password.value=="请输入密码"){
        document.getElementById("error").style.display="block";
        document.getElementById("error").innerHTML="请输入密码";
        $("#empty_password").click();
        return false;
    }
    return true;
});

</script>
</body>
</html>
