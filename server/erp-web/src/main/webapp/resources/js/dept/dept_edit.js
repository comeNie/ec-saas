$(function(){
	$.validator.addMethod("validateCode",function(value,element){
			var _code = $("#code").val();
			if(_code && _code!=null && _code!=""){
				var flag = true;
				$.ajax({
					type:"post",
					dataType:'json',
					async:false,
					data:{code:_code,id:$("#dept_id").val()},
				    url: "/dept/checkIsRepeat",
				    success:function (result) {
				    	if(result && result==true){
				    		flag = false;
				    	}else
				    		flag = true;
				    }
				});
				return flag;
			}else
				return false;
		},"部门编号已存在！");
	$("#form").validate({
		rules: {
			code:{
				required:true,
				validateCode:true
			},
			dept_name: "required"
		},
		messages: {
			code:{
				required:"部门编号不能为空！"
			},
			dept_name:"部门名称不能为空！"
		}
	});
	$("#sub_but").click(function(){
		if(!$("#form").valid()){
		    return; 
		}
		if(document.getElementById("description").value.length>200){
			alert("备注不得多于200个字符。");
			return;
		}
		$("#form").attr("action","/dept/saveOrUpdate");
		$("#form").submit();
	});
});