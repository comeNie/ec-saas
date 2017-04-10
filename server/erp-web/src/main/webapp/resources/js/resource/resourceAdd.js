/**
 * Created by Mengzipeng on 2015/9/23.
 */

var checkData = [
    {
        name: "resource_name",
        nullCheck: true,
        nullCheckMessage: "菜单名称不能为空！"

    },
    {
        name: "resource_path",
        nullCheck: true,
        nullCheckMessage: "菜单路径不能为空！"
    },
    {
        name: "resource_description",
        nullCheck: true,
        nullCheckMessage: "菜单描述不能为空！"
    },
    {
        name: "resource_depth",
        nullCheck: true,
        nullCheckMessage: "菜单级别不能为空！",
        checkFormat : {
            checkType : "regex",
            regex_message : {
                formatBase : "intege",
                message : "菜单级别格式不正确！"
            }
        }
    },
    {
        name: "resource_sort",
        nullCheck: true,
        nullCheckMessage: "菜单排序不能为空！",
        checkFormat : {
            checkType : "regex",
            regex_message : {
                formatBase : "intege",
                message : "菜单排序格式不正确！"
            }
        }
    },
    {
        name: "resource_pid",
        nullCheck: true,
        nullCheckMessage: "父菜单id不能为空！",
        checkFormat : {
            checkType : "regex",
            regex_message : {
                formatBase : "intege",
                message : "父菜单id格式不正确！"
            }
        }
    },
    {
        name: "resource_authority_id",
        nullCheck: true,
        nullCheckMessage: "权限id不能为空！",
        checkFormat : {
            checkType : "regex",
            regex_message : {
                formatBase : "intege",
                message : "权限id格式不正确！"
            }
        }
    }
];

//初始化父菜单集合弹出框
function initFatherResourceList(fid) {
    $.systemAjax({
        type: 'post',
        url: "/erpresouce/initFatherResourceList",
        cache: false,
        dataType: 'json',
        success: function (data) {
            if(data != null) {
                var tdStr = "<td align=\"left\" valign=\"top\"><input type=\"radio\" name=\"radio\" value=\"0\"/>无</td>";
                $.each(data, function(i, item) {
                    if(fid != null && fid != "" && fid == item.id) {
                        var td = '<td align="left" valign="top"><input type="radio" name="radio" value="' + item.id + '" checked/> ' + item.name + '</td>';
                    } else {
                        var td = '<td align="left" valign="top"><input type="radio" name="radio" value="' + item.id + '"/> ' + item.name + '</td>';
                    }
                    tdStr += td;
                });
                $("#popupTr").html("");
                $("#popupTr").html(tdStr);
                $("#popupCon").off();
                $("#popupCon").click(function() {
                    $("#resource_pid").val($('input:radio:checked').val());
                    $('#shdz').css('display', 'none');$('#fade').css('display', 'none');
                });
                $('#shdz').css('display', 'block');$('#fade').css('display', 'block')
            }
        }
    });
}

//初始化权限集合弹出框
function initAuthList(authId) {
    $.systemAjax({
        type: 'post',
        url: "/erpresouce/initAuthList",
        cache: false,
        dataType: 'json',
        success: function (data) {
            if(data != null) {
                var tdStr = "";
                $.each(data, function(i, item) {
                    if(authId != null && authId != "" && authId == item.id) {
                        var td = '<td align="left" valign="top"><input type="radio" name="radio" value="' + item.id + '" checked/> ' + item.description + '</td>';
                    } else {
                        var td = '<td align="left" valign="top"><input type="radio" name="radio" value="' + item.id + '"/> ' + item.description + '</td>';
                    }
                    tdStr += td;
                });
                $("#popupTr").html("");
                $("#popupTr").html(tdStr);
                $("#popupCon").off();
                $("#popupCon").click(function() {
                    $("#resource_authority_id").val($('input:radio:checked').val());
                    $('#shdz').css('display', 'none');$('#fade').css('display', 'none');
                });
                $('#shdz').css('display', 'block');$('#fade').css('display', 'block')
            }
        }
    });
}

function addResource() {
    if ($.checkSubmit(checkData)) {
        $.systemAjax({
            type: 'post',
            url: "/erpresouce/update",
            cache: false,
            dataType: 'json',
            data: $("#resourceAdd_form").serializeJson(),
            success: function (data) {
                if (data == true) {
                    $.systemAlert("操作成功", function() {
                        window.location.href = ctx + "/erpresouce/list";
                    });
                } else {
                    $.systemAlert("操作失败");
                }
            }
        });
    }
}