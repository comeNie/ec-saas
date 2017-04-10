<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>顾氏erp系统</title>
	<%@ include file="../header.jsp" %>
	<script type="text/javascript" src="${ctx}/resources/js/authority/authorityAdd.js"></script>
</head>

<body>
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li>权限系统</li>
		<li>权限管理</li>
	</ul>
</div>

<div class="rightinfo">
	<div class="tipinfo">
		<form id="authorityAdd_form" action="/erpauthority/update" method="post">
			<div class="tipinfo">
				<dl>
					<dt>权限码：</dt>
					<dd><input type="text" name="code" id="authority_code" value="${erpAuthority.code}" /></dd>
				</dl>
				<dl>
					<dt>权限描述：</dt>
					<dd><input type="text" name="description" id="authority_description" value="${erpAuthority.description}" /></dd>
				</dl>
			</div>
			<input type="hidden" name="id" value="${erpAuthority.id}"/>

			<div class="tipbtn">
				<input id="authority_submit" type="button" class="sure" value="确定" onclick="addAuthority()"/>&nbsp;
				<input id="authority_back" type="button" class="cancel" value="返回" onclick="window.location.href='${ctx}/erpauthority/list'"/>
			</div>
		</form>
	</div>
</div>

</body>
</html>
