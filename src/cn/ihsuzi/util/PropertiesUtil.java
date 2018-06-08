package cn.ihsuzi.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil
{
	/**
	 * 返回一个指定的Properties对象
	 * @param filename Properties文件的路径
	 * @return
	 * @throws Exception
	 */
	public static Properties loadProperties(String filename) throws Exception
	{
		Properties properties = new Properties();
		InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
		properties.load(input);
		
		return properties;
	}
}
