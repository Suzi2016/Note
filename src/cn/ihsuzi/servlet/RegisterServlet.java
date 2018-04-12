package cn.ihsuzi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ihsuzi.bean.User;
import cn.ihsuzi.dao.UserDao;
import cn.ihsuzi.data.ConstantValues;

public class RegisterServlet extends HttpServlet
{


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 解决乱码问题
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/heml;charset=UTF-8");
		
		//TODO 做一个空值验证以及错误处理

		// 获取从浏览器传过来的注册信息
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Test 解决数据库存储乱码的问题，打印从浏览器获取的用户名和密码，查看是否是乱码
		System.out.println("username:"+username);
		System.out.println("password:"+password);


		
		boolean isValid = true;
		
		// 判断信息是否已经注册了
		if (username == "" || username.isEmpty() || password == "" || password.isEmpty())
		{
			isValid = false;
		}
		
		// 如果注册了就提示用户已经注册
		// 没有注册就进行注册，并提示注册成功，跳转到登录界面
		PrintWriter out = response.getWriter();
		if (isValid)
		{
			User user = new User(username, password,"no_email");
			// 将注册用户信息存储到数据库中
			try
			{
				// 判断用户名是否已经注册了
				if (UserDao.isUsernameExist(user))
				{
					out.write("用户名已被注册，请重试");
					response.setHeader("refresh", "2;url=./register.jsp");
				}else {
					UserDao.insertUser(user);
					out.write("注册成功，3秒后跳转到登录界面");
					response.setHeader("refresh", "3;url=./login.jsp");
				}
			} catch (Exception e)
			{
				out.write("注册失败，请重试");
				response.setHeader("refresh", "2;url=./register.jsp");
				e.printStackTrace();
			}
			
		}else {
			out.write("用户名或密码不能为空");
			response.setHeader("refresh", "2;url=./register.jsp");
		}
		
		out.flush();
		out.close();
		
		
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}















