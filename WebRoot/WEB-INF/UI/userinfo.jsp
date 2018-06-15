<%@page import="cn.ihsuzi.util.ServiceUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Note|个人中心</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="Note">
<meta http-equiv="description" content="Note|UserInfo">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
a {
	text-decoration: none;
	color: #436EEE;
}

</style>
</head>

<%
	// 判断是否已经登录了，如果还没有登录了，就直接跳转到主页
	if (!ServiceUtil.isLogin(request))
	{
		response.sendRedirect(request.getContextPath());
	}
%>

<body>
	<br />
	<br />
	>>>>欢迎你，
	<a href="<%=request.getContextPath()%>"><%=session.getAttribute("username")%></a>
	<hr>
	<h3>跟多功能，敬请期待...</h3>
</body>
</html>












