/*start下一页开始的数字*/
var tableEle = $(".workspacetable")[0];
$(document).ready(function() {
    if (typeof(config) == "undefined") {
        config = new Object();
    }
    var tableOptions = config.tableOptions;
    if (typeof(tableOptions) != "undefined") {
    	TableTools.InitWijtable();
    	initButtons(config);
    	config.tableOptions.url = config.url;
        getTableData(0);

    }
});
//ifAfterQuery判断是否需要加载部门下的部门

function getTableData(start, ifAfterQuery) {
    config.tableOptions.donecall = function(data) {
        console.log(data.total);
        console.log(data.root);
        console.log(data.success);
        if (data.success) {
            TableTools.emptyTable(tableEle);
            if (ifAfterQuery != false) {
                triggerCallBack("afterQuery", null, config);
            }
            config.tableOptions.data = data.root;
            
            TableTools._fillTableData(tableEle, config.tableOptions, true);
        }
    };
    config.tableOptions.start = start;
    TableTools.loadData(tableEle, config.tableOptions);
}

//  按钮相关 
/*//根据built_in判断是否可以编辑, 返回true表示可修改该行
function checkEdit(built_inOption,row_data){
    if(row_data.built_in == false || typeof(row_data.built_in) == "undefined"){//若该行的bulit_in = false或者无built_in属性，则无需进行下面的判断
        return true;
    }
    if(built_inOption.edit == false){//若该行的bulit_in = true，则判断配置中的built_in.edit，为false表示不可以修改，其他可以修改
        alert(built_inOption.editmessage);
        return false;
    }
    return true;
}
//根据built_in判断是否可以删除, 返回true表示可删除该行
function checkDelete(built_inOption,row_data){
    if(row_data.built_in == false || typeof(row_data.built_in) == "undefined"){//若该行的bulit_in = false，则无需进行下面的判断
        return true;
    }
    if(built_inOption.delete == false){
    	//若该行的bulit_in = true，则判断配置中的built_in.edit，为false表示不可以修改，其他可以修改
        alert(built_inOption.deletemessage);
        return false;
    }
    return true;
}*/
function initButtons(config) {
    //刷新按钮 -- 开始
    $(".btn-left_refresh,.btn-left_refresh img").click(function(event) {
        triggerCallBack("beforeRefresh", null, config);
        self.location.reload();
        triggerCallBack("afterRefresh", null, config);
    });
    //刷新按钮 -- 结束

    var url = config.url;
    //新建按钮 -- 开始
    $(".btn-left_create").click(function() {
        triggerCallBack("beforeCreate", null, config);
        $("#box_div .form")[0].reset();
        $("#box_div").show();

        //新建Save的点击事件
        $("#box_div .work_head_cell span").text("新建");
        /*$("#Save").unbind("click");
        $("#Save").bind("click", function(event) {
            var formdata = JSON.stringify($(this).closest("form").serializeJson());

            $.ajax({
                url: url,
                type: 'POST',
                dataType: 'json',
                data: formdata,
            })
                .done(function(result) {
                    console.log("success");
                    //成功之后，表格中添加一行
                    TableTools.addRow(tableEle, config.tableOptions, result, true);
                    triggerCallBack("completeCreate", result, config);
                    //隐藏添加div或者继续添加
                    $("#box_div").hide();
                })
                .fail(function() {
                    console.log("error");
                    alert("创建失败！");
                })
                .always(function() {
                    console.log("complete");
                });

        });*/
        //新建Save的点击事件

        triggerCallBack("AfterCreate", null, config);

    });
    //新建按钮 -- 结束

    //编辑按钮 -- 开始
    $(".btn-left_edit").click(function() {
        triggerCallBack("beforeEdit", null, config);
        var table = $(this).closest('.Operatings').siblings('.wijmo-table-widget').children('.workspacetable');
        var selectedTr = table.find('tbody tr.selected');
        if (selectedTr.length == 0) {
            alert("请至少选中一行！");
            return;
        }
        var row_data_json = selectedTr.attr("data");
        var row_data = $.parseJSON(row_data_json);
       /* if(!checkEdit(config.tableOptions.built_in,row_data)){
            return;
        }*/
        
        $("#box_div").hide();
        $("#box_div .form")[0].reset();
        $("#box_div").show();


        triggerCallBack("fillEdit", row_data, config);

        //编辑Save的点击事件
        $("#box_div .work_head_cell span").text("编辑");
        /*$("#Save").unbind("click");
        $("#Save").bind("click", function(event) {
            var formdata = JSON.stringify($(this).closest("form").serializeJson());
            var Id = triggerCallBack("getId", selectedTr[0], config);

            $.ajax({
                url: url + "/" + Id,
                type: 'PUT',
                dataType: 'json',
                data: formdata,
            })
                .done(function(result) {
                    console.log("success");
                    //成功之后，表格中添加一行
                    TableTools.updateRow(config.tableOptions, result, selectedTr[0]);
                    triggerCallBack("completeEdit", result, config);
                    //隐藏添加div或者继续添加
                    $("#box_div").hide();
                })
                .fail(function() {
                    console.log("error");
                    alert("修改失败！");
                })
                .always(function() {
                    console.log("complete");
                });

        });*/
        //编辑Save的点击事件


        triggerCallBack("afterEdit", null, config);

    });
    //编辑按钮 -- 结束

    //删除按钮 -- 开始
    $(".btn-left_delete").click(function() {
        triggerCallBack("beforeDelete", null, config);
        var table = $(this).closest('.Operatings').siblings('.wijmo-table-widget').children('.workspacetable');
        var selectedTr = table.find('tbody tr.selected');

        if (selectedTr.length == 0) {
            alert("请至少选中一行！");
            return;
        }
        var row_data_json = selectedTr.attr("data");
        var row_data = $.parseJSON(row_data_json);
        /*if(!checkDelete(config.tableOptions.built_in,row_data)){
            return;
        }*/
        if (confirm("你确定要删除吗?") == false) {
            return;
        }

        var deleteId = triggerCallBack("getId", selectedTr[0], config);
        $.ajax({
            url: url + "/" + deleteId,
            type: 'DELETE',
            data: JSON.stringify({})
        })
            .done(function(result) {
                console.log("success");
                /*成功之后，表格中删除一行*/
                TableTools.deleteRow(selectedTr[0], true);
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

        triggerCallBack("afterDelete", null, config);
    });
    //删除按钮 -- 结束

    //关闭编辑box按钮 -- 开始
    $(".close_m_box").click(function() {
        $(this).closest(".dialog").hide();
    });
    //关闭编辑box按钮 -- 结束

    //上一页 -- 开始
    $(".btn-left_prev").click(function() {
        TableTools.prevPage(tableEle,config.tableOptions);
    });
    //上一页 -- 结束

    //下一页 -- 开始
    $(".btn-left_next").click(function() {
        TableTools.nextPage(tableEle,config.tableOptions);
    });
    //下一页 -- 结束

}
// 按钮相关 

//默认回调函数 -- 开始

function fillEdit_Init(data) {
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
    //当有department_id属性,且需要用到department_name时，从缓存中读出department_name
    var $department_name = $(".form input[name='department_name']");
    if ($department_name.length > 0) {
        var department_name = top.Cache.Get("department",data.department_id);
        $department_name.val(department_name);
    }
    //当有area_id属性,且需要用到area_name时，从缓存中读出area_name
    var $area_name = $(".form input[name='area_name']");
    if ($area_name.length > 0) {
        var area_name = top.Cache.Get("area",data.area_id);
        $area_name.val(area_name);
    }
    //当有product_type_id属性,且需要用到product_type_name时，从缓存中读出product_type_name
    var $product_type_name = $(".form input[name='product_type_name']");
    if ($product_type_name.length > 0) {
        var product_type_name = top.Cache.Get("product_type",data.product_type_id);
        $product_type_name.val(product_type_name);
    }

}

function getId_Init(selectedTr) {
    var rowdata_json = $(selectedTr).attr("data");
    var rowdata = $.parseJSON(rowdata_json);
    var id = rowdata.id;
    return id;
}

