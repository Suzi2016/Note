package cn.ihsuzi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import cn.ihsuzi.util.PropertiesUtil;

/**
 * 获取 JDBC 连接实例
 * @author Suzi
 *
 */
public class DBUtil
{
	private Connection connection = null;
	private Statement statement = null;
	
	private static DBUtil dbUtil = null;
	
	private DBUtil(){}
	
	public static DBUtil creatInstance() throws Exception
	{
		if (dbUtil == null)
		{
			dbUtil = new DBUtil();
		}
		dbUtil.init();
		return dbUtil;
	}
	
	private void init() throws Exception
	{	
		String filename = "cn/ihsuzi/config/jdbc.properties";
		Properties properties = PropertiesUtil.loadProperties(filename);
		String db_name = properties.getProperty("name");
		String db_password = properties.getProperty("password");
		String url = properties.getProperty("url");
		
		// 1.加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		
		// 2.取得链接
		connection = DriverManager.getConnection(url, db_name, db_password);
		
		// 3.取得Statement，用于发送 sql语句
		statement = connection.createStatement();
	}
	
	public static void close()
	{
		try
		{
			if (dbUtil != null)
			{
				if (dbUtil.statement != null)
				{
					dbUtil.connection.close();
				}
				if (dbUtil.connection != null)
				{
					dbUtil.connection.close();
				}
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public Connection getConnection()
	{
		return connection;
	}

	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

	public Statement getStatement()
	{
		return statement;
	}

	public void setStatement(Statement statement)
	{
		this.statement = statement;
	}


	
	
}

























