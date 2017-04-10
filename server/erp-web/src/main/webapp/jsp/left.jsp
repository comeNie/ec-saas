<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@ include file="light_header.jsp" %>
	<script type = "text/javascript" src="${ctx}/resources/js/auth/menu.js"></script>
	<script type="text/javascript">
		window.onload = function(){
			var data = '${data}';
			if(data && data != "") {
				erpMenu.refreshMenu("mbody", '${data_name}', data);
			}
		}
	</script>
</head>

<body style="background:#f0f9fd;">
	<div id="mbody">
		<div class="lefttop"><span></span></div>
	</div>

</body>
</html>
