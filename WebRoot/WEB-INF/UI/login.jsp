<%@page import="cn.ihsuzi.util.ServiceUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Note | 登录</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
	<meta name="expires" content="-1">
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">  
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is login page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <style type="text/css">
      html {
        background-color: #FFFAF0;
      }
  
	  input {
		border: 1px solid #ccc;
		padding: 7px 0px;
		border-radius: 3px;
		padding-left:5px;
		-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
		box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
		-webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
		-o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
		transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s
	  }
	  
	  form {
	    text-align:center;
	    
	  }
	  
	  #loginbutton {
	    background-color: #4CAF50; /* Green */
	    border: none;
	    color: white;
	    padding: 6px 32px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 14px;
	    cursor: pointer;
	    border-radius: 4px;
	  }
	  
  	  #registerbutton {
	    background-color: #FFFAF0; /* Green */
	    border: 1px solid #4CAF50; /* Green */
	    color: #4CAF50;
	    padding: 5px 32px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 14px;
	    cursor: pointer;
	    border-radius: 4px;
	  }
	  
	  p {
	    text-align:center;
	    padding-top: 0px;
	    margin-top: 0px;
	  }
	  
	  input {
        width:200px;	  
	  }
  
  </style>
  
  <script type="text/javascript">
	  function checkData(){
		 var result = document.getElementById("userid").value;
		 var password = document.getElementById("passid").value;
		
		 if(result == ""  ){
		   alert("用户名不能为空");
		   return false;
		 }
		 if(password == ""  ){
		  alert("密码不能为空");
		   return false;
		 }
		document.getElementById("formid").submit();
     }
  </script>

  </head>
  
  <body>
    <%
        // 判断是否已经登录了，如果登录了，就直接跳转到主页
		if (ServiceUtil.isLogin(request))
		{
			response.sendRedirect(request.getContextPath());
		}
     %>
    
    <br/><br/><br/><br/>
	<form action="${pageContext.request.contextPath}/login/verify" method="post" id="formid">

	  <input type="text" name="username" required="required" id="userid" placeholder="昵称"><br/><br/>

	  <input type="password" name="password" required="required" id="passid" placeholder="密码"><br/><br/>
	</form>
	<p>
	  <button id="loginbutton" type="button" value="" onclick = "checkData();">登录</button>
	  <button id="registerbutton" type="button" value="" class="btn2" onclick="window.open('./register','_self')">注册</button>
	</p>
	<br/>
	
	<p style="text-align: center;color:red;">${error_info }</p>
	
  </body>
</html>



