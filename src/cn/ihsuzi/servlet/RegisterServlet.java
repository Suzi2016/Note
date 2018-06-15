package cn.ihsuzi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ihsuzi.bean.User;
import cn.ihsuzi.dao.UserDao;
import cn.ihsuzi.util.StringUtil;

public class RegisterServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 解决乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;");

		// 获取从浏览器传过来的注册信息 并进行错误校验
		String username = request.getParameter("username");
		System.out.println("RegisterServlet:username--"+username);
		if (username == null || StringUtil.isEmpty(username))
		{
			try
			{
				request.setAttribute("username_warning", "昵称不能为空");
				request.getRequestDispatcher("/register").forward(request,
						response);
				return;
			} catch (Exception e)
			{
				e.printStackTrace();
				response.sendRedirect(request.getContextPath());
				return;
			}
		}
		String password = request.getParameter("password");
		if (password == null || StringUtil.isEmpty(password))
		{
			try
			{
				request.setAttribute("pass_warning", "密码不能为空");
				request.getRequestDispatcher("/register").forward(request,
						response);
				return;
			} catch (Exception e)
			{
				e.printStackTrace();
				response.sendRedirect(request.getContextPath());
				return;
			}
		}
		String repassword = request.getParameter("repassword");
		if (repassword == null || StringUtil.isEmpty(repassword))
		{
			try
			{
				request.setAttribute("repass_warning", "请确认密码");
				request.getRequestDispatcher("/register").forward(request,
						response);
				return;
			} catch (Exception e)
			{
				e.printStackTrace();
				response.sendRedirect(request.getContextPath());
				return;
			}
		}

		// 判断两次密码是否相同
		if (!repassword.equals(password))
		{
			try
			{
				request.setAttribute("repass_warning", "两次输入的密码不一样");
				request.getRequestDispatcher("/register").forward(request,
						response);
				return;
			} catch (Exception e)
			{
				e.printStackTrace();
				response.sendRedirect(request.getContextPath());
				return;
			}
		}

		// 校验验证码
		String valistr = request.getParameter("valistr");
		String valistr2 = (String) request.getSession().getAttribute("valistr");
		if (valistr == null || valistr2 == null || !valistr.equals(valistr2))
		{
			System.out.println("RegisterServlet:验证码不对");
			request.setAttribute("vali_warning", "验证码不对");
			request.getRequestDispatcher("/register")
					.forward(request, response);
			return;
		}
		
		// 判断信息是否已经注册了
		// 如果注册了就提示用户已经注册
		// 没有注册就进行注册，并提示注册成功，跳转到登录界面
		PrintWriter out = response.getWriter();

		User user = new User(username, password, "no_email");
		try
		{
			// 判断用户名是否已经注册了
			if (UserDao.isUsernameExist(user))
			{
				request.setAttribute("username_warning", "昵称已经被使用了");
				request.getRequestDispatcher("/register").forward(request,
						response);
				return;
			} else
			{
				UserDao.insertUser(user);
				out.write("注册成功，马上跳转到登录界面");
				response.setHeader("refresh", "2;url=./login");
			}
		} catch (Exception e)
		{
			// TODO 此处可以跳转到错误页面 500
			out.write("发生了未知错误");
			response.setHeader("refresh", "2;url=./register");
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









