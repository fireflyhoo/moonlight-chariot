package cn.yayatao.db.moonlight.chariot.context;

import cn.yayatao.db.moonlight.chariot.model.Database;

public class MatchedDatabaseItme   {
	
	/**
	 * 左边数据库
	 */
	Database left;
	
	/**
	 * 右边数据库
	 */
	Database right;

	
	
	/**
	 * @return the left
	 */
	public Database getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Database left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Database getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(Database right) {
		this.right = right;
	}
	
	
}
