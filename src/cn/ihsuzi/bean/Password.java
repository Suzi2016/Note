package cn.ihsuzi.bean;

public class Password
{
	/*
	 *  pw_id int not null auto_increment primary key,
		user_id int not null,
		type int not null,
		version int not null default 0,
		pw_createtime datetime not null,
		pw_updatetime datetime not null,
		pw_isshow int not null default 1,
		pw_isdelete int not null default 0,
		pw_degree int not null default 0
	 */
	
	private int pw_id;
	private int user_id;
	private int type;
	private int version;
	private String pw_createtime;
	private String pw_updatetime;
	private int pw_isshow;
	private int pw_isdelete;  //是否被删除，用作回收站功能
	private int pw_degree;    //笔记的等级，用于表示重要性
	
	public Password()
	{
		super();
	}

	public Password(int user_id, int type, int version, String pw_createtime,
			String pw_updatetime, int pw_isshow, int pw_isdelete, int pw_degree)
	{
		super();
		this.user_id = user_id;
		this.type = type;
		this.version = version;
		this.pw_createtime = pw_createtime;
		this.pw_updatetime = pw_updatetime;
		this.pw_isshow = pw_isshow;
		this.pw_isdelete = pw_isdelete;
		this.pw_degree = pw_degree;
	}

	public Password(int pw_id, int user_id, int type, int version,
			String pw_createtime, String pw_updatetime, int pw_isshow,
			int pw_isdelete, int pw_degree)
	{
		super();
		this.pw_id = pw_id;
		this.user_id = user_id;
		this.type = type;
		this.version = version;
		this.pw_createtime = pw_createtime;
		this.pw_updatetime = pw_updatetime;
		this.pw_isshow = pw_isshow;
		this.pw_isdelete = pw_isdelete;
		this.pw_degree = pw_degree;
	}

	public int getPw_id()
	{
		return pw_id;
	}

	public void setPw_id(int pw_id)
	{
		this.pw_id = pw_id;
	}

	public int getUser_id()
	{
		return user_id;
	}

	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int getVersion()
	{
		return version;
	}

	public void setVersion(int version)
	{
		this.version = version;
	}

	public String getPw_createtime()
	{
		return pw_createtime;
	}

	public void setPw_createtime(String pw_createtime)
	{
		this.pw_createtime = pw_createtime;
	}

	public String getPw_updatetime()
	{
		return pw_updatetime;
	}

	public void setPw_updatetime(String pw_updatetime)
	{
		this.pw_updatetime = pw_updatetime;
	}

	public int getPw_isshow()
	{
		return pw_isshow;
	}

	public void setPw_isshow(int pw_isshow)
	{
		this.pw_isshow = pw_isshow;
	}

	public int getPw_isdelete()
	{
		return pw_isdelete;
	}

	public void setPw_isdelete(int pw_isdelete)
	{
		this.pw_isdelete = pw_isdelete;
	}

	public int getPw_degree()
	{
		return pw_degree;
	}

	public void setPw_degree(int pw_degree)
	{
		this.pw_degree = pw_degree;
	}
	
	
	
	
}













