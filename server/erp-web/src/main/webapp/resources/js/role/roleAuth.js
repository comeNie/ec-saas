/**
 * Created by Mengzipeng on 2015/9/23.
 */

function addRoleAuth() {
    var str = "";

    var treeObj = $.fn.zTree.getZTreeObj("authRelationList"),
        nodes = treeObj.getCheckedNodes(true);
    for (var i = 0; i < nodes.length; i++) {
        str += "," + nodes[i].id;//获取选中节点的值
    }
    str = str.substr(1);
    var roleId = $("#erpRoleId").val();
    $.systemAjax({
        type: 'post',
        url: "/erprole/updateRoleAuth",
        isAlert: true,
        data: {"authStr": str, "roleId": roleId},
        success: function (data) {
            if (data == true) {
                $.systemAlert("绑定成功", function () {
                    window.location.href = "/erprole/list";
                });
            } else {
                $.systemAlert("绑定失败");
            }

        }
    });
}
