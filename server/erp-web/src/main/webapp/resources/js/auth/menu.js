/**
 * erp menu 动态生成函数
 * 在页面里使用方法如下：
 * window.onload = function(){
		var data='[{"name":"基础信息维护","children":[{"name":"机构信息","url":"right.html"},{"name":"医生合同管理","children":[{"name":"劳务合同管理","url":"right_tc.html"}]}]}]';
		erpMenu.refreshMenu("mbody","财务系统",data);
	}
	目前最多支持3级菜单
 */
var erpMenu = {
	container:null,
	title:"",
	/** sNodes结构如：[{name:'',value:'',url:'',children:[{name:'',value:'',url:'',children:[{},...]},...]},...] **/
	sNodes:null,
	init:function(pid,title,data){
		this.container = $("#"+pid);
		this.title = title;
		this.sNodes = $.parseJSON(data);
		this.container.html("");
	},
	createMenu:function(){
		this.container.append('<div class="lefttop"><span></span>'+ this.title +'</div>');
		var _nodes = this.sNodes;
		if($.isArray(_nodes)){
			var menu_html = '<dl class="leftmenu">';
			for(var i=0;i<_nodes.length;i++){
				var item = _nodes[i];
				menu_html += "<dd>";
				menu_html += '<div class="title"><span><img src="/resources/images/leftico01.png" /></span>'+ item.name +'</div>';
				menu_html += '<ul class="menuson">';
				if(item.children && item.children.length>0){
					menu_html += parseNode(item.children);
				}
				menu_html += '</ul>';
				menu_html += "</dd>";
			}
			menu_html += '</dl>';
			this.container.append(menu_html);
		}
		this.addClickEvent();
	},
	addClickEvent:function(){
		//导航切换
		$(".menuson .header").click(function(){
			var $parent = $(this).parent();
			$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
			$parent.addClass("active");
			if(!!$(this).next('.sub-menus').size()){
				if($parent.hasClass("open")){
					$parent.removeClass("open").find('.sub-menus').hide();
				}else{
					$parent.addClass("open").find('.sub-menus').show();	
				}
			}
		});
		// 三级菜单点击
		$('.sub-menus li').click(function(e) {
	        $(".sub-menus li.active").removeClass("active")
			$(this).addClass("active");
	    });
		$('.title').click(function(){
			var $ul = $(this).next('ul');
			$('dd').find('.menuson').slideUp();
			if($ul.is(':visible')){
				$(this).next('.menuson').slideUp();
			}else{
				$(this).next('.menuson').slideDown();
			}
		});
	},
	refreshMenu:function(pid,title,data){
		this.init(pid,title,data);
		this.createMenu();
	}
}
var parseNode = function(nodes){
	var menu_html = '';
	var _nodes = nodes;
	if(_nodes){
		for(var i=0;i<_nodes.length;i++){
			var item = _nodes[i];
			menu_html += '<li>';
			if(item.children && item.children.length>0){
				menu_html += '<div class="header"><cite></cite>'+item.name+'<i></i></div>';
				menu_html += '<ul class="sub-menus">';
				for(var j=0;j<item.children.length;j++){
					var c_item = item.children[j];
					menu_html += '<li><a href="'+c_item.url+'" target="rightFrame">'+c_item.name+'</a></li>';
				}
				menu_html += '</ul>';
			}else{
				menu_html += '<cite></cite><a href="'+ item.url +'" target="rightFrame">'+item.name+'</a><i></i>';
			}
			menu_html += '</li>';
		}
	}
	return menu_html;
}