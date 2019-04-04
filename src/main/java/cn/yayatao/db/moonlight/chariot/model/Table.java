package cn.yayatao.db.moonlight.chariot.model;

import java.util.List;

public class Table {
	
	/**
	 * 表名
	 */
	private String name;

	/**
	 * 字段
	 */
	private List<Column> columns;
	
	/**
	 * 索引
	 */
	private List<Index> indexs;
	
	
	/**
	 * 表引擎
	 */
	private String  engine;
	
	


	/**
	 * 表编码
	 */
	private String charset;


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
	 * @return the columns
	 */
	public List<Column> getColumns() {
		return columns;
	}


	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}


	/**
	 * @return the indexs
	 */
	public List<Index> getIndexs() {
		return indexs;
	}


	/**
	 * @param indexs the indexs to set
	 */
	public void setIndexs(List<Index> indexs) {
		this.indexs = indexs;
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
	

	public String getEngine() {
		return engine;
	}


	public void setEngine(String engine) {
		this.engine = engine;
	}
}
