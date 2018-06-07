<%@page import="org.apache.taglibs.standard.tag.el.core.IfTag"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人主页</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />

	<link rel="stylesheet" type="text/css" href="css/private.css">


  </head>
  
  <body>
  
  <%
	  // 当未登录时，跳转到登录注册界面
	  String username = (String)request.getSession().getAttribute("username");
	  if (username == null)
	  {
	      response.sendRedirect(request.getContextPath());
	  }
   %>

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

  </body>
</html>























