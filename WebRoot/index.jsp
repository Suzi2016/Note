<%@page import="cn.ihsuzi.dao.PasswordDao"%>
<%@page import="cn.ihsuzi.bean.PasswordSet"%>
<%@page import="cn.ihsuzi.dao.UserDao"%>
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
    
    <title>Note</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/index.css">
	
	<script>
		function deleteNote(pw_id,obj)
		{
		  var xmlhttp;
		  if (window.XMLHttpRequest)
		  {
		    // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		    xmlhttp=new XMLHttpRequest();
		  }
		  else
		  {
		    // IE6, IE5 浏览器执行代码
		    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		  xmlhttp.onreadystatechange=function()
		  {
		    if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		        deleteTableRow(obj);
		    }
		  };
		  xmlhttp.open("GET","/Note/deleteServlet?pw_id="+pw_id,true);
		  xmlhttp.send();
		}
		
		
		function deleteTableRow(obj)
		{
		    var tr = obj.parentNode.parentNode;
		    var table = tr.parentNode;
		    table.removeChild(tr);
		    if (table.rows.length == 1)
		    {
		        location.reload();
		    }
		}
	</script>

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
	          Note
	        </div>
	        <div class="headRight">
	          <a class="link" href="${pageContext.request.contextPath}/i">个人中心</a>&nbsp|
	          <a class="link" href="${pageContext.request.contextPath}/exit" target="">退出</a>
	        </div>
	      </div>
	       
	      <%-- 从数据库中读取用户数据，显示出来 --%>
	      <%
	          boolean hasData = false;   
	          String username = (String)request.getSession().getAttribute("username");
	          int user_id = UserDao.getUserId(username);
	          List<PasswordSet> list = PasswordDao.getPasswordSets(user_id);
	          if (list != null)
	          {
	              hasData = true;
	              System.out.println("lalala...");
	          }
	           
	      %>
	      
	      <!-- body part:show the operate funciton and user's Note info -->
	      <br/>
	      <div class="body">
	        <!-- user's info -->
	        <div>
	          <%
	              // 判断是否有数据
		          if(hasData)
		          {
		              out.write("<table align=center border=1>");
		              out.write("<tr>");
		              out.write("<th>标题</th>");
		              out.write("<th>账号</th>");
		              out.write("<th>密码</th>");
		              out.write("<th>创建时间</th>");
		              out.write("<th>更新时间</th>");
		              out.write("<th>操作</th>");
		              out.write("</tr>");
		              
					  for (int i = 0;i<list.size();i++)
					  {
					      out.write("<tr>");
					      out.write("<td>"+list.get(i).getTitle()+"</td>");
					      out.write("<td>"+list.get(i).getAccount()+"</td>");
					      out.write("<td>"+list.get(i).getPassword()+"</td>");
					      out.write("<td>"+list.get(i).getCreate_time()+"</td>");
					      out.write("<td>"+list.get(i).getUpdate_time()+"</td>");
					      out.write("<td>");
					      String button = "<button onclick='{if(confirm(\"确定要删除吗?\")) {deleteNote("+list.get(i).getPw_id()+",this); }else {}}'>删除</button>";
					      out.write(button);
					      out.write("</td>");
   					      out.write("<td>");
					      button = "<button onclick=\"window.open('./update','_self')\">修改</button>";
					      out.write(button);
					      out.write("</td>");
					      out.write("</tr>");
					  }		              
		              
		              out.write("</table>");
		          }else {
		              out.write("<p style='text-align: center;margin: 20px;'>还没有数据~~去添加吧&nbsp&#8595</p>");
		          }  
		          
	          %>
          
	        </div>
	        <!-- add button -->
	        <div class="add">
	          <button class="button" onclick="window.open('./add','_self')">ADD</button>
	        </div>
	      </div>
	      
	      <!-- footer part:show some website info -->
	      <br/>
	      <div class="footer">
	       &copy; 2018
	       <a class="link" href="http://ihsuzi.cn/" target="_blank">云中崖</a>
	      </div>
	    </div>
    
    </c:if>

  </body>
</html>






















