<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugin/grid/grid.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugin/blockui/loading.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugin/formCheck/css/fcheck.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugin/blockui/blockui.css">

<script type = "text/javascript" src="${ctx}/resources/plugin/jquery-2.1.4.min.js"></script>
<script type = "text/javascript" src="${ctx}/resources/plugin/common.method.js"></script>
<script type = "text/javascript" src="${ctx}/resources/plugin/ajax.util.message.js"></script>
<script type = "text/javascript" src="${ctx}/resources/plugin/system.ajax.src.js"></script>
<script type = "text/javascript" src="${ctx}/resources/plugin/formCheck/form.check.src.js"></script>
<script type = "text/javascript" src="${ctx}/resources/plugin/blockui/jquery.blockUI.js"></script>
<script type = "text/javascript" src="${ctx}/resources/plugin/grid/doc.grid.src.js"></script>
<script type = "text/javascript" src="${ctx}/resources/plugin/grid/jquery.pagination.js"></script>
<script type = "text/javascript" src="${ctx}/resources/plugin/jquery.qrcode.min.js"></script>


<script type="text/javascript">
	var ctx = "${ctx}";
	var local_url = "${ctx}";

	function isNull(val) {
		if (val == "" || val == "undefined" || val == undefined || val == null
				|| val == "null") {
			return true;
		}
		return false;
	}
</script>

<div id="initLoadingHtml">
	<svg viewBox="0 0 120 120" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
		<g id="circle" class="g-circles g-circles--v1">
			<circle id="12" transform="translate(35, 16.698730) rotate(-30) translate(-35, -16.698730) " cx="35" cy="16.6987298" r="10"></circle>
			<circle id="11" transform="translate(16.698730, 35) rotate(-60) translate(-16.698730, -35) " cx="16.6987298" cy="35" r="10"></circle>
			<circle id="10" transform="translate(10, 60) rotate(-90) translate(-10, -60) " cx="10" cy="60" r="10"></circle>
			<circle id="9" transform="translate(16.698730, 85) rotate(-120) translate(-16.698730, -85) " cx="16.6987298" cy="85" r="10"></circle>
			<circle id="8" transform="translate(35, 103.301270) rotate(-150) translate(-35, -103.301270) " cx="35" cy="103.30127" r="10"></circle>
			<circle id="7" cx="60" cy="110" r="10"></circle>
			<circle id="6" transform="translate(85, 103.301270) rotate(-30) translate(-85, -103.301270) " cx="85" cy="103.30127" r="10"></circle>
			<circle id="5" transform="translate(103.301270, 85) rotate(-60) translate(-103.301270, -85) " cx="103.30127" cy="85" r="10"></circle>
			<circle id="4" transform="translate(110, 60) rotate(-90) translate(-110, -60) " cx="110" cy="60" r="10"></circle>
			<circle id="3" transform="translate(103.301270, 35) rotate(-120) translate(-103.301270, -35) " cx="103.30127" cy="35" r="10"></circle>
			<circle id="2" transform="translate(85, 16.698730) rotate(-150) translate(-85, -16.698730) " cx="85" cy="16.6987298" r="10"></circle>
			<circle id="1" cx="60" cy="10" r="10"></circle>
		</g>
		<use xlink:href="#circle" class="use"/>
	</svg>
</div>