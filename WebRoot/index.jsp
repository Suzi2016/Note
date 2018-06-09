<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Note</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/private.css">

  </head>
  
  <body>
    
    <c:if test="${sessionScope.username == null }">
        <div style="background-color: #E0FFFF;height: 90px">
           <h2 style="padding-top: 24px;text-align:center;">Note</h2>
        </div>
       
        <div style="background-color: #76EE00;height: 90px">
	      <div onclick="window.open('./login','_self')" style="background-color: #76EE00;height: 90px">
	           <h2 style="padding-top: 20px;text-align:center;">登录</h2>
	      </div>
	      <div onclick="window.open('./register.jsp','_self')" style="background-color: #76EE00;height: 90px">
	           <h2 style="padding-top: 20px;text-align:center;">注册</h2>
	      </div>	      

	    </div>
    </c:if>
    
    <c:if test="${sessionScope.username != null }">
	    <div class="main">
	    
	      <!-- head part:show the website info & private info,exit button -->
	      <div class="head">
	        <div class="headLeft">
	          <a class="link" href="${pageContext.request.contextPath}/private" style="color:#000000;">首页</a>->个人中心
	        </div>
	        <div class="headRight">
	          <a class="link" href="${pageContext.request.contextPath}/i">个人中心</a>&nbsp|
	          <a class="link" href="${pageContext.request.contextPath}/exit" target="">退出</a>
	        </div>
	      </div>
	      
	      
	      
	      <%-- 从数据库中读取用户数据，显示出来 --%>
	      <%
	          boolean hasData = false;   
	           
	      %>
	      
	      <!-- body part:show the operate funciton and user's Note info -->
	      <div>
	        <!-- user's info -->
	        <div>
	          <%
	              // 判断是否有数据
		          if(hasData)
		          {
		              
		          }else {
		              out.write("还没有数据！！");
		          }  
	          %>
	        </div>
	        <!-- add button -->
	        <div class="add">
	          <button onclick="window.open('./add','_self')">ADD</button>
	        </div>
	      </div>
	      
	      <!-- footer part:show some website info -->
	      <div class="footer">
	       &copy; 2018
	       <a class="link" href="http://ihsuzi.cn/" target="_blank">云中崖</a>
	      </div>
	    </div>
    
    </c:if>

  </body>
</html>






















