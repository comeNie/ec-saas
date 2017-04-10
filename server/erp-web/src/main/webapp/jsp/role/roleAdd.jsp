<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>顾氏erp系统</title>
	<%@ include file="../header.jsp" %>
	<script type="text/javascript" src="${ctx}/resources/js/role/roleAdd.js"></script>
</head>

<body>
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li>权限系统</li>
		<li>角色管理</li>
	</ul>
</div>

<div class="rightinfo">
	<div class="tipinfo">

		<form id="roleAdd_form" action="/erprole/update" method="post">
			<div class="tipinfo">
				<dl>
					<dt>角色名称：</dt>
					<dd><input type="text" name="role_name" id="role_name" value="${erpRole.role_name}"></dd>
				</dl>
				<dl>
					<dt>角色描述：</dt>
					<dd><input type="text" name="description" id="role_description" value="${erpRole.description}"></dd>
				</dl>
				<dl>
					<dt>角色状态：</dt>
					<dd>
						<select id="role_status" name="status">
							<option value="1" <c:if test="${erpRole.status == 1}"> selected </c:if>>有效</option>
							<option value="0">无效</option>
						</select>
					</dd>
				</dl>
			</div>
			<input type="hidden" name="id" value="${erpRole.id}"/>

			<div class="tipbtn">
				<input id="role_submit" type="button" class="sure" value="确定" onclick="addRole()"/>&nbsp;
				<input id="role_back" type="button" class="cancel" value="返回" onclick="window.location.href='${ctx}/erprole/list'"/>
			</div>
		</form>
	</div>
</div>

</body>
</html>
