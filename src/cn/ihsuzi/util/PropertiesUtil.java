package cn.ihsuzi.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil
{
	/**
	 * 返回一个指定的Properties对象
	 * @param url Properties文件的路径
	 * @return
	 * @throws Exception
	 */
	public static Properties loadProperties(String url) throws Exception
	{
		Properties properties = new Properties();
		InputStream input = new FileInputStream(url);
		properties.load(input);
		
		return properties;
	}
}
