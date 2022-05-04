package cn.yayatao.db.moonlight.chariot.loader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.yayatao.db.moonlight.chariot.model.Column;
import cn.yayatao.db.moonlight.chariot.model.Database;
import cn.yayatao.db.moonlight.chariot.model.Index;
import cn.yayatao.db.moonlight.chariot.model.Table;
import cn.yayatao.db.moonlight.chariot.model.View;
import cn.yayatao.db.moonlight.chariot.utils.CloseUtils;

public class MysqlStructureLoader implements StructureLoader {

	@Override
	public Database loadDatabaseStructure(Connection connection, String databaseName) throws Exception {

		Database database = new Database(databaseName);

		List<View> views = loadViews(connection, databaseName);
		database.setViews(views);
		List<Table> tables = loadTables(connection, databaseName);

		database.setTables(tables);
		return database;
	}

	private List<Table> loadTables(Connection connection, String databaseName) throws Exception {
		String tableSql = "SELECT TABLE_NAME as `name` , `ENGINE` as `engine`,TABLE_COLLATION  as `charset`  from information_schema.`TABLES` where TABLE_SCHEMA  = ?  and TABLE_TYPE = 'BASE TABLE'";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<String, Table> tableMappper = new HashMap<>();
		List<Table> tables = new ArrayList<>();
		// 加载表结构
		try {
			preparedStatement = connection.prepareStatement(tableSql);
			preparedStatement.setString(1, databaseName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Table table = new Table();
				table.setName(resultSet.getString("name"));
				table.setCharset(resultSet.getString("charset"));
				table.setEngine(resultSet.getString("engine"));
				table.setColumns(new ArrayList<>());
				table.setIndexs(new ArrayList<>());
				tableMappper.put(table.getName(), table);
				tables.add(table);
			}
		} finally {
			CloseUtils.close(preparedStatement);
			CloseUtils.close(resultSet);
		}

		String colSql = "SELECT TABLE_NAME as `table_name`,COLUMN_NAME  as `name`,DATA_TYPE as `type` ,IFNULL(CHARACTER_MAXIMUM_LENGTH,NUMERIC_PRECISION) as length ,NUMERIC_SCALE as scale ,COLUMN_COMMENT as `comment` ,COLUMN_DEFAULT as `default_value` ,IS_NULLABLE as `is_nullable`,EXTRA as 'extra' from information_schema.`COLUMNS` where TABLE_SCHEMA  = ? ";
		preparedStatement = null;
		resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(colSql);
			preparedStatement.setString(1, databaseName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String tableName = resultSet.getString("table_name");
				Table table = tableMappper.get(tableName);
				if (table != null) {
					Column column = new Column();
					column.setName(resultSet.getString("name"));
					column.setAutoIncrement("auto_increment".equals(resultSet.getString("extra")));
					column.setComment(resultSet.getString("comment"));
					column.setDefaultValue(resultSet.getString("default_value"));
					column.setNotNull("NO".equals(resultSet.getString("is_nullable")));
					column.setType(resultSet.getString("type"));
					column.setLength(resultSet.getString("length")); //数值或者字符串长度
					column.setScale(resultSet.getInt("scale"));	//数值小数点精度				 
					table.getColumns().add(column);
				}
			}
		} finally {
			CloseUtils.close(preparedStatement);
			CloseUtils.close(resultSet);
		}

		// 获取索引
		String indexSQL = "SELECT  TABLE_SCHEMA, TABLE_NAME, NON_UNIQUE,INDEX_NAME,SEQ_IN_INDEX,COLUMN_NAME,INDEX_TYPE,CONCAT(COMMENT,INDEX_COMMENT)  INDEX_COMMENT   FROM  INFORMATION_SCHEMA.STATISTICS  WHERE  TABLE_SCHEMA = ?   ORDER BY TABLE_SCHEMA,TABLE_NAME,INDEX_NAME,SEQ_IN_INDEX ";
		preparedStatement = null;
		resultSet = null;
		Map<String, Index> indexs = new TreeMap<>();
		try {
			preparedStatement = connection.prepareStatement(indexSQL);
			preparedStatement.setString(1, databaseName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Index index = indexs.get(resultSet.getString("INDEX_NAME"));
				if (index == null) {
					index = new Index();
					index.setName(resultSet.getString("INDEX_NAME"));
					index.setColumns(new ArrayList<>());
					index.setIndexMethod(resultSet.getString("INDEX_TYPE"));
					index.setIndexType(resultSet.getString("NON_UNIQUE"));
					index.getColumns().add(resultSet.getString("COLUMN_NAME"));
					index.setTableName(resultSet.getString("TABLE_SCHEMA"));
					indexs.put(index.getName(), index);
				} else {
					index.getColumns().add(resultSet.getString("COLUMN_NAME"));
				}
			}
			for (Index index : indexs.values()) {
				Table table = tableMappper.get(index.getTableName());
				if (table != null) {
					table.getIndexs().add(index);
				}
			}
		} finally {
			CloseUtils.close(preparedStatement);
			CloseUtils.close(resultSet);
		}

		return tables;
	}

	private List<View> loadViews(Connection connection, String databaseName) throws Exception {
		List<View> views = null;
		String viewSql = "select TABLE_NAME as name,VIEW_DEFINITION as `sql`, 0 as `view_type` from  information_schema.views where TABLE_SCHEMA = ? ";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(viewSql);
			preparedStatement.setString(1, databaseName);
			resultSet = preparedStatement.executeQuery();
			views = new ArrayList<>();
			while (resultSet.next()) {
				View v = new View();
				v.setName(resultSet.getString("name"));
				v.setSql(resultSet.getString("sql"));
				v.setViewType(resultSet.getInt("view_type"));
				views.add(v);
			}
		} finally {
			CloseUtils.close(resultSet);
			CloseUtils.close(preparedStatement);
		}
		return views;
	}

	
	@Override
	public List<String> loadDatabaseNames(Connection connection) throws Exception {
		String databaseSql = "SHOW DATABASES";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		List<String> database = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(databaseSql);
			
			resultSet =  preparedStatement.executeQuery();
			while (resultSet.next()) {
				database.add(resultSet.getString(1));
			}
		}finally {
			CloseUtils.close(resultSet);
			CloseUtils.close(preparedStatement);
		}	
		return database;
	}

}
