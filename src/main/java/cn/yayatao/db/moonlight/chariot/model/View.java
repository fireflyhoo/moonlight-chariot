package cn.yayatao.db.moonlight.chariot.model;

public class View {
	
	/**
	 * 视图名称
	 */
	private String name;
	
	
	/**
	 * 创建视图的SQL
	 */
	private String sql;

	/**
	 * 视图类型 普通0,物化视图1
	 */
	private int viewType;


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
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * @return the viewType
	 */
	public int getViewType() {
		return viewType;
	}

	/**
	 * @param viewType the viewType to set
	 */
	public void setViewType(int viewType) {
		this.viewType = viewType;
	} 
}
