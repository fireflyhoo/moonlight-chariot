package cn.yayatao.db.moonlight.chariot.config;

public class DBConnectionConfig {
	/**
	 * 数据库类型
	 */
	private String type;
	
	public DBConnectionConfig() {
		super();
	}


	public DBConnectionConfig(String type, String host, String prot, String user, String password, String database) {
		super();
		this.type = type;
		this.host = host;
		this.prot = prot;
		this.user = user;
		this.password = password;
		this.database = database;
	}


	/**
	 * ip
	 */
	private String host;
	
	/**
	 * 端口
	 */
	private String prot;
	
	/***
	 * 用户
	 */
	private String user;
	
	/**
	 * 密码
	 */
	private String password;
	
	
	/**
	 * 数据库
	 */
	private String database;


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}


	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}


	/**
	 * @return the prot
	 */
	public String getProt() {
		return prot;
	}


	/**
	 * @param prot the prot to set
	 */
	public void setProt(String prot) {
		this.prot = prot;
	}


	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}


	/**
	 * @param database the database to set
	 */
	public void setDatabase(String database) {
		this.database = database;
	}
	
}
