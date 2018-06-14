package cn.ihsuzi.servlet;

import java.io.IOException;

import javax.print.ServiceUI;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ihsuzi.bean.User;
import cn.ihsuzi.dao.UserDao;
import cn.ihsuzi.util.ServiceUtil;
import cn.ihsuzi.util.StringUtil;

public class LoginServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 判断是否已经登录了，如果登录了，就直接跳转到主页
		if (ServiceUtil.isLogin(request))
		{
			response.sendRedirect(request.getContextPath());
		}

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 判断得到的 username 和 password 是否为空
		if (username==null || StringUtil.isEmpty(username))
		{
			// 校验失败，提示用户名不能为空
			request.setAttribute("error_info", "账号不能为空");
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		if (password==null || StringUtil.isEmpty(password))
		{
			// 校验失败，提示密码不能为空
			request.setAttribute("error_info", "密码不能为空");
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		
		User user = new User(username,password);
		// 校验用户名密码是否正确
		try
		{
			if (UserDao.isExist(user))
			{
				// 校验正确，设置 sesseion，跳转到主页
				request.getSession().setAttribute("username", user.getUsername());
				response.sendRedirect(request.getContextPath());
				return;
			}else {
				// 校验失败，提示用户名或密码错误
				request.setAttribute("error_info", "账号或密码错误");
				request.getRequestDispatcher("/login").forward(request, response);
				return;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}


	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}









