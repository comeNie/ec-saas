<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>顾氏erp系统</title>
	<%@ include file="../header.jsp" %>
	<script type="text/javascript" src="${ctx}/resources/plugin/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctx}/resources/plugin/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/role/roleAuth.js"></script>
	<link rel="stylesheet" href="${ctx}/resources/plugin/ztree/css/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/resources/plugin/ztree/css/mineTree.css" type="text/css">

	<script type="text/javascript">
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		$(document).ready(function(){
			var relationVoList = '${relationVoList}';
			if(relationVoList && relationVoList != "") {
				$.fn.zTree.init($("#authRelationList"), setting, JSON.parse(relationVoList));
			}
		})
	</script>
</head>

<body>
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li>权限系统</li>
		<li>角色管理</li>
	</ul>
</div>

<ul id="authRelationList" class="ztree"></ul>

<%--<br/>
<input type="button" id="btn1" value="全选">
<input type="button" id="btn2" value="取消全选"><br/><br/>

<div class="rightinfo">
	<div class="tipinfo">
		<ul>
			<c:forEach items="${authList}" var="au">
				<li><input name="subBox" type="checkbox" value="${au.id}"
				           <c:if test="${au.bindFlag == 1}">checked="checked" </c:if> >${au.description}</li>
			</c:forEach>
		</ul>
	</div>
</div>--%>

<input type="hidden" id="erpRoleId" value="${erpRole.id}"/>

<div class="tipbtn btn_mr">
	<input id="role_auth_submit" type="button" class="sure" value="确定" onclick="addRoleAuth()"/>&nbsp;
	<input id="role_auth_back" type="button" class="cancel" value="返回" onclick="window.location.href='${ctx}/erprole/list'"/>
</div>
<br/>
</body>
</html>
