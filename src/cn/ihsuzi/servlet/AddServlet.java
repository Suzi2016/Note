package cn.ihsuzi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ihsuzi.bean.PasswordInformation;
import cn.ihsuzi.dao.PasswordDao;
import cn.ihsuzi.dao.PasswordInformationDao;
import cn.ihsuzi.dao.UserDao;
import cn.ihsuzi.util.StringUtil;

public class AddServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;");
		
		// 判断是否已经登录，如果没有登录直接访问，则重定向到主页
		// 判断是否存在 session，如果不存在 session 或者没有相应的 username 参数，则没有登录，回到主页
		if (request.getSession(false) ==null || request.getSession().getAttribute("username")==null)
		{
			response.sendRedirect(request.getContextPath());
			return;
		}

		// 拿到从 add.jsp 提交上来的数据，并作简单校验
		// TODO 对特殊字符进行转义，比如 ' " 等符号在存储的时侯不进行转义会出错
		String title = request.getParameter("title");
		System.out.println(title);
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
			
		}else {
			// TODO 判断标题是否已经使用了
		}
		String account = request.getParameter("account");
		if (account == null || StringUtil.isEmpty(account))
		{
			try
			{
				request.setAttribute("account_warning", "账号不能为空");
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
		int user_id = 0;
        try
		{
			user_id = UserDao.getUserId(username);
			int pw_id = PasswordDao.insert(user_id);
			if (pw_id != 0)
			{
				PasswordInformation pwinfo = new PasswordInformation();
				pwinfo.setTitle(title);
				pwinfo.setPw_id(pw_id);
				pwinfo.setContent_one(account);
				pwinfo.setContent_two(content);
				pwinfo.setVersion(1);
				PasswordInformationDao.insert(pwinfo);
			}else {
				// TODO 报错
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
        
        // TODO 提示用户添加成功，并跳转回主页
//        response.getWriter().write( "添加成功");
        response.sendRedirect(request.getContextPath());
        
        
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
