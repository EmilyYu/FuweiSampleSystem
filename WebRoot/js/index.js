$(document).ready(function() {
	$("#left").height(document.body.clientHeight-$("#left").offset().top);
    /*左侧栏 样式相关js*/
	//设置左边栏的选中项
	var pathname = location.pathname;
	var pathname_lst = pathname.substring(pathname.lastIndexOf("/")+1);
	$(".menubar-ul li>a").each(function(){
		var href = $(this).attr("href");
		if(href==pathname_lst){
			$(this).parent().siblings().removeClass('active');
			$(this).parent().addClass("active");
			$(this).closest('.menubar-ul>li').siblings().removeClass('active');
			$(this).closest('.menubar-ul>li').addClass("active");
			$(this).closest('.menubar-ul>li').toggleClass("show");
		}
	});
	//设置左边栏的选中项
    $("#sidebar-collapse>i").click(function() {
    	var data_icon2 = $(this).attr("data-icon2");
    	$(this).toggleClass(data_icon2);
        $("#left").toggleClass('left_mini');
    });
    /*左侧栏 样式相关js*/
    /*左侧栏点击后功能相关js，比如打开工作台，打开新建单据等等*/
    $('.menubar-ul>li>a').click(function() {
        $(this).parent().toggleClass("show");
        return true;
    });
    $('.menubar-ul li>a').click(function() {
        $(this).closest('.menubar-ul>li').siblings().removeClass('active');
        $(this).closest('.menubar-ul>li').addClass('active');
        if($(this).attr("href") == "#"){
        	return false;
        }
        return true;
    });
    /*左侧栏点击后功能相关js，比如打开工作台，打开新建单据等等*/
    
    //下拉框
    $("a.dropdown-toggle").click(function(){
    	$(this).siblings("ul.dropdown").toggle();
    	return false;
    });
  //下拉框
});


/*函数区*/
function openSearchDialog(iframe_id,query){
    var $iframe = $("#"+iframe_id);
    var iframe_url = $iframe.attr("furl")+"?"+query;
    $iframe.attr("src",iframe_url);
    $iframe.closest(".dialog").show();
}
function closeSearchDialog(iframe_id){
    var $iframe = $("#"+iframe_id);
    $iframe.closest(".dialog").show();
}

function openBillDialog(src,billid, billname, bill_code) {
    $("#bill_dialog .title .name").text(billname);
    $("#bill_dialog .title .id").text(bill_code);
    $("#bill_dialog").attr("billid",billid);
    // $("#billiframe").attr("src", "bills/"+src);
    // $("#bill_dialog").show();

    $.get("bills/" + src, function(data) {
        BillsTool.id = billid;
        var bill_content = $("#bill_dialog .dialog_content");
        bill_content[0].innerHTML = data;
        $("#bill_dialog").show();
        var scripts = bill_content.find("script"); //由于使用div加载页面中的js代码无法二次执行，因此要提取出其中代码，手动执行
        for (var i = 0; i < scripts.length; ++i) {
            var script = document.createElement("script");
            script.text = scripts[i].text;
            $("#bill_dialog .dialog_content").after(script);
        }

        
    });

}

function closeBillDialog() {
    $("#bill_dialog").hide();
    $("#bill_dialog .title .name").text("[单据名称]");
    $("#bill_dialog .title .id").text("");
    $("#billiframe").removeAttr('src');
}

function reloadWorkspace() {
    $("#workspace").attr("src", "workspace.html");
}
/*函数区*/