package cn.ihsuzi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.ihsuzi.bean.PasswordSet;
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
	
	public static List<PasswordSet> getPasswordSets(int user_id) throws SQLException, Exception
	{
		String sql = "select b.pw_id,b.pwi_id,a.pw_createtime as create_time,a.pw_updatetime update_time,b.content_one as account,b.content_two as password,b.title from password as a,passwordinformation as b where a.user_id=1 and a.pw_isshow=%d and a.version=b.version and a.pw_id=b.pw_id;";
		String selectSql = String.format(sql, user_id);
		
		ResultSet set = DBUtil.creatInstance().getStatement().executeQuery(selectSql);
		if (!set.next())
		{
			return null;
		}

		List<PasswordSet> pwList = new ArrayList<PasswordSet>();
		PasswordSet setObj;
		do
		{
			setObj = new PasswordSet();
			setObj.setPw_id(set.getInt("pw_id"));
			setObj.setPwi_id(set.getInt("pwi_id"));
			setObj.setCreate_time(set.getString("create_time"));
			setObj.setUpdate_time(set.getString("update_time"));
			setObj.setTitle(set.getString("title"));
			setObj.setAccount(set.getString("account"));
			setObj.setPassword(set.getString("password"));
			pwList.add(setObj);
		} while (set.next());
		
		DBUtil.close();
		
		return pwList;
	}
	
	
	
}

















