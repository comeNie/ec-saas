<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>顾氏erp系统</title>
	<%@ include file="../header.jsp" %>
	<script type="text/javascript" src="${ctx}/resources/js/user/user_add.js"></script>
</head>

<body>
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li>权限系统</li>
		<li>用户管理</li>
	</ul>
</div>

<div class="rightinfo">
	<div class="tipinfo">

		<form id="userAdd_form" action="/erpuser/update" method="post">
			<div class="tipinfo">
				<dl>
					<dt>用户名称：</dt>
					<dd><input type="text" name="login_name" id="login_name" value="${erpUser.login_name}"></dd>
				</dl>
				<dl>
					<dt>系统id：</dt>
					<dd><input type="text" name="system_id" id="system_id" value="${erpUser.system_id}"></dd>
				</dl>
				<dl>
					<dt>是否可用：</dt>
					<dd>
						<select id="isAlive" name="isAlive">
							<option value="1" <c:if test="${erpUser.isAlive == 1}"> selected </c:if>>有效</option>
							<option value="0">无效</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>部门：</dt>
					<dd>
						<input type="hidden" id="hid_dept_id" value="${erpUser.dept_id}">
						<select id="dept_id" name="dept_id">
							<option value="">--请选择--</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>职位：</dt>
					<dd>
						<input type="hidden" id="hid_job_id" value="${erpUser.job_id}">
						<select id="job_id" name="job_id">
							<option value="">--请选择--</option>
						</select>
					</dd>
				</dl>
			</div>
			<input type="hidden" name="id" value="${erpUser.id}"/>

			<div class="tipbtn">
				<input id="user_submit" type="button" class="sure" value="确定" onclick="addUser()"/>&nbsp;
				<input id="user_back" type="button" class="cancel" value="返回" onclick="window.location.href='${ctx}/erpuser/list'"/>
			</div>
		</form>
	</div>
</div>

</body>
</html>
