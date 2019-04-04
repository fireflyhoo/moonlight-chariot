package cn.yayatao.db.moonlight.chariot.context;

import java.util.List;

public class MatchedDatabaseContext {
	
	private MatchedDatabaseItme database;
	
	private List<MatchedTableItme> tables;
	
	private List<MatchedViewItme> views;

	/**
	 * @return the views
	 */
	public List<MatchedViewItme> getViews() {
		return views;
	}

	/**
	 * @param views the views to set
	 */
	public void setViews(List<MatchedViewItme> views) {
		this.views = views;
	}

	/**
	 * @return the database
	 */
	public MatchedDatabaseItme getDatabase() {
		return database;
	}

	/**
	 * @param database the database to set
	 */
	public void setDatabase(MatchedDatabaseItme database) {
		this.database = database;
	}

	/**
	 * @return the tables
	 */
	public List<MatchedTableItme> getTables() {
		return tables;
	}

	/**
	 * @param tables the tables to set
	 */
	public void setTables(List<MatchedTableItme> tables) {
		this.tables = tables;
	}


	
	
}
