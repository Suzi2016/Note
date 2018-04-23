<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is login page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    
    <br/>
	<form action="${pageContext.request.contextPath}/Login" method="post" style="text-align:center;">
	  用户名: <input type="text" name="username"><br/><br/>
	  密&nbsp&nbsp&nbsp&nbsp码: <input type="text" name="password"><br/><br/>
	  <input style="width:80px" type="submit" value="登录">
	</form>
  </body>
</html>
