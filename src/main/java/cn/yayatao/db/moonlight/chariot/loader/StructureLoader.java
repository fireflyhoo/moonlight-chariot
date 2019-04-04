package cn.yayatao.db.moonlight.chariot.loader;

import java.sql.Connection;
import java.util.List;

import cn.yayatao.db.moonlight.chariot.model.Database;

public interface StructureLoader  {
	
	/**
	 * 获取数据库结构
	 * @param connection
	 * @param databaseName
	 * @return
	 * @throws Exception
	 */
	public Database loadDatabaseStructure(Connection connection,String databaseName)throws Exception;
	
	
	/**
	 * 通过连接获取数据库名称
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	public List<String> loadDatabaseNames(Connection connection) throws Exception ;

}
