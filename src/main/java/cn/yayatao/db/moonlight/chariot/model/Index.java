package cn.yayatao.db.moonlight.chariot.model;

import java.util.List;

public class Index {
	
	/**
	 * 索引所在的表名
	 */
	private String tableName;
	
	
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}


	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	/***
	 * 索引名称
	 */
	private  String name;
	
	
	/***
	 * 参与索引的列
	 */
	private List<String> columns;


	/**
	 * 索引类型   Normal | Unique | Full Text 
	 */
	private String indexType;


	/**
	 * 索引方法 btree hash
	 */
	private String indexMethod;


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
	public List<String> getColumns() {
		return columns;
	}


	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}


	/**
	 * @return the indexType
	 */
	public String getIndexType() {
		return indexType;
	}


	/**
	 * @param indexType the indexType to set
	 */
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}


	/**
	 * @return the indexMethod
	 */
	public String getIndexMethod() {
		return indexMethod;
	}


	/**
	 * @param indexMethod the indexMethod to set
	 */
	public void setIndexMethod(String indexMethod) {
		this.indexMethod = indexMethod;
	}
	

}
