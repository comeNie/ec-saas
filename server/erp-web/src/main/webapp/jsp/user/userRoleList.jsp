<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>顾氏erp系统</title>
	<%@ include file="../header.jsp" %>
	<script type="text/javascript" src="${ctx}/resources/js/user/userRole.js"></script>
</head>

<body>
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li>权限系统</li>
		<li>用户管理</li>
	</ul>
</div>
<br/>
<input type="button" id="btn1" value="全选">
<input type="button" id="btn2" value="取消全选"><br/><br/>

<div class="rightinfo">
	<div class="tipinfo">
		<ul>
			<c:forEach items="${roleList}" var="role">
				<li><input name="subBox" type="checkbox" value="${role.id}"
				       <c:if test="${role.bindFlag == 1}">checked="checked" </c:if> >${role.description}</li>
			</c:forEach>
		</ul>
	</div>
</div>

<input type="hidden" id="erpUserId" value="${erpUser.id}"/>

<div class="tipbtn btn_mr">
	<input type="button" class="sure" value="确定" onclick="addUserRole()"/>&nbsp;
	<input type="button" class="cancel" value="返回" onclick="window.location.href='${ctx}/erpuser/list'" />
</div>
<br/>

</body>
</html>
