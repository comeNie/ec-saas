<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎登录顾氏集团MIS系统</title>
	<%@ include file="../header.jsp" %>
	<script type="text/javascript">
		if (window != top) {
			top.location.href = location.href;
		}
	</script>
</head>

<body style="background-color:#1c77ac; background-image:url(${ctx}/resources/images/light.png); background-repeat:no-repeat; background-position:center top;">
<div id="mainBody">
	<div id="cloud1" class="cloud"></div>
	<div id="cloud2" class="cloud"></div>
</div>
<div class="logintop">
	<span>欢迎登录顾氏集团MIS系统</span>
</div>

<div class="loginbody">
	<span class="systemlogo"></span>

	<div class="loginbox">
		<form action="${ctx}/loginCheck" method="post" id="loginForm">
			<ul>
				<li><input id="username" name="username" type="text" class="loginuser"/></li>
				<li><input id="password" name="password" type="password" class="loginpwd"/></li>
				<div style="color: red">${error}</div>
				<li><input id="loginSubmitButton" type="button" class="loginbtn" value="登录"/></li>
			</ul>
		</form>
	</div>

</div>

<script>
	$(function () {
		$("#loginSubmitButton").click(function () {
			var username = $("#username").val().trim();
			var password = $("#password").val().trim();
			if (username == "") {
				alert("用户名不能为空");
				return;
			}
			if (password == "") {
				alert("密码不能为空");
				return;
			}
			$("#loginForm").submit();
		})
	});
</script>
</body>
</html>
