package cn.ihsuzi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认的 servlet，当发生错误（访问不存在的 servlet）的时候访问此 servlet
 */
public class RegisterServlet extends HttpServlet
{


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 解决乱码问题
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/heml;charset=UTF-8");
		

		PrinterWriter out = response.getWriter();
		out.write("出现错误了....");
		
		out.flush();
		out.close();
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}















