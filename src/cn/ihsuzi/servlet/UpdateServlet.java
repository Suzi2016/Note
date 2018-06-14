package cn.ihsuzi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ihsuzi.bean.PasswordInformation;
import cn.ihsuzi.dao.PasswordDao;
import cn.ihsuzi.dao.PasswordInformationDao;
import cn.ihsuzi.dao.UserDao;

public class UpdateServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 拿到修改的值
		int pw_id = Integer.parseInt(request.getParameter("pw_id"));
		int pwi_id = Integer.parseInt(request.getParameter("pwi_id"));
		String title = request.getParameter("title");
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		// System.out.println(pw_id+"--"+pwi_id+"--"+title+account+password);

		// 判断是否是当前登录用户在操作
		// 根据 pw_id 和 pwi_id 看是否可以查询到对应的 user_id，然后再和登录的用户的 user_id 进行比较
		// 相同则是当前用户
		String username = (String) request.getSession()
				.getAttribute("username");
		int login_user_id = 0; // 已经登录的用户的 user_id
		int user_id = -1; // 根据传过来的 pw_id 和 pwi_id 适配数据中得到的 user_id
		try
		{
			login_user_id = UserDao.getUserId(username);
			user_id = PasswordDao.getUserId(pw_id, pwi_id);

			// 只有两个 user_id 相同才能说明是当前用户在操作
			if (login_user_id == user_id)
			{
				// 判断数据是否修改，直接去数据库中匹配，如果匹配成功说明没有修改
				boolean isUpdate = !PasswordDao.isMatch(pw_id, pwi_id, title,
						account, password);
				if (isUpdate)
				{
					// 修改 password 表，字段 version+1，字段 pw_updatetime
					int new_version = PasswordDao
							.updateVersionAndUpdateTime(pw_id);
					PasswordInformation pwinfo = new PasswordInformation();
					pwinfo.setTitle(title);
					pwinfo.setPw_id(pw_id);
					pwinfo.setContent_one(account);
					pwinfo.setContent_two(password);
					pwinfo.setVersion(new_version);
					PasswordInformationDao.insert(pwinfo);
					out.write("true");
				} else
				{
					out.write("false");
				}
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
