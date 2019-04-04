package cn.yayatao.db.moonlight.chariot.model;

/********
 * 列结构信息
 * @author Huyahui
 *
 */
public class Column {
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Column [name=" + name + ", type=" + type + ", length=" + length + ", scale=" + scale + ", notNull="
				+ notNull + ", defaultValue=" + defaultValue + ", comment=" + comment + ", autoIncrement="
				+ autoIncrement + ", primaryKey=" + primaryKey + "]";
	}

	/**
	 * 列名
	 */
	private String name;

	/**
	 * 字段类型
	 */
	private String type;

	/**
	 * 长度
	 */
	private String length;
	
	/**
	 * 小数点精度
	 */
	private int scale;
	
	

	/**
	 * 是否要求不为空
	 */
	private boolean notNull;
	
	
	/**
	 * 默认值
	 */
	private String defaultValue;
	
	
	/**
	 * 注释
	 */
	private String comment;
	
	
	/**
	 * 是否递增
	 */
	private boolean autoIncrement;
	
	
	
	/**
	 * 主健
	 */
	private boolean primaryKey;
	
	

	
	/**
	 * @return the primaryKey
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the autoIncrement
	 */
	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	/**
	 * @param autoIncrement the autoIncrement to set
	 */
	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	/**
	 * @return the notNull
	 */
	public boolean isNotNull() {
		return notNull;
	}

	/**
	 * @param notNull the notNull to set
	 */
	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	
	
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * @return the scale
	 */
	public int getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}


}
