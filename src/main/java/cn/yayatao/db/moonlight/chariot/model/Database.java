package cn.yayatao.db.moonlight.chariot.model;

import java.util.List;

/**
 * 数据库结构
 * @author Huyahui
 *
 */
public class Database {
	
	/**
	 * 数据库名称
	 */
	private String name;
	
	
	/**
	 * 数据库编码
	 */
	private String charset;	
	
	
	/**
	 * 数据引擎类型
	 */
	private String dataEngine;
	
	
	public Database(String name) {
		super();
		this.name = name;
	}
	
	public Database(){};

	/**
	 * 表结构 
	 */
	private List<Table> tables;
	
	/**
	 * 视图
	 */
	private List<View> views;

	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	

	/**
	 * @return the dataEngine
	 */
	public String getDataEngine() {
		return dataEngine;
	}

	/**
	 * @param dataEngine the dataEngine to set
	 */
	public void setDataEngine(String dataEngine) {
		this.dataEngine = dataEngine;
	}

	/**
	 * @return the tables
	 */
	public List<Table> getTables() {
		return tables;
	}

	/**
	 * @param tables the tables to set
	 */
	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	/**
	 * @return the views
	 */
	public List<View> getViews() {
		return views;
	}

	/**
	 * @param views the views to set
	 */
	public void setViews(List<View> views) {
		this.views = views;
	}

	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * @param charset the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}
}
