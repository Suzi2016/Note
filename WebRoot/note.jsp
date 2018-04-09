<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Note</title>
    
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
      <div style="background-color: #E0FFFF;height: 90px">
           <h2 style="padding-top: 24px;text-align:center;">Note</h2>
      </div>
       
      <div style="background-color: #76EE00;height: 90px">
	      <div onclick="window.open('./login.jsp','_self')" style="background-color: #76EE00;height: 90px">
	           <h2 style="padding-top: 20px;text-align:center;">登录</h2>
	      </div>
	      <div onclick="window.open('./register.jsp','_self')" style="background-color: #76EE00;height: 90px">
	           <h2 style="padding-top: 20px;text-align:center;">注册</h2>
	      </div>	      

	  </div>

	  
	  
  </body>
</html>
