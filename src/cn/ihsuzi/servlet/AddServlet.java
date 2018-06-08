package cn.ihsuzi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ihsuzi.util.StringUtil;

public class AddServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;");
		
		// 判断是否已经登录，如果没有登录直接访问，则重定向到主页
		// 判断是否存在 session，如果不存在 session 或者没有相应的 username 参数，则没有登录，回到主页
		if (request.getSession(false) ==null || request.getSession().getAttribute("username")==null)
		{
			response.sendRedirect(request.getContextPath());
			return;
		}

		// 拿到从 add.jsp 提交上来的数据，并作简单校验
		String title = request.getParameter("title");
		if (title == null || StringUtil.isEmpty(title))
		{
			// 如果浏览器长时间没操作再次直接刷新可能会报错：cant forword after response is commited
			try
			{
				request.setAttribute("title_warning", "标题不能为空");
				request.getRequestDispatcher("/add").forward(request, response);
				return;
			} catch (Exception e)
			{
				e.printStackTrace();
				response.sendRedirect(request.getContextPath());
				return;
			}
			
		}
		String description = request.getParameter("description");
		if (description == null || StringUtil.isEmpty(description))
		{
			try
			{
				request.setAttribute("description_warning", "简介不能为空");
				request.getRequestDispatcher("/add").forward(request, response);
				return;
			} catch (Exception e)
			{
				e.printStackTrace();
				response.sendRedirect(request.getContextPath());
				return;
			}
		}
		String content = request.getParameter("content");
		if (content == null || StringUtil.isEmpty(content))
		{
			try
			{
				request.setAttribute("content_warning", "不能为空");
				request.getRequestDispatcher("/add").forward(request, response);
				return;
			} catch (Exception e)
			{
				e.printStackTrace();
				response.sendRedirect(request.getContextPath());
				return;
			}
		}
		
		// 将记录添加到数据库，提示用户，返回首页
		String username = (String) request.getSession().getAttribute("username");
        int user_id = 1;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
