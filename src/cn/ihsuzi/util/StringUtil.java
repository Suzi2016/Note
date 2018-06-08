package cn.ihsuzi.util;

public class StringUtil
{
	/**
	 * 判断字符串是否有意义，不仅判断字符串是否为空，还会判断是不是由空格等组成，如果是的，则判定字符串无意义，为空，返回 true
	 * @param s
	 * @return
	 */
    public static boolean isEmpty(String s)
    {
    	if (s.isEmpty())
		{
			return true;
		}
    	
    	if (s.replace(" ", "").isEmpty())
    	{
    		return true;
    	}
    	
    	return false;
    }
}
