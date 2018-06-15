<%@page import="cn.ihsuzi.util.ServiceUtil"%>
<%@page import="cn.ihsuzi.dao.PasswordDao"%>
<%@page import="cn.ihsuzi.bean.PasswordSet"%>
<%@page import="cn.ihsuzi.dao.UserDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>Note</title>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/index.css">

<script>
	function deleteNote(pw_id, obj) {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
			xmlhttp = new XMLHttpRequest();
		} else {
			// IE6, IE5 浏览器执行代码
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				deleteTableRow(obj);
			}
		};
		xmlhttp.open("GET", "/Note/deleteServlet?pw_id=" + pw_id, true);
		xmlhttp.send();
	}

	function deleteTableRow(obj) {
		var tr = obj.parentNode.parentNode;
		var table = tr.parentNode;
		table.removeChild(tr);
		if (table.rows.length == 1) {
			location.reload();
		}
	}

	function update(element, oldTitle, oldAccount, oldPassword, pw_id, pwi_id) {
		// 获取修改后的值
		var tr = element.parentNode.parentNode;

		var titleTag = tr.firstChild;
		var accountTag = titleTag.nextSibling;
		var passwordTag = accountTag.nextSibling;
		var title = titleTag.innerHTML;
		var account = accountTag.innerHTML;
		var password = passwordTag.innerHTML;

		console.log("oldTitle:", oldTitle);
		console.log("newTitle:", title);
		// 判断是否进行了修改
		var isUpdate = false;
		if (title != oldTitle) {
			isUpdate = true;
		} else if (account != oldAccount) {
			isUpdate = true;
		} else if (password != oldPassword) {
			isUpdate = true;
		}

		if (!isUpdate) {
			alert("信息还没有改动哦~\n双击要修改的内容进行编辑");
		} else {
			var xmlhttp;
			if (window.XMLHttpRequest) {
				// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
				xmlhttp = new XMLHttpRequest();
			} else {
				// IE6, IE5 浏览器执行代码
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					alert("修改成功");
				}
			};
			xmlhttp.open("GET", "/Note/updateServlet?pw_id=" + pw_id
					+ "&pwi_id=" + pwi_id + "&title=" + title + "&account="
					+ account + "&password=" + password, true);
			xmlhttp.send();
		}

	}

	function ShowElement(element) {
		var oldhtml = element.innerHTML;
		//创建新的input元素
		var newobj = document.createElement('input');
		//为新增元素添加类型
		newobj.type = 'text';
		//为新增元素添加value值
		newobj.value = oldhtml;
		//为新增元素添加光标离开事件
		newobj.onblur = function() {
			//当触发时判断新增元素值是否为空，为空则不修改，并返回原有值 
			element.innerHTML = this.value == oldhtml ? oldhtml : this.value;
			if (this.value == "") {
				element.innerHTML = oldhtml;
			}

			//当触发时设置父节点的双击事件为ShowElement
			element.setAttribute("ondblclick", "ShowElement(this);");
		}
		//设置该标签的子节点为空
		element.innerHTML = '';
		//添加该标签的子节点，input对象
		element.appendChild(newobj);
		//设置选择文本的内容或设置光标位置（两个参数：start,end；start为开始位置，end为结束位置；如果开始位置和结束位置相同则就是光标位置）
		newobj.setSelectionRange(0, oldhtml.length);
		//设置获得光标
		newobj.focus();

		//设置父节点的双击事件为空
		newobj.parentNode.setAttribute("ondblclick", "");

	}
</script>

</head>

<body>

	<%
		// 如果未登录且是手机端访问就直接跳转到登录页面，此页面没有登录不适应手机屏幕，不会显示登录 注册 button
		boolean isLogin = ServiceUtil.isLogin(request);
		boolean isMobile = ServiceUtil.JudgeIsMoblie(request);
		if (!isLogin && isMobile)
		{
			response.sendRedirect(request.getContextPath() + "/login");
		}
	%>

	<c:if test="${sessionScope.username == null }">
		<div class="main">

			<!-- head part:show the website info & private info,exit button -->
			<div class="head">
				<div class="headLeft">Note</div>
				<div class="headRight">
					<a class="link" href="${pageContext.request.contextPath}/login">登录</a>
					&nbsp| <a class="link"
						href="${pageContext.request.contextPath}/register" target="">注册</a>
				</div>
			</div>

		</div>
	</c:if>

	<c:if test="${sessionScope.username != null }">
		<div class="main">

			<!-- head part:show the website info & private info,exit button -->
			<div class="head">
				<div class="headLeft">Note</div>
				<div class="headRight">
					<a class="link" href="${pageContext.request.contextPath}/i">个人中心</a>&nbsp|
					<a class="link" href="${pageContext.request.contextPath}/exit"
						target="">退出</a>
				</div>
			</div>

			<%-- 从数据库中读取用户数据，显示出来 --%>
			<%
				boolean hasData = false;
					String username = (String) request.getSession().getAttribute(
							"username");
					int user_id = UserDao.getUserId(username);
					List<PasswordSet> list = PasswordDao.getPasswordSets(user_id);
					if (list != null)
					{
						hasData = true;
					}
			%>

			<!-- body part:show the operate funciton and user's Note info -->
			<br />
			<div class="body">
				<!-- user's info -->
				<div>
					<%
						// 判断是否有数据
							if (hasData)
							{
								out.write("<center>");
								out.write("<table class=\"table\" cellspacing=\"0\" rules=\"all\" bordercolor=\"#CCCCCC\" border=\"1\">");
								out.write("<tr>");
								out.write("<th class=\"th\">标题</th>");
								out.write("<th>账号</th>");
								out.write("<th>密码</th>");
								//out.write("<th>创建时间</th>");
								//out.write("<th>更新时间</th>");
								out.write("<th>操作</th>");
								out.write("</tr>");

								for (int i = 0; i < list.size(); i++)
								{
									out.write("<tr>");
									out.write("<td class=\"td\" ondblclick=\"ShowElement(this);\">"
											+ list.get(i).getTitle() + "</td>");
									out.write("<td class=\"td\" ondblclick=\"ShowElement(this);\">"
											+ list.get(i).getAccount() + "</td>");
									out.write("<td class=\"td\" ondblclick=\"ShowElement(this);\">"
											+ list.get(i).getPassword() + "</td>");
									//out.write("<td class=\"td\">"+list.get(i).getCreate_time()+"</td>");
									//out.write("<td class=\"td\">"+list.get(i).getUpdate_time()+"</td>");
									out.write("<td class=\"td\">");
									String button = "<button class=\"button delete\" onclick='{if(confirm(\"确定要删除吗?\")) {deleteNote("
											+ list.get(i).getPw_id()
											+ ",this); }else {}}'>删除</button>";
									out.write(button);
									out.write("&nbsp&nbsp");
									button = "<button class=\"button update\" onclick=\"update(this,'"
											+ list.get(i).getTitle()
											+ "','"
											+ list.get(i).getAccount()
											+ "','"
											+ list.get(i).getPassword()
											+ "',"
											+ list.get(i).getPw_id()
											+ ","
											+ list.get(i).getPwi_id() + ");\">修改</button>";
									out.write(button);
									out.write("</td>");
									out.write("</tr>");

									list.get(i).getPw_id();
								}

								out.write("</table>");
								out.write("</center>");
								out.write("<br />");
								out.write("<small>提示：双击表格内容进行编辑，再点击修改进行确认</small>");
							} else
							{
								out.write("<p style='text-align: center;margin: 20px;'>还没有数据~~去添加吧&nbsp&#8595</p>");
							}
					%>

				</div>
				<!-- add button -->
				<br /> <br />
				<div class="add">
					<button class="button" onclick="window.open('./add','_self')">新建</button>
				</div>
			</div>

			<!-- footer part:show some website info -->
			<br />

		</div>

	</c:if>

	<footer class="footer22">
		&copy; 2018 <strong> <a class="link" href="http://ihsuzi.cn/"
			target="_blank">云中崖</a>
		</strong>
	</footer>
</body>
</html>






















