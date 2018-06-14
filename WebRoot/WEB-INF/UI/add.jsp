<%@page import="cn.ihsuzi.util.ServiceUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>Note|添加一条记录</title>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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
	padding-left: 5px;
	width: 250px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

form {
	text-align: center;
	margin-top: 20px;
}

button {
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	padding: 8px 32px;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin-left: 20%;
	margin-top: 10px;
	cursor: pointer;
	border-radius: 6px;
}

p {
	display: none;
	margin-left: 18%;
}

table {
	margin-left: 20%;
	border-collapse: separate;
	border-spacing: 10px;
}

h3 {
	margin-left: 18%;
}

hr {
	margin-left: 18%;
	width: 50%;
}

small {
	margin-left: 18%;
	word-break: keep-all; /* 不换行 */
	white-space: nowrap; /* 不换行 */
}

a {
	text-decoration: none;
	color: #436EEE;
}

.helpInfo {
	margin-left: 0px;
	font-size: 12px;
}
</style>

<script type="text/javascript">
	function checkData() {
		var title = document.getElementById("titleid").value;
		var password = document.getElementById("passid").value;
		var account = document.getElementById("accountid").value;

		if (title == "") {
			alert("标题不能为空");
			return false;
		}
		if (password == "") {
			alert("账号不能为空");
			return false;
		}
		if (account == "") {
			alert("密码不能为空");
			return false;
		}

		document.getElementById("formid").submit();
	}

	function showHelpInfo() {
		var p = document.getElementById("helpInfo");
		if (p.style.display != "block") {
			p.style.display = "block";
		} else {
			p.style.display = "none";
		}
	}
</script>

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
	<h3>添加一条记录</h3>
	<small>
	   >>>>遇到困难？
	   <a href="" onclick="showHelpInfo();return false;">帮助</a>
	   &nbsp
	   <a href="<%=request.getContextPath() %>" >首页</a>
	</small>
	<hr />
	<form action="${pageContext.request.contextPath}/Add" method="POST"
		id="formid">
		<table>
			<tr>
				<td>标题</td>
				<td><input type="text" name="title" required="required"
					id="titleid" /></td>
				<td><small style="color:#FF0000;">${title_warning}</small></td>
			</tr>

			<tr>
				<td>账号</td>
				<td><input type="text" name="account" required="required"
					id="accountid" /></td>
				<td><small style="color:#FF0000;">${account_warning}</small></td>
			</tr>

			<tr>
				<td>密码</td>
				<td><input type="text" name="content" required="required"
					id="passid" /></td>
				<td><small style="color:#FF0000;">${content_warning}</small></td>
			</tr>

		</table>
	</form>
	<button id="addbutton" type="button" value="" onclick="checkData();">新建</button>

	<br />
	<br />
	<br />
	<br />

	<p id="helpInfo">
		<small class="helpInfo">标题：需要记录的账号的名称，比如&nbspNote</small><br /> 
		<small class="helpInfo">账号：需要记录的账号，一般来说会是一个用户名/手机号码/邮箱</small><br /> 
		<small class="helpInfo">密码：需要记录的账号的密码</small><br /><br /> 
		<small class="helpInfo">PS：账号密码信息会在客户端、服务器端、数据库端三重加密存储，保证您信息的绝对安全</small>
	</p>
</body>
</html>













