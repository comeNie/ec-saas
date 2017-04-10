/**
 * Created by Mengzipeng on 2015/9/23.
 */

var cn = ['用户编号', '用户名', '系统编号', '用户状态', '操作'];

var cm = [{
    name: 'id',
    index: 'id',
    width: 100,
    hidden: false,
    align: 'center'
}, {
    name: 'login_name',
    index: 'login_name',
    width: 200,
    hidden: false,
    align: 'center'
}, {
    name: 'system_id',
    index: 'system_id',
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
    name: 'user',
    index: 'user',
    width: 200,
    hidden: false,
    align: 'center',
    renderFun: function (obj, content) {
        var str = "<a href=\""+ ctx +"/erpuser/info?id=" + obj.id + "\">编辑</a>&nbsp;&nbsp;<a href=\""+ ctx +"/erpuser/userRoleList?id=" + obj.id + "\">绑定角色</a>"
        return str;
    }
}];

var userGrid = null;

//初始化grid
function initUserGrid() {
    userGrid = $("#user_list").docgrid({
        url: "/erpuser/getUserList",// 请求url
        colNames: cn,
        needCheckBox: false,
        colModel: cm,
        initParams: $("#getUserListForm").serializeJson(),
        rowNum: 10,// 每页显示行数量
        rowList: [ 10, 20, 30 ],// 每页显示数量变化select使用
        trId: "id",
        cListKey: "jsonList",
        pageId: "user_list_pager",
        needPage: false
    });
}

$(function () {
    initUserGrid();
})

function searchUserList() {
    var submitParam = $("#getUserListForm").serializeJson();
    userGrid.reload(submitParam);
}
