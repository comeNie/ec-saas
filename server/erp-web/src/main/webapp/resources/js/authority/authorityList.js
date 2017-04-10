/**
 * Created by Mengzipeng on 2015/9/23.
 */

var cn = ['权限编号', '权限码', '权限描述', '操作'];

var cm = [{
    name: 'id',
    index: 'id',
    width: 200,
    hidden: false,
    align: 'center'
}, {
    name: 'code',
    index: 'code',
    width: 200,
    hidden: false,
    align: 'center'
}, {
    name: 'description',
    index: 'description',
    width: 200,
    hidden: false,
    align: 'center'
}, {
    name: 'authority',
    index: 'authority',
    width: 200,
    hidden: false,
    align: 'center',
    renderFun: function (obj, content) {
        var str = "<a href=\""+ ctx +"/erpauthority/info?id=" + obj.id + "\">编辑</a>&nbsp;&nbsp;<a onclick=\"deleteAuthority(" + obj.id + ")\">删除</a>"
        return str;
    }
}];

var authorityGrid = null;

//初始化grid
function initAuthorityGrid() {
    authorityGrid = $("#authority_list").docgrid({
        url: "/erpauthority/getAuthorityList",// 请求url
        colNames: cn,
        needCheckBox: false,
        colModel: cm,
        initParams: $("#getAuthorityListForm").serializeJson(),
        rowNum: 10,// 每页显示行数量
        rowList: [ 10, 20, 30 ],// 每页显示数量变化select使用
        trId: "id",
        cListKey: "jsonList",
        pageId: "authority_list_pager",
        needPage: false
    });
}

$(function () {
    initAuthorityGrid();
})

function searchAuthorityList() {
    var submitParam = $("#getAuthorityListForm").serializeJson();
    authorityGrid.reload(submitParam);
}

function deleteAuthority(id) {
    $.systemConfirm("确定要删除该权限吗？", function () {
        $.systemAjax({
            type: 'post',
            url: "/erpauthority/delete",
            cache: false,
            dataType: 'json',
            data: { "id": id },
            success: function (data) {
                if (data == true) {
                    $.systemAlert("删除成功", function() {
                        searchAuthorityList();
                    });
                } else {
                    $.systemAlert("删除失败");
                }
            }
        });
    }, function () {

    })
}