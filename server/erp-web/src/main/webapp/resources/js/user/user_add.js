var requestDeptList = function(select,selectedValue){
	$.ajax({
	type:"get",
	dataType:'json',
    url: "/dept/requestDepts",
    success:function (result) {
    	if (result == null || result == '' || result.length == 0) {
	    	alert("请求数据失败");
        }else{
        	for(var i=0;i<result.length;i++){
        		$(select).append('<option value="' + result[i].id + '">' + result[i].dept_name + '</option>');
    		}
        }
    	if(selectedValue)
    		select.val(selectedValue);
    }
	});
}

var requestJobList = function(select,selectedValue){
	$.ajax({
	type:"get",
	dataType:'json',
    url: "/job/requestJobs",
    success:function (result) {
    	if (result == null || result == '' || result.length == 0) {
	    	alert("请求数据失败");
        }else{
        	for(var i=0;i<result.length;i++){
        		$(select).append('<option value="' + result[i].id + '">' + result[i].job_name + '</option>');
    		}
        }
    	if(selectedValue)
    		select.val(selectedValue);
    }
	});
}
$(function(){
	requestDeptList($("#dept_id"),$("#hid_dept_id").val());
	requestJobList($("#job_id"),$("#hid_job_id").val());
});

var checkData = [
	{
		name: "login_name",
		nullCheck: true,
		nullCheckMessage: "用户名称不能为空！"
	}, {
		name: "system_id",
		nullCheck: true,
		nullCheckMessage: "系统id不能为空！",
		checkFormat : {
			checkType : "regex",
			regex_message : {
				formatBase : "intege",
				message : "系统id格式不正确！"
			}
		}
	}
];

function addUser() {
	if ($.checkSubmit(checkData)) {
		$.systemAjax({
			type: 'post',
			url: "/erpuser/update",
			cache: false,
			dataType: 'json',
			data: $("#userAdd_form").serializeJson(),
			success: function (data) {
				if (data == true) {
					$.systemAlert("操作成功", function() {
						window.location.href = ctx + "/erpuser/list";
					});
				} else {
					$.systemAlert("操作失败");
				}
			}
		});
	}
}