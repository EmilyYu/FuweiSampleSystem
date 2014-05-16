var loadingNumbers = 0;
$(document).ajaxSend(function() {
    Common.loading();
    loadingNumbers = loadingNumbers + 1;
});
$(document).ajaxComplete(function(event, xhr, settings) {
    loadingNumbers = loadingNumbers - 1;
    if (loadingNumbers <= 0) {
        Common.unloading();
    }
});
var Common = {};
//充值
Common.resetForm = function(formEle){
	formEle.reset();
	$(formEle).find("input[type='hidden']").val("");
};
//填充下拉框
Common.fillSelect = function(selectEle, data) {
    $(selectEle).empty();
    var frag = document.createDocumentFragment();
    for (var prop in data) {
        var option = document.createElement("option");
       // option.value = prop;
        option.value = data[prop];
        option.text = data[prop];
        frag.appendChild(option);
    }
    selectEle.appendChild(frag);
};

/*加载框*/
Common.loading = function() {
    var background = $(".background");
    var loading = $("#loading");
    if (background.length > 0 & loading.length > 0) {
        $(".background,#loading").show()
    } else {
        $(".background,#loading", top.document).show();
    }
};
Common.unloading = function() {
    var background = $(".background");
    var loading = $("#loading");
    if (background.length > 0 & loading.length > 0) {
        $(".background,#loading").hide()
    } else {
        $(".background,#loading", top.document).hide();
    }
};
/*加载框*/

//获取url的中的参数
Common.urlParams = function(url) {
    if (typeof(url) == "undefined") {
        url = location.href;
    }
    var urlParams = new Object();
    if (url.lastIndexOf("?") > 0) {
        para = url.substring(url.lastIndexOf("?") + 1, url.length);
        var arr = para.split("&");
        var param = new Object();
        for (var i = 0; i < arr.length; i++) {
            urlParams[arr[i].split("=")[0]] = arr[i].split("=")[1];
        }

    }
    return urlParams;
};
//获取url的中的参数

Common.getClientHeight =function() {
    //可见高
    var clientHeight = document.documentElement.clientHeight; //其它浏览器默认值
    if (navigator.userAgent.indexOf("MSIE 6.0") != -1) {
        clientHeight = document.documentElement.clientHeight;
    }
    else if (navigator.userAgent.indexOf("MSIE") != -1) {
        //IE7 IE8
        clientHeight = document.documentElement.offsetHeight;
    }

    if (navigator.userAgent.indexOf("Chrome") != -1) {
        clientHeight = document.documentElement.clientHeight;
    }

    if (navigator.userAgent.indexOf("Firefox") != -1) {
        clientHeight = document.documentElement.clientHeight;
    }
    return clientHeight;
};

/*验证相关*/
function checkform(formEle){
	var requireles = $(formEle).find(".require");
	for(var i =0 ; i< requireles.length;++i){
		if(!requireCheck(requireles[i],requireles[i].value)){
			var label_text = $(requireles[i]).siblings("label").text();
			$(formEle).find("span.error").text(label_text+"不能为空");
			$(requireles[i]).addClass("error");
			return false;
		}
	}
	var doubleles = $(formEle).find(".double");
	for(var i =0 ; i< doubleles.length;++i){
		if(!doubleCheck(doubleles[i],doubleles[i].value)){
			var label_text = $(doubleles[i]).siblings("label").text();
			$(formEle).find(".error").text(label_text+"格式不正确，请重新输入！");
			$(doubleles[i]).addClass("error");
			return false;
		}
	}
	return true;
}
function doubleCheck(ele,value){
	var myRegExp = /^\d+\.?\d*$/;  //数字，且只出现0或1次小数点  问号?表示前面的只出现0或1次
       if (!myRegExp.test(value)) {
       		//alert("数据格式不正确，请重新输入!");
       		$(ele).focus();
          return false;
      } else {
           return true;
      }
}
function doubleCheck_Rewrite(ele,value){
	var myRegExp = /^\d+\.?\d*$/;  //数字，且只出现0或1次小数点  问号?表示前面的只出现0或1次
       if (!myRegExp.test(value)) {
       		value = value.replace(/[^\d.]/g,"");//[]中表示其中的字符只出现一次
       		//保证只有出现一个.而没有多个.    
            value = value.replace(/\.{2,}/g,".");    
            //保证.只出现一次，而不能出现两次以上    
            value = value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
       		ele.value = value;
      }
}
function requireCheck(ele,value){
	if(value == ""){
		//alert("数据不能为空!");
		$(ele).focus();
		return false;
	}
	return true;
}

function fillEdit(data) {
	var inputs = $(".form input[type!='button'],.form select");
	var length = inputs.length;
	for (var i = 0; i < length; ++i) {
    	var inputEle = inputs[i];
    	var name = inputEle.name;
    	var value = (data[name] != null) ? data[name] : "";
    	if (inputEle.type == "radio") {
        	value = (value == true) ? "1" : "0";
        	if (inputEle.value == value) {
            	inputEle.checked = true;
        	}
    	} else {
        	inputEle.value = value;
    	}
	}
}

$(document).ready(function(){
	$(".double").keyup(function(){
		doubleCheck_Rewrite(this,$(this).val());
	});
	//关闭编辑box按钮 -- 开始
    $(".close_m_box,#Cancel").click(function() {
        $(this).closest(".dialog").hide();
    });
    //关闭编辑box按钮 -- 结束
    
    //显示全部 -- 开始
    $("#showall").click(function(){
    	location = location.pathname;
    });
    //显示全部 -- 结束
    
    //表格样式
    $('.wijmo tbody').on("click", "tr", function() {
    	$(this).siblings().removeClass("selected");
    	$(this).addClass("selected");
    });
    //表格样式
    
    //关闭编辑box按钮 -- 开始
    $("#Cancel").click(function() {
        $(this).closest(".dialog").hide();
    });
    //关闭编辑box按钮 -- 结束
    
    $("input[tip],textarea[tip]").bind("focus click", function() {
        var tip = $(this).attr("tip");
        if (this.value == tip) {
            this.value = "";
            $(this).removeClass('tip');
        }
    });
    $("input[tip],textarea[tip]").bind("blur", function() {
        var tip = $(this).attr("tip");
        if (this.value == "") {
            this.value = tip;
            $(this).addClass('tip');
        }
    });
    
    //绑定时间插件
    $("input.wdatepicker").focus(function() {
        WdatePicker();
    });
    $("input.wdatepicker").click(function() {
        WdatePicker();
    });

});
/*验证相关*/

