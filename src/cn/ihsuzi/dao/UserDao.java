package cn.ihsuzi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.ihsuzi.bean.User;
import cn.ihsuzi.data.ConstantValues;

public class UserDao
{
	/**
	 * 插入一条数据
	 * @param user
	 * @throws Exception
	 */
	public static void insertUser(User user) throws Exception
	{
		//要链接数据库的url
		String url = "jdbc:mysql://localhost:3306/Note";
		//链接数据库的用户名和密码
		String username = ConstantValues.DB_USERNAME;
		String password = ConstantValues.DB_PASSWORD;

		// 1.加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		
		// 2.取得链接
		Connection con = DriverManager.getConnection(url, username, password);
		
		// 3.取得Statement，用于发送 sql语句
		Statement statement = con.createStatement();
		
		// 4.拼接 sql 语句
		String insertSql = String.format("insert into user(user_name,user_email,user_password) "
				+ "values('%s','%s','%s');", user.getUsername(),user.getEmail(),user.getPassword());
		
		// 5.执行 sql 语句
		statement.executeUpdate(insertSql);
		
		// 6.关闭数据库
		statement.close();
		con.close();
		
	}
	
	
	/**
	 * 查询是否有此条纪录
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static boolean isExist(User user) throws Exception
	{
		//要链接数据库的url
		String url = "jdbc:mysql://localhost:3306/Note";
		//链接数据库的用户名和密码
		String username = ConstantValues.DB_USERNAME;
		String password = ConstantValues.DB_PASSWORD;

		// 1.加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		
		// 2.取得链接
		Connection con = DriverManager.getConnection(url, username, password);
		
		// 3.取得Statement，用于发送 sql语句
		Statement statement = con.createStatement();
		
		// 4.拼接 sql 语句
		String sql = String.format("select user_id from user where user_name='%s' and user_password='%s';", 
				user.getUsername(),user.getPassword());
		System.out.println("sql:"+sql);
		// 5.执行 sql 语句
		ResultSet set = (ResultSet) statement.executeQuery(sql);
		
		// 查看是否返回了数据
		boolean haveData;
		if (set.next())
		{
			haveData = true;
		}else {
			haveData = false;
		}
		System.out.println("haveData:"+haveData);
		
		// 6.关闭数据库
		statement.close();
		con.close();
		
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
		//要链接数据库的url
		String url = "jdbc:mysql://localhost:3306/Note";
		//链接数据库的用户名和密码
		String username = ConstantValues.DB_USERNAME;
		String password = ConstantValues.DB_PASSWORD;

		// 1.加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		
		// 2.取得链接
		Connection con = DriverManager.getConnection(url, username, password);
		
		// 3.取得Statement，用于发送 sql语句
		Statement statement = con.createStatement();
		
		// 4.拼接 sql 语句
		String sql = String.format("select user_id from user where user_name='%s';", 
				user.getUsername());
		System.out.println("sql:"+sql);
		// 5.执行 sql 语句
		ResultSet set = (ResultSet) statement.executeQuery(sql);
		
		// 查看是否返回了数据
		boolean haveData;
		if (set.next())
		{
			haveData = true;
		}else {
			haveData = false;
		}
		System.out.println("haveData:"+haveData);
		
		// 6.关闭数据库
		statement.close();
		con.close();
		
		return haveData;
	}
	
}

























