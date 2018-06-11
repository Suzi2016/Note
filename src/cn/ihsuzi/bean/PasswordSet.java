package cn.ihsuzi.bean;

/**
 * 用于封装查询结果集
 * @author Suzi
 *
 */
public class PasswordSet
{
    private int pw_id;
    private int pwi_id;
    private String create_time;
    private String update_time;
    private String title;
    private String account;
    private String password;
    
    public PasswordSet(){}

	public int getPw_id()
	{
		return pw_id;
	}

	public void setPw_id(int pw_id)
	{
		this.pw_id = pw_id;
	}

	public int getPwi_id()
	{
		return pwi_id;
	}

	public void setPwi_id(int pwi_id)
	{
		this.pwi_id = pwi_id;
	}

	public String getCreate_time()
	{
		return create_time;
	}

	public void setCreate_time(String create_time)
	{
		this.create_time = create_time;
	}

	public String getUpdate_time()
	{
		return update_time;
	}

	public void setUpdate_time(String update_time)
	{
		this.update_time = update_time;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
    
    public String toString()
    {
    	return "pw_id:"+pw_id
    			+"\npwi_id:"+pwi_id
    			+"\ncreate_time:"+create_time
    			+"\nupdate_time:"+update_time
    			+"\naccount:"+account
    			+"\npassword:"+password
    			+"\ntitle:"+title
    			+"\n";
    }
}
