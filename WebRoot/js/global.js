
var Cache = new Object();
var Department = new Object();
var Area = new Object();
var product_type = new Object();

var TreeDialogTool = new Object();


$(document).ready(function() {
    /*Cache相关,Cache放在本地存储里，需要时从本地存储中实时存取，
不用document的局部变量存放Cache数据，是因为一旦刷新页面等局部变量清空，则缓存变量会恢复到最初过时的缓存
Cache放在本地存储时，不是整个放在Cache中，而是分开Object放*/
    /*Cache相关*/
    Cache.Init = function(data, callback) {
        if (data == null || typeof(data) == "undefined") {
            $.when($.ajax({
                url: '/api/cache',
                type: 'get',
                dataType: 'json'
            })).done(function(result) {
                data = result;
                for (var item in data) {
                    var item_data_json = JSON.stringify(data[item]);
                    setLocalStorage("c9_" + item, item_data_json);
                }
                callback();
            }).fail(function(result) {
                alert("获取缓存数据出错！");
            });
        } else {
            for (var item in data) {
                var item_data_json = JSON.stringify(data[item]);
                setLocalStorage("c9_" + item, item_data_json);
            }
        }

    };
    Cache.Get = function(ObjName, Param) {
        if (ObjName == null) {
            return null;
        }
        var value = getLocalStorage("c9_" + ObjName);
        if (typeof(value) != "undefined") {
            value = $.parseJSON(value);
        }
        if (Param == null) {
            return value;
        } else {
            return value[Param];
        }
    };
    Cache.Set = function(ObjName, Param, Value) {
        if (ObjName == null) {
            return null;
        }
        if (Param == null) {
            setLocalStorage(ObjName, Value);
        } else {
            var oldvalue = Cache.Get(ObjName);
            oldvalue[Param] = Value;
            setLocalStorage(ObjName, JSON.stringify(oldvalue));
        }
        // setLocalStorage("c9_cache", );
    };
    /*Cache相关*/

    /*全局对话框相关，包括部门、地区、商品类型 ； 商品、员工、商业伙伴*/
    //树对话框 -- 开始
    //部门树的更新不能跟cache中部门更新一样，部门树更新必须重新请求后台获取全部数据，因为sockjs只告诉部门更新的信息而不会告诉更新的上级节点

    TreeDialogTool.setting = {
        view: {
            showIcon: false,
            dblClickExpand: false
        },
        callback: {

        }
    };

    // function showIconForTree(treeId, treeNode) {
    //     return !treeNode.isParent;
    // }
    TreeDialogTool.getTreeData = function(url, callback) {
        $.ajax({
            url: url + "?action=tree",
            type: 'GET',
            dataType: 'json'
        })
            .done(function(result) {
                callback(result);
            })
            .fail(function() {
                console.log("error");
            })
            .always(function() {
                console.log("complete");
            });
    };
    TreeDialogTool.InitTreeDialog = function(Obj, url, treeid) {
        var treeidseletor = "#"+treeid;
        var dialog = $(treeidseletor).closest(".tree_search_dialog");
        Obj.isdatachange = true;
        Obj.openDialog = function(dblclick) {
            _DblClick = function(event, treeId, treeNode){
                dblclick(event, treeId, treeNode);
                Obj.closeDialog();
            };
            if (Obj.isdatachange) {
                Obj.getTreeData(
                    function(result) {
                        Obj.fillTree(result, _DblClick);
                        Obj.isdatachange = false;
                    }
                );
            }
            else{
                var treeObj = $.fn.zTree.getZTreeObj(treeid);
                treeObj.setting.callback.onDblClick = _DblClick;
            }
            dialog.show();
        };
        Obj.closeDialog = function() {
            dialog.hide();
        };
        Obj.getTreeData = function(callback) {
            TreeDialogTool.getTreeData(url, callback);
        };
        Obj.fillTree = function(nodes, dblclick) {
            var setting = TreeDialogTool.setting;
            setting.callback.onDblClick = dblclick;
            var tree = $.fn.zTree.init($(treeidseletor), setting, nodes);
            tree.expandAll(true);
        };
        dialog.find(".close_m_box").click(function() {
            dialog.hide();
        });
    };

    // 部门对话框

    TreeDialogTool.InitTreeDialog(Department, "/api/departments", "department_tree");
    // 地区对话框

    TreeDialogTool.InitTreeDialog(Area, "/api/areas", "area_tree");
    // 商品类型对话框

    TreeDialogTool.InitTreeDialog(product_type, "/api/product_types", "product_type_tree");
    //树对话框 -- 结束

    /*全局对话框相关，包括部门、地区、商品类型 ； 商品、员工、商业伙伴*/

});
