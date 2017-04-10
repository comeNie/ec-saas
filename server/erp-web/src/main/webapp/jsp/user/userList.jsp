<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>顾氏erp系统</title>
	<%@ include file="../header.jsp" %>
	<script type="text/javascript" src="${ctx}/resources/js/user/userList.js"></script>
</head>
<body>
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li>权限系统</li>
		<li>用户管理</li>
	</ul>
</div>

<div style="height: 10px;"></div>

<div style="padding: 0px 20px 20px 20px;">
	<div class="righttext">
		<form action="${ctx}/erpuser/getUserList" method="post" id="getUserListForm">
			<span>用户名：<input type="text" id="login_name" name="login_name" size="20" /></span>
			<span>用户状态：
				<select id="user_status" name="isAlive">
					<option value="">全部</option>
					<option value="0">无效</option>
					<option value="1" selected>有效</option>
				</select>
			</span>
			<span><input style="margin-top: 0" type="button" value="查询" id="getUserListSearch" name="button" class="cx_but" onclick="searchUserList()" /></span>
			<span><input style="margin-top: 0" type="button" value="新建"  name="button" class="cx_but" onclick="window.location.href='${ctx}/erpuser/add'" /></span>
		</form>
	</div>

	<div class="grid-fixed" id="user_list"></div>
	<div class="pagin" id="user_list_pager"></div>
</div>

</body>
</html>
