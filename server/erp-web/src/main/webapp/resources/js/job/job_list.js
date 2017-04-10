$(function(){
	var cn = ['编号', '职位名称', '创建时间', '操作'];
	var cm = [{
	    name: 'code',
	    index: 'code',
	    width: 100,
	    hidden: false,
	    align: 'center'
	}, {
	    name: 'job_name',
	    index: 'job_name',
	    width: 200,
	    hidden: false,
	    align: 'center'
	}, {
	    name: 'createDateStr',
	    index: 'createDateStr',
	    width: 200,
	    hidden: false,
	    align: 'center'
	}, {
	    name: 'caozuo',
	    index: 'caozuo',
	    width: 200,
	    hidden: false,
	    align: 'center',
	    renderFun: function (obj, content) {
	        var str = "<a href=\""+ ctx +"/job/detail?jobId=" + obj.id + "\">编辑</a>&nbsp;&nbsp;" +
	        		  "<a href=\""+ ctx +"/job/delete?jobId=" + obj.id + "\" onclick=\"if(!doDelete("+ obj.id +")) return false;\">删除</a>"
	        return str;
	    }
	}];
	window.doDelete = function(id){
		var flag = false;
		$.ajax({
			type:"post",
			dataType:'json',
			async:false,
			data:{jobId:id},
		    url: "/job/countUsers",
		    success:function (result) {
		    	if(result && !isNaN(result)){
		    		flag = parseInt(result)>0;
		    	}
		    }
		});
		var hitTex = "您确认要删除此条记录吗？";
		if(flag){
			hitTex ="该职位下已存在用户，" + hitTex;
		}
		if(confirm(hitTex)){
			return true;
		}
		return false;
	}
	var grid = $("#grid").docgrid({
        url: "/job/query",
        colNames: cn,
        needCheckBox: false,
        colModel: cm,
//        initParams: $("#form").serializeJson(),
        rowNum: 10,// 每页显示行数量
        rowList: [ 10, 20, 30 ],// 每页显示数量变化select使用
        trId: "id",
        cListKey: "jsonList",
        pageId: "page",
        needPage: false
    });

});