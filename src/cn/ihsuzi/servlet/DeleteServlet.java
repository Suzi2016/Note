package cn.ihsuzi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ihsuzi.dao.PasswordDao;

public class DeleteServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter out = response.getWriter();
		
		int pw_id = 0;
		pw_id = Integer.parseInt(request.getParameter("pw_id"));
		System.out.println("pw_id:"+pw_id);
		if (pw_id != 0)
		{
			try
			{
				PasswordDao.setPasswordIsShow(pw_id, false);
			} catch (SQLException e)
			{
				e.printStackTrace();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		out.write(pw_id);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
