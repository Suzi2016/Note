package cn.ihsuzi.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Test
{

	public static void main(String[] args) throws Exception
	{
		Properties properties = new Properties();
		InputStream input = new FileInputStream("jdbc.properties");
		properties.load(input);
		System.out.println(properties.getProperty("url"));

	}

}
