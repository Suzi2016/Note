package cn.ihsuzi.bean;

public class PasswordInfomation
{
    /*
        pwi_id int not null auto_increment primary key,
		pw_id int not null,
		type int not null default 1,
		description varchar(128) not null,
		content_one varchar(128) not null,
		content_two varchar(128) not null,
		content_three varchar(128) not null,
		version int not null,
		title varchar(128) not null
     
     */
	
	private int pwi_id;
	private int pw_id;
	private int version;
	private int type;
	private String description;
	private String content_one;
	private String content_two;
	private String content_three;
	private String title;
	
	public PasswordInfomation()
	{
		super();
	}

	public PasswordInfomation(int pw_id, int version, int type,
			String description, String content_one, String content_two,
			String content_three, String title)
	{
		super();
		this.pw_id = pw_id;
		this.version = version;
		this.type = type;
		this.description = description;
		this.content_one = content_one;
		this.content_two = content_two;
		this.content_three = content_three;
		this.title = title;
	}

	public PasswordInfomation(int pwi_id, int pw_id, int version, int type,
			String description, String content_one, String content_two,
			String content_three, String title)
	{
		super();
		this.pwi_id = pwi_id;
		this.pw_id = pw_id;
		this.version = version;
		this.type = type;
		this.description = description;
		this.content_one = content_one;
		this.content_two = content_two;
		this.content_three = content_three;
		this.title = title;
	}

	public int getPwi_id()
	{
		return pwi_id;
	}

	public void setPwi_id(int pwi_id)
	{
		this.pwi_id = pwi_id;
	}

	public int getPw_id()
	{
		return pw_id;
	}

	public void setPw_id(int pw_id)
	{
		this.pw_id = pw_id;
	}

	public int getVersion()
	{
		return version;
	}

	public void setVersion(int version)
	{
		this.version = version;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getContent_one()
	{
		return content_one;
	}

	public void setContent_one(String content_one)
	{
		this.content_one = content_one;
	}

	public String getContent_two()
	{
		return content_two;
	}

	public void setContent_two(String content_two)
	{
		this.content_two = content_two;
	}

	public String getContent_three()
	{
		return content_three;
	}

	public void setContent_three(String content_three)
	{
		this.content_three = content_three;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
	
	
	
}



















