<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>顾氏erp系统</title>
	<%@ include file="../header.jsp" %>
	<script type="text/javascript" src="${ctx}/resources/js/job/job_list.js"></script>
</head>
<body>
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li>权限系统</li>
		<li>职位管理</li>
	</ul>
</div>
<div style="height: 10px;"></div>
<div style="padding: 0px 20px 20px 20px;">
	<div class="tools">
		<ul class="toolbar">
			<li class="click">
				<span><img src="../../resources/images/t01.png"/></span>
				<a href="${ctx}/job/add">新建</a>
			</li>
		</ul>
	</div>
	<div class="grid-fixed" id="grid"></div>
	<div class="pagin" id="page"></div>
</div>

</body>
</html>
