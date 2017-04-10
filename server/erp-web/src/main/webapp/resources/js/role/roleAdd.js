/**
 * Created by Mengzipeng on 2015/9/23.
 */

var checkData = [
    {
        name: "role_name",
        nullCheck: true,
        nullCheckMessage: "角色名称不能为空！"

    },
    {
        name: "role_description",
        nullCheck: true,
        nullCheckMessage: "角色描述不能为空！"
    }
];

function addRole() {
    if ($.checkSubmit(checkData)) {
        $.systemAjax({
            type: 'post',
            url: "/erprole/update",
            cache: false,
            dataType: 'json',
            data: $("#roleAdd_form").serializeJson(),
            success: function (data) {
                if (data == true) {
                    $.systemAlert("操作成功", function() {
                        window.location.href = ctx + "/erprole/list";
                    });
                } else {
                    $.systemAlert("操作失败");
                }
            }
        });
    }
}