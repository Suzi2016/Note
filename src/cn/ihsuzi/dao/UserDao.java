package cn.ihsuzi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.ihsuzi.bean.User;

public class UserDao
{
	private UserDao(){}
	

	/**
	 * 插入一条数据
	 * @param user
	 * @throws Exception
	 */
	public static void insertUser(User user) throws Exception
	{		
		// 拼接 sql 语句
		String insertSql = String.format("insert into user(user_name,user_email,user_password) "
				+ "values('%s','%s','%s');", user.getUsername(),user.getEmail(),user.getPassword());
	
		DBUtil.creatInstance().getStatement().executeUpdate(insertSql);
		DBUtil.close();

	}
	
	
	/**
	 * 查询是否有此条纪录
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static boolean isExist(User user) throws Exception
	{	
		// 拼接 sql 语句
		String sql = String.format("select user_id from user where user_name='%s' and user_password='%s';", 
				user.getUsername(),user.getPassword());

		// 执行 sql 语句
		ResultSet set = (ResultSet) DBUtil.creatInstance().getStatement().executeQuery(sql);
		
		// 查看是否返回了数据
		boolean haveData;
		if (set.next())
		{
			haveData = true;
		}else {
			haveData = false;
		}
		
		// 关闭数据库
		DBUtil.close();
		
		return haveData;
	}
	
	
	/**
	 * 查询是否有此条纪录
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static boolean isUsernameExist(User user) throws Exception
	{
		// 拼接 sql 语句
		String sql = String.format("select user_id from user where user_name='%s';", 
				user.getUsername());

		// 执行 sql 语句
		ResultSet set = (ResultSet) DBUtil.creatInstance().getStatement().executeQuery(sql);
		
		// 查看是否返回了数据
		boolean haveData;
		if (set.next())
		{
			haveData = true;
		}else {
			haveData = false;
		}
		
		// 关闭数据库
		DBUtil.close();
		
		return haveData;
	}
	
	
	public static int getUserId(String username) throws Exception
	{
		int user_id = 0;
		// 拼接 sql 语句
		String sql = String.format("select user_id from user where user_name='%s';",username);
		
		// 执行 sql 语句
		ResultSet set = (ResultSet) DBUtil.creatInstance().getStatement().executeQuery(sql);
		
		// 拿到 user_id
		if (set.next())
		{
			user_id = set.getInt(set.getRow());
		}
		
		// 关闭数据库
		DBUtil.close();
		
		return user_id;
	}
	
}

























