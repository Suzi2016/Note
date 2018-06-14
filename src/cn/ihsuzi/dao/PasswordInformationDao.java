package cn.ihsuzi.dao;

import cn.ihsuzi.bean.PasswordInformation;

public class PasswordInformationDao
{
	static final String CLASSNAME = "PasswordInformationDao";
	
	public static void insert(PasswordInformation info) throws Exception
	{
		String s = "insert into passwordInformation(pw_id,description,content_one,content_two,content_three,version,title) values(%d,'','%s','%s','',%d,'%s');";
		String insertSql = String.format(s, info.getPw_id(), info.getContent_one(),
				info.getContent_two(), info.getVersion(),info.getTitle());
		
		DBUtil.creatInstance().getStatement().executeUpdate(insertSql);
		DBUtil.close();
	}
	
	
}
