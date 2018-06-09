package cn.ihsuzi.dao;

import cn.ihsuzi.bean.PasswordInformation;

public class PasswordInformationDao
{
	static final String CLASSNAME = "PasswordInformationDao";
	
	public static void insert(PasswordInformation info) throws Exception
	{
		String s = "insert into passwordinformation(pw_id,description,content_one,content_two,content_three,version,title) values(%d,'','%s','%s','',1,'%s');";
		String insertSql = String.format(s, info.getPw_id(), info.getContent_one(),
				info.getContent_two(), info.getTitle());
		
		DBUtil.creatInstance().getStatement().executeUpdate(insertSql);
		DBUtil.close();
	}
}
