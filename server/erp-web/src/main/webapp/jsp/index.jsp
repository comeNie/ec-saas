<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<%@ include file="light_header.jsp" %>
	<title>顾氏erp系统</title>
</head>

<frameset rows="88,*,31" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="${ctx}/frame/top" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame"/>
	<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
		<frame src="${ctx}/frame/left" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame"/>
		<frame src="${ctx}/frame/right" name="rightFrame" id="rightFrame" title="rightFrame"/>
	</frameset>
	<frame src="${ctx}/frame/footer" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" title="bottomFrame"/>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>