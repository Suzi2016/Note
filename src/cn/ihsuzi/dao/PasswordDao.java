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
		String sql = "select b.pw_id,b.pwi_id,a.pw_createtime as create_time,a.pw_updatetime update_time,b.content_one as account,b.content_two as password,b.title from password as a,passwordInformation as b where a.user_id=%d and a.pw_isshow=1 and a.version=b.version and a.pw_id=b.pw_id;";
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
	
	
	public static void setPasswordIsShow(int pw_id,boolean isShow) throws SQLException, Exception
	{
		int showValue = 0;
		if (isShow)
		{
			showValue = 1;
		}
		String sql = String.format("update password set pw_isshow=%d where pw_id=%d", showValue,pw_id);
		DBUtil.creatInstance().getStatement().executeUpdate(sql);
		DBUtil.close();
	}
	
	
	public static boolean isMatch(int pw_id,int pwi_id,String title,String account,String password) throws SQLException, Exception
	{
		// !!!重要，在换行写 String 时一定要注意空格!!!
		String format = "select a.user_id "
				+ "from password as a,passwordInformation as b "
				+ "where a.pw_id=%d "
				+ "and b.pw_id=%d "
				+ "and b.pwi_id=%d "
				+ "and b.title='%s' "
				+ "and b.content_one='%s' "
				+ "and b.content_two='%s';";		
		String sql = String.format(format, pw_id,pw_id,pwi_id,title,account,password);
		System.out.println(sql);
		
		// 执行 sql 语句
		ResultSet set = (ResultSet) DBUtil.creatInstance().getStatement().executeQuery(sql);
		
		// 拿到 user_id
		if (set.next())
		{
			return true;
		}
		
		// 关闭数据库
		DBUtil.close();
		
		return false;
	}
	
	public static int getUserId(int pw_id,int pwi_id) throws Exception
	{
		String format = "select a.user_id "
				+ "from password as a,passwordInformation as b "
				+ "where a.pw_id=%d "
				+ "and b.pw_id=%d "
				+ "and b.pwi_id=%d;";
		String sql = String.format(format, pw_id,pw_id,pwi_id);
		System.out.println("sql:"+sql);
		
		// 执行 sql 语句
		ResultSet set = (ResultSet) DBUtil.creatInstance().getStatement().executeQuery(sql);
		
		int user_id = -1;
		
		// 拿到 user_id
		if (set.next())
		{
			user_id = set.getInt(set.getRow());
		}
		
		// 关闭数据库
		DBUtil.close();
		
		return user_id;
	}
	
	/**
	 * 
	 * @param pw_id
	 * @return 修改后的 version
	 * @throws Exception
	 */
	public static int updateVersionAndUpdateTime(int pw_id) throws Exception
	{
		// 拿到当前时间
		String time = new Timestamp(System.currentTimeMillis()).toString();
		String format = "update password set version=version+1,pw_updatetime='%s' where pw_id=%d;";
		String sql = String.format(format, time,pw_id);
		
		Statement statement = DBUtil.creatInstance().getStatement();
		statement.executeUpdate(sql);
		
		// 拿到 pw_id
		String selectVersionSql = "select version from password where pw_id="+pw_id+";";
		ResultSet set = (ResultSet) statement.executeQuery(selectVersionSql);
		
		int version = 0;
		if (set.next())
		{
			version = set.getInt(set.getRow());
		}
		DBUtil.close();
		
		return version;
	}
	
}

















