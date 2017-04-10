<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@ include file="light_header.jsp" %>
	<script type="text/javascript">
		$(function () {
			//顶部导航切换
			$(".nav li a").click(function () {
				$(".nav li a.selected").removeClass("selected")
				$(this).addClass("selected");
			});
		})
	</script>
</head>

<body style="background:url(${ctx}/resources/images/topbg.gif) repeat-x;">

<div class="topleft">
	<a href="${ctx}/index" target="_parent"><img src="${ctx}/resources/images/logo.png" title="系统首页"/></a>
</div>

<ul class="nav">
	<c:choose>
		<c:when test="${tree != null}">
			<c:forEach var="res" items="${tree}" varStatus="status">
				<li>
					<a href="${ctx}/frame/left?pid=${res.id}&name=${res.name}" target="leftFrame"><img src="${ctx}/resources/images/icon0${status.index+2}.png" title="${res.name}"/>
						<h2>${res.name}</h2>
					</a>
				</li>
			</c:forEach>
		</c:when>
		<c:otherwise>
			您没有相关权限，请联系管理员开通
		</c:otherwise>
	</c:choose>
</ul>
<c:set var="loginUser" value="<%=cn.gusmedsci.erp.auth.integration.UserContext.getLoginName()%>" scope="request"/>
<div class="topright">
	<ul>
		<li><a href="${ctx}/logout" target="_parent">退出</a></li>
	</ul>

	<div class="user">
		<span>${loginUser}</span>
	</div>

</div>

</body>
</html>
