package cn.yayatao.db.moonlight.chariot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yayatao.db.moonlight.chariot.config.DBConnectionConfig;
import cn.yayatao.db.moonlight.chariot.loader.MysqlStructureLoader;
import cn.yayatao.db.moonlight.chariot.model.Database;
import cn.yayatao.db.moonlight.chariot.utils.CloseUtils;

public class DatabaseStructureLoader {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseStructureLoader.class);

	/**
	 * 从数据中加载表结构
	 * 
	 * @param config
	 * @return
	 */
	public Database loadDatabase(DBConnectionConfig config) {
		if ("mysql".equalsIgnoreCase(config.getType())) {
			String url = String.format("jdbc:mysql://%s:%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", config.getHost(), config.getProt());
			String username = config.getUser();
			String password = config.getPassword();
			Connection con = null;
			try {
				con = DriverManager.getConnection(url, username, password);
				return new MysqlStructureLoader().loadDatabaseStructure(con, config.getDatabase());
			} catch (Exception e) {
				LOGGER.error("获取数据库结构出错", e);
			} finally {
				CloseUtils.close(con);
			}
		}
		return null;
	}
	
	
	
	public List<String> loadAllDatabaseNames(DBConnectionConfig config){
		if ("mysql".equalsIgnoreCase(config.getType())) {
			String url = String.format("jdbc:mysql://%s:%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", config.getHost(), config.getProt());
			String username = config.getUser();
			String password = config.getPassword();
			Connection con = null;
			try {
				con = DriverManager.getConnection(url, username, password);
				return new MysqlStructureLoader().loadDatabaseNames(con);
			} catch (Exception e) {
				LOGGER.error("获取数据库结构出错", e);
			} finally {
				CloseUtils.close(con);
			}
		}
		return new ArrayList<String>();
	}
	
}
