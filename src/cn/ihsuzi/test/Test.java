package cn.ihsuzi.test;

import java.sql.SQLException;
import java.util.List;
import cn.ihsuzi.bean.PasswordSet;
import cn.ihsuzi.dao.PasswordDao;

public class Test
{

	public static void main(String[] args) throws Exception
	{
//		Time time = new Time(System.currentTimeMillis());
//		System.out.println(time.toString());
//
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		System.out.println(timestamp.toString());
		
//		testDBGetPasswordSet();
		
//		System.out.println(PasswordDao.getUserId(14, 7));
//		System.out.println(!PasswordDao.isMatch(14, 7, "Note1", "Suzi", "szw123"));
		System.out.println(PasswordDao.updateVersionAndUpdateTime(14));
	}
	
	private static void testDBGetPasswordSet() throws SQLException, Exception
	{
		int user_id =1;
		List<PasswordSet> list = PasswordDao.getPasswordSets(user_id);
		for (int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i).toString());
		}
	}
	
	

}
