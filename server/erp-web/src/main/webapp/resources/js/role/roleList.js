/**
 * Created by Mengzipeng on 2015/9/23.
 */

var cn = ['角色编号', '角色名称', '角色描述', '角色状态', '操作'];

var cm = [{
    name: 'id',
    index: 'id',
    width: 100,
    hidden: false,
    align: 'center'
}, {
    name: 'role_name',
    index: 'role_name',
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
    name: 'statusName',
    index: 'statusName',
    width: 200,
    hidden: false,
    align: 'center'
}, {
    name: 'role',
    index: 'role',
    width: 200,
    hidden: false,
    align: 'center',
    renderFun: function (obj, content) {
        var str = "<a href=\""+ ctx +"/erprole/info?id=" + obj.id + "\">编辑</a>&nbsp;&nbsp;<a onclick=\"deleteRole(" + obj.id + ")\">删除</a>&nbsp;&nbsp;<a href=\""+ ctx +"/erprole/roleAuthList?id=" + obj.id + "\">绑定权限</a>"
        return str;
    }
}];

var roleGrid = null;

//初始化grid
function initRoleGrid() {
    roleGrid = $("#role_list").docgrid({
        url: "/erprole/getRoleList",// 请求url
        colNames: cn,
        needCheckBox: false,
        colModel: cm,
        initParams: $("#getRoleListForm").serializeJson(),
        rowNum: 10,// 每页显示行数量
        rowList: [ 10, 20, 30 ],// 每页显示数量变化select使用
        trId: "id",
        cListKey: "jsonList",
        pageId: "role_list_pager",
        needPage: false
    });
}

$(function () {
    initRoleGrid();
})

function searchRoleList() {
    var submitParam = $("#getRoleListForm").serializeJson();
    roleGrid.reload(submitParam);
}

function deleteRole(id) {
    $.systemConfirm("确定要删除该角色吗？", function () {
        $.systemAjax({
            type: 'post',
            url: "/erprole/delete",
            cache: false,
            dataType: 'json',
            data: { "id": id },
            success: function (data) {
                if (data == true) {
                    $.systemAlert("删除成功", function() {
                        searchRoleList();
                    });
                } else {
                    $.systemAlert("删除失败");
                }
            }
        });
    }, function () {

    })
}