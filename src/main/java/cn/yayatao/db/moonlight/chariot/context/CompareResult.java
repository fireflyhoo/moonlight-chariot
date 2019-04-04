package cn.yayatao.db.moonlight.chariot.context;

public enum CompareResult {
	/**
	 * 相等
	 */
	EQUAL, 
	
	/***
	 * 不等
	 */
	NOT_EQUAL, 
	
	/***
	 * 左边不存在
	 */
	LEFT_NOT_EXIST, 
	
	/**
	 * 右边不存在
	 */
	RIGHT_NOT_EXIST;
}
