package cn.ihsuzi.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

public class Test
{

	public static void main(String[] args) throws Exception
	{
		Time time = new Time(System.currentTimeMillis());
		System.out.println(time.toString());

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp.toString());
	}

}
