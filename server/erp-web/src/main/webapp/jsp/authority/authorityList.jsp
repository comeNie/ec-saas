<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>顾氏erp系统</title>
	<%@ include file="../header.jsp" %>
	<script type="text/javascript" src="${ctx}/resources/js/authority/authorityList.js"></script>
</head>
<body>
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li>权限系统</li>
		<li>权限管理</li>
	</ul>
</div>

<div style="height: 10px;"></div>

<div style="padding: 0px 20px 20px 20px;">
	<div class="righttext">
		<form action="${ctx}/erpauthority/getAuthorityList" method="post" id="getAuthorityListForm">
			<span>权限码：<input type="text" id="code" name="code" size="20" /></span>
			<span><input style="margin-top: 0" type="button" value="查询" id="getAuthorityListSearch" name="button" class="cx_but" onclick="searchAuthorityList()" /></span>
			<span><input style="margin-top: 0" type="button" value="新建"  name="button" class="cx_but" onclick="window.location.href='${ctx}/erpauthority/add'" /></span>
		</form>
	</div>

	<div class="grid-fixed" id="authority_list"></div>
	<div class="pagin" id="authority_list_pager"></div>
</div>

</body>
</html>
