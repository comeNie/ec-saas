<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>顾氏erp系统</title>
	<%@ include file="../header.jsp" %>
	<script type="text/javascript" src="${ctx}/resources/js/resource/resourceAdd.js"></script>
</head>

<body>
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li>权限系统</li>
		<li>菜单管理</li>
	</ul>
</div>

<div class="rightinfo">
	<div class="tipinfo">
		<form id="resourceAdd_form" action="/erpresouce/update" method="post">
			<div class="tipinfo">
				<dl>
					<dt>菜单名称：</dt>
					<dd><input type="text" name="name" id="resource_name" value="${erpResouce.name}"></dd>
				</dl>
				<dl>
					<dt>菜单路径：</dt>
					<dd><input type="text" name="path" id="resource_path" value="${erpResouce.path}"></dd>
				</dl>
				<dl>
					<dt>菜单描述：</dt>
					<dd><input type="text" name="description" id="resource_description" value="${erpResouce.description}"></dd>
				</dl>
				<dl>
					<dt>菜单级别：</dt>
					<dd>
						<select name="depth" id="resource_depth">
							<option value="">请选择</option>
							<option value="1" <c:if test="${erpResouce.depth == 1}"> selected </c:if>>一级</option>
							<option value="2" <c:if test="${erpResouce.depth == 2}"> selected </c:if>>二级</option>
							<option value="3" <c:if test="${erpResouce.depth == 3}"> selected </c:if>>三级</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>菜单排序：</dt>
					<dd><input type="text" name="sort" id="resource_sort" value="${erpResouce.sort}"></dd>
				</dl>
				<dl>
					<dt>父菜单id：</dt>
					<dd><input type="text" name="pid" id="resource_pid" value="${erpResouce.pid}" onclick="initFatherResourceList('${erpResouce.pid}')" readonly></dd>
				</dl>
				<dl>
					<dt>权限id：</dt>
					<dd><input type="text" name="authority_id" id="resource_authority_id" value="${erpResouce.authority_id}" onclick="initAuthList('${erpResouce.authority_id}')" readonly></dd>
				</dl>
			</div>
			<input type="hidden" name="id" value="${erpResouce.id}"/>
		</form>
	</div>
	<div class="tipbtn">
		<input id="resource_submit" type="button" class="sure" value="确定" onclick="addResource()"/>&nbsp;
		<input id="authority_back" type="button" class="cancel" value="返回" onclick="window.location.href='${ctx}/erpresouce/list'"/>
	</div>
</div>

<!--弹窗 start-->
<div id="shdz" class="tip_a">
	<div class="tiptop"><a href="javascript:void(0)" onclick="$('#shdz').css('display', 'none');$('#fade').css('display', 'none')"></a></div>
	<div class="tip_text">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr id="popupTr">
			</tr>
		</table>
	</div>
	<div class="tip_title" style="text-align:center;">
		<input id="popupCon" type="button" class="sure sure_w" value="确定"/>&nbsp;
		<input onclick="$('#shdz').css('display', 'none');$('#fade').css('display', 'none')" type="button" class="cancel sure_w" value="取消"/>
	</div>
</div>
<div id="fade" class="black_overlay"></div>
<!--弹窗 end-->

</body>
</html>
