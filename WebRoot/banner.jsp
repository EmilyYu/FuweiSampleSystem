<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta charset="utf-8" />
		<title>富伟针织厂- 管理系统</title>
		<link href="css/global.css" rel="stylesheet" type="text/css" />
		<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<!--web字体-->
		<script src="js/common.js" type="text/javascript"></script>
		<script src="js/WdatePicker.js" type="text/javascript"></script>
	</head>
	<body>
 <div style="display:none;" class="background"></div>
		<div class="fixed_head" id="fixed_head">
	<div class="breadcrumbs" id="breadcrumbs">
				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a href="#">首页</a>
					</li>
					<li>
						<a href="#">基础资料</a>
					</li>
					<li class="active">
						样品
					</li>
				</ul>
			</div>
			<form name="head_search_form" id="head_search_form" action="search.do">
				<div class="editor-label search_triggers">
					<select name="type" class="search_triggers_select">
						<option value="1" selected>
							按款号搜索
						</option>
						<option value="2">
							按公司搜索
						</option>
						<option value="3">
							按打样人搜索
						</option>
						<option value="4">
							按公司业务员搜索
						</option>
					</select>
					
<input type="text" autocomplete="off" name="key"
						id="searchQuery" class="autocomplete tip searchText" tip="请输入样品名称"
						value="请输入样品名称" />
<div class="autocompletediv">
						<ul class="autocomplete_ul">
						</ul>
					</div>

					
					<input class="searchQueryBtn search" type="submit" value=""/>


					
				</div>
				<a href="#" id="advanced_search">高级搜索</a>
			</form>
			<div class="dialog advanced_search_dialog">
				<div class="widget_head">
					<span>高级搜索</span>
					<span class="icon close_dialog"> <a href="###"></a> </span>
				</div>
				<div class="dialog_content">
					<form action="search.do" method="get">
						<div class="editor-label">
							<label>
								款号:
							</label>
							<input type="text" name="productNumber" id="productNumber" />
						</div>
						<div class="editor-label">
							<label>
								公司:
							</label>
							<input type="text" name="companyName" id="companyName" />
						</div>
						<div class="editor-label">
							<label>
								打样人:
							</label>
							<input type="text" name="price" id="price" />
						</div>
						<div class="editor-label">
							<label>
								业务员:
							</label>
							<input type="text" name="salesman" id="salesman" />
						</div>

						<div class="editor-label" style="width: 500px;">
							<label>
								创建时间段:
							</label>
							<input type="text" name="salesman" id="salesman" onClick="WdatePicker()" />
							---
							<input type="text" name="salesman" id="salesman" onClick="WdatePicker()"/>
						</div>
						<div class="Operatings">
							<input type="submit" class="button_work" value="搜索" />
							<input type="button" id="Cancel" class="button_work" value="取消" />
						</div>
					</form>

				</div>


			</div>

		</div>

	
	</body>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
window.onscroll = function(){ 
    var t = document.documentElement.scrollTop || document.body.scrollTop;  
    if( t > 50 ) { 
        $("#fixed_head").css("position","fixed");
    } else { 
        $("#fixed_head").css("position","relative");
    } 
}
$("#advanced_search").click(function(){
	$(".advanced_search_dialog").show();
	$(".background").show();
	return false;
});
/*搜索相关函数*/
	$(".autocomplete").bind("focus click", function() {
	    var tip = $(this).attr("tip");
	    if (this.value == tip) {
	        this.value = "";
	        $(this).removeClass('tip');
	    }
	});
	
	$(".search_triggers").bind("focusout",function() {
		var autodiv = $(this).find(".autocompletediv");
		var searchQuery = $(this).find(".autocomplete")[0];
	    var tip = $(searchQuery).attr("tip");
	    if (searchQuery.value == "") {
	    	searchQuery.value = tip;
	        $(searchQuery).addClass('tip');
	    }
	    autodiv.slideUp();
	});
	
	$(".autocomplete").keyup(function(event) {
		var autoul = $(this).siblings(".autocompletediv").find(".autocomplete_ul");
		var autoul_lis = autoul.children("li");
		var selectedli = autoul.children("li.selected");
	    if(event.keyCode==40){//下方向键操作
	    	
	    	var next = selectedli.next();
	    	var current = null;
	    	if(selectedli.length == 0 || next.length == 0){
	    		current = autoul_lis.first();
	    	}
	    	else{
	    		current = next;
	    	}
	    	autoul_lis.removeClass("selected");
	    	current.addClass("selected");
	    	$(this).val(current.attr("value"));
	        $(this).focus();
	    }
	    else if(event.keyCode==38){//下方向键操作
	    	var prev = selectedli.prev();
	    	var current = null;
	    	if(selectedli.length == 0 || prev.length == 0){
	    		current = autoul_lis.first();
	    	}
	    	else{
	    		current = prev;
	    	}
	    	autoul_lis.removeClass("selected");
	    	current.addClass("selected");
	    	$(this).val(current.attr("value"));
	        $(this).focus();
	    }
	});

	$(".autocomplete").unbind("input propertychange");
	$(".autocomplete").bind("input propertychange click", function () {
		var autodiv = $(this).siblings(".autocompletediv");
		var autoul = autodiv.children(".autocomplete_ul");
		var selecttrigger = $(this).siblings(".search_triggers_select");
		var value = this.value;
		var searchtype = selecttrigger.val();
		if(value == ""){
			return;
		}
	    $.ajax({
	        url: 'getInputValue.do',
	        type: 'GET',
	        dataType: 'json',
	        data: {searchtype: searchtype,searchQuery:value},
	    })
	    .done(function(result) {
	        console.log("success");
	        autoul.empty();
	        var frag = document.createDocumentFragment();
	        result = result.value;
	        for(var i = 0 ; i<result.length;++i){
	            var li = document.createElement("li");
	            $(li).text(result[i]);
	            $(li).attr("value",result[i]);
	            frag.appendChild(li);
	        }
	        
	        
	        autoul.append(frag);
	        autodiv.show();
	        
	    })
	    .fail(function(result) {
	        console.log("error");
	        autoul.empty();
	    })
	    .always(function() {
	        console.log("complete");
	    });
	    
		
	});
	
	$('.autocomplete_ul').on('mouseup', 'li', function() {
		var valEle = $(this).parent().parent().siblings(".autocomplete");
		var autodiv = $(this).parent().parent();
		valEle.val($(this).attr("value"));
        valEle.focus();
        autodiv.hide();
    });
	$('.autocomplete_ul').on('mouseover', 'li', function() {
		var autoul = $(this).parent().find("li").removeClass("selected");
		$(this).addClass("selected");
    });
	$('.autocomplete_ul').on('mouseout', 'li', function() {
    	$(this).removeClass("selected");
    });
	/*搜索相关函数*/
	
	$(".close_dialog").click(function(){
		$(this).closest(".dialog").hide();
		$(".background").hide();
	});
	$("#Cancel").click(function(){
		$(this).closest(".dialog").hide();
		$(".background").hide();
	});
	});
</script>