/**
 * Created by Mengzipeng on 2015/9/23.
 */

$(function () {
    $("#btn1").click(function () {
        var isChecked = $(this).prop("checked");
        $("input[name='subBox']").prop("checked", !isChecked);
    })
    $("#btn2").click(function () {
        var isChecked = $(this).prop("checked");
        $("input[name='subBox']").prop("checked", isChecked);
    })
    $("#btn5").click(function () {
        var str = "";
        $("[name='subBox'][checked]").each(function () {
            str += $(this).val() + ",";
            //alert($(this).val());
        });
        alert(str);
    })
});

function addUserRole() {
    var str = "";
    $("[name='subBox']").each(function () {
        if ($(this).prop("checked")) {
            str += "," + $(this).val();
        }
    });
    str = str.substr(1);
    var userId = $("#erpUserId").val();
    $.systemAjax({
        type: 'post',
        url: "/erpuser/updateUserRole",
        isAlert: true,
        data: {"roleStr": str, "userId": userId},
        success: function (data) {
            if (data == true) {
                $.systemAlert("绑定成功", function() {
                    window.location.href = ctx + "/erpuser/list";
                });
            } else {
                $.systemAlert("绑定失败");
            }
        }
    });
}
