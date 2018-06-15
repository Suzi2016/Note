<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>Note|注册</title>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<meta name="expires" content="-1">
<meta name="pragma" content="no-cache">
<meta name="cache-control" content="no-cache">
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
	text-align: center;
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
</style>

<script type="text/javascript">
	function checkData() {
		var result = document.getElementById("userid").value;
		var password = document.getElementById("passid").value;
		var repassword = document.getElementById("repassid").value;
		var valistr = document.getElementById("validateid").value;

		if (result == "") {
			alert("用户名不能为空");
			return false;
		}
		if (password == "") {
			alert("密码不能为空");
			return false;
		}
		if (repassword == "") {
			alert("请确认密码");
			return false;
		}
		if (valistr == "") {
			alert("验证码不能为空");
			return false;
		}

		document.getElementById("formid").submit();
	}

	function changeImg(img) {
		img.src = "./ValidateImg?time=" + new Date().getTime();
	}
</script>

</head>

<body>
	<br />
	<br />
	<br />
	<h3>欢迎加入Note</h3>
	<small>>>>>已有账号，直接去&nbsp<a
		href="${pageContext.request.contextPath}/login">登录</a></small>
	<hr>
	<form action="${pageContext.request.contextPath}/Register"
		method="post" id="formid">
		<table>
			<tr>
				<td>昵&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp称</td>
				<td><input type="text" name="username" id="userid"></td>
				<td><small style="color:#FF0000;">${username_warning}</small></td>
			</tr>
			<tr>
				<td>密&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp码</td>
				<td><input type="password" name="password" id="passid"></td>
				<td><small style="color:#FF0000;">${pass_warning}</small></td>
			</tr>
			<tr>
				<td>确认密码</td>
				<td><input type="password" name="repassword" id="repassid"></td>
				<td><small style="color:#FF0000;">${repass_warning}</small></td>
			</tr>
			<tr>
				<td>验&nbsp&nbsp证&nbsp&nbsp码</td>
				<td><input type="text" name="valistr" id="validateid"></td>
				<td><img src="./ValidateImg" style="cursor:pointer;"
					onclick="changeImg(this)" /></td>
				<td><small style="color:#FF0000;">${vali_warning}</small></td>
			</tr>
		</table>
	</form>
	<button id="registerbutton" type="button" value=""
		onclick="checkData();">注册</button>
</body>
</html>
