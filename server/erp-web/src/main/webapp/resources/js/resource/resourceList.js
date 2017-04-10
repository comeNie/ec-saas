/**
 * Created by Mengzipeng on 2015/9/23.
 */

var cn = ['菜单编号', '菜单名称', '菜单路径', '菜单描述', '菜单级别', '排序', '操作'];

var cm = [{
    name: 'id',
    index: 'id',
    width: 50,
    hidden: false,
    align: 'center'
}, {
    name: 'name',
    index: 'name',
    width: 100,
    hidden: false,
    align: 'center'
}, {
    name: 'path',
    index: 'path',
    width: 500,
    hidden: false,
    align: 'left'
}, {
    name: 'description',
    index: 'description',
    width: 200,
    hidden: false,
    align: 'center'
}, {
    name: 'depth',
    index: 'depth',
    width: 50,
    hidden: false,
    align: 'center'
}, {
    name: 'sort',
    index: 'sort',
    width: 50,
    hidden: false,
    align: 'center'
}, {
    name: 'resource',
    index: 'resource',
    width: 100,
    hidden: false,
    align: 'center',
    renderFun: function (obj, content) {
        var str = "<a href=\""+ ctx +"/erpresouce/info?id=" + obj.id + "\">编辑</a>&nbsp;&nbsp;<a onclick=\"deleteResource(" + obj.id + ")\">删除</a>"
        return str;
    }
}];

var resourceGrid = null;

//初始化grid
function initResourceGrid() {
    resourceGrid = $("#resource_list").docgrid({
        url: "/erpresouce/getResourceList",// 请求url
        colNames: cn,
        needCheckBox: false,
        colModel: cm,
        initParams: $("#getResourceListForm").serializeJson(),
        rowNum: 10,// 每页显示行数量
        rowList: [ 10, 20, 30 ],// 每页显示数量变化select使用
        trId: "id",
        cListKey: "jsonList",
        pageId: "resource_list_pager",
        needPage: false
    });
}

$(function () {
    initResourceGrid();
})

function searchResourceList() {
    var submitParam = $("#getResourceListForm").serializeJson();
    resourceGrid.reload(submitParam);
}

function deleteResource(id) {
    $.systemConfirm("确定要删除该菜单吗？", function () {
        $.systemAjax({
            type: 'post',
            url: "/erpresouce/delete",
            cache: false,
            dataType: 'json',
            data: { "id": id },
            success: function (data) {
                if (data == true) {
                    $.systemAlert("删除成功", function() {
                        searchResourceList();
                    });
                } else {
                    $.systemAlert("删除失败");
                }
            }
        });
    }, function () {

    })
}