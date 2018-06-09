package cn.ihsuzi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import cn.ihsuzi.bean.Password;
import cn.ihsuzi.util.TestUtil;

public class PasswordDao
{
	public static final String CLASSNAME = "PasswordDao";

	private PasswordDao()
	{
	}

	public static int insert(int user_id) throws Exception
	{
		// 拿到当前时间
		String time = new Timestamp(System.currentTimeMillis()).toString();
		// 拼接 sql 语句
		String sql = String
				.format("insert into password(user_id,type,version,pw_createtime,pw_updatetime,pw_isshow,pw_isdelete,pw_degree) values(%d,1,1,'%s','%s',1,0,1);",
						user_id, time, time);
		
		Statement statement = DBUtil.creatInstance().getStatement();
		statement.executeUpdate(sql);
		
		// 拿到 pw_id
		String selectIdSql = "select @@identity as id;";
		ResultSet set = (ResultSet) statement.executeQuery(selectIdSql);
		
		int pw_id = 0;
		if (set.next())
		{
			pw_id = set.getInt(set.getRow());
			TestUtil.printLog(CLASSNAME, "pw_id="+pw_id);
		}
		DBUtil.close();
		
		return pw_id;
	}
	
	
	
}
