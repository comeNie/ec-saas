/**
 * Created by Mengzipeng on 2015/9/23.
 */

var checkData = [
    {
        name: "authority_code",
        nullCheck: true,
        nullCheckMessage: "权限码不能为空！"
    },
    {
        name: "authority_description",
        nullCheck: true,
        nullCheckMessage: "权限描述不能为空！"
    }
];

function addAuthority() {
    if ($.checkSubmit(checkData)) {
        $.systemAjax({
            type: 'post',
            url: "/erpauthority/update",
            cache: false,
            dataType: 'json',
            data: $("#authorityAdd_form").serializeJson(),
            success: function (data) {
                if (data == true) {
                    $.systemAlert("操作成功", function() {
                        window.location.href = ctx + "/erpauthority/list";
                    });
                } else {
                    $.systemAlert("操作失败");
                }
            }
        });
    }
}