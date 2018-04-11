package cn.ihsuzi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ihsuzi.bean.User;
import cn.ihsuzi.dao.UserDao;

public class LoginServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User(username,password);
		
		PrintWriter out = response.getWriter();
		
		// 校验用户名密码是否正确
		try
		{
			if (UserDao.isExist(user))
			{
				// 校验正确，跳转到主页
				response.setHeader("refresh", "0;url=./index.jsp");
			}else {
				// 校验失败，提示用户名或密码错误
				out.write("<script>window.alert('用户名或密码错误');</script>");
				response.setHeader("refresh", "0;url=./login.jsp");
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		doGet(request, response);
	}

}
