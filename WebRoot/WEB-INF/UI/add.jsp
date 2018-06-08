<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Note|添加一条记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
 
   <%
	  // 当未登录时，跳转到登录注册界面
	  String username = (String)request.getSession().getAttribute("username");
	  if (username == null)
	  {
	      response.sendRedirect(request.getContextPath());
	  }
   %>
  
  <body>
      <div style="align:center;margin: 20px;">
          <h3>添加一条记录</h3>
          <form action="${pageContext.request.contextPath}/Add" method="POST">
              <table>
                  <tr>
                      <td>标题</td>
                      <td>
                          <input type="text" name="title" required="required"/>
                      </td>
                      <td><small style="text-color:red;">${title_warning}</small></td>
                  </tr>
                  
                  <tr>
                      <td>简介</td>
                      <td>
                          <input type="text" name="description" required="required"/>
                      </td>
                  </tr>
                  
                  <tr>
                      <td>密码</td>
                      <td>
                          <input type="text" name="content" required="required"/>
                      </td>
                  </tr>
                  
                  <tr>
                      <td colspan="2">
                          <input type="submit" value="添加"/>
                      </td>
                  </tr>
              </table>
          </form>
      </div>
  </body>
</html>













