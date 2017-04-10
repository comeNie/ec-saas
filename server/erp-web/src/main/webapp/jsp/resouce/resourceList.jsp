<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>顾氏erp系统</title>
	<%@ include file="../header.jsp" %>
	<script type="text/javascript" src="${ctx}/resources/js/resource/resourceList.js"></script>
</head>
<body>
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li>权限系统</li>
		<li>菜单管理</li>
	</ul>
</div>

<div style="height: 10px;"></div>

<div style="padding: 0px 20px 20px 20px;">
	<div class="righttext">
		<form action="${ctx}/erpresouce/getResourceList" method="post" id="getResourceListForm">
			<span>菜单名称：<input type="text" id="name" name="name" size="20" /></span>
			<span>菜单路径：<input type="text" id="path" name="path" size="30" /></span>
			<span><input style="margin-top: 0" type="button" value="查询" id="getResourceListSearch" name="button" class="cx_but" onclick="searchResourceList()" /></span>
			<span><input style="margin-top: 0" type="button" value="新建"  name="button" class="cx_but" onclick="window.location.href='${ctx}/erpresouce/add'" /></span>
		</form>
	</div>

	<div class="grid-fixed" id="resource_list"></div>
	<div class="pagin" id="resource_list_pager"></div>
</div>

</body>
</html>
