<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <h3 style="padding-left:200px;padding-top:30px">欢迎加入</h3>
    <br/>
	<form action="./RegisterServlet" method="post" style="padding-left:230px">
	    用户名:&nbsp<input type="text" name="username">
	  <p></p>

	    密&nbsp&nbsp&nbsp&nbsp码:&nbsp<input type="text" name="password">
	  <p></p>
	  
	  <input style="width:80px" type="submit" value="注册"/>
	</form>
  </body>
</html>
