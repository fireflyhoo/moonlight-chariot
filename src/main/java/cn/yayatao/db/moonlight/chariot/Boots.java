package cn.yayatao.db.moonlight.chariot;

import cn.yayatao.db.moonlight.chariot.config.DBConnectionConfig;
import cn.yayatao.db.moonlight.chariot.model.Database;
import cn.yayatao.db.moonlight.chariot.output.Displayer;
import cn.yayatao.db.moonlight.chariot.output.HTMLDisplayer;

/***
 * 比较器启动
 * @author Administrator
 *
 */
public class Boots {
	public static void main(String[] args) {
		DatabaseStructureLoader databaseStructureLoader = new DatabaseStructureLoader();
	
		DatabaseComparator comparator = new DatabaseComparator();
		Displayer displayer = new HTMLDisplayer();
		comparator.setDisplayer(displayer);
		long start = System.currentTimeMillis();
		Database left_db = databaseStructureLoader.loadDatabase(new DBConnectionConfig("mysql", "54.86.39.142", "3306", "testdb_user", "klF3r2AbOWaBE", "glb_goods"));
		Database rigth_db = databaseStructureLoader.loadDatabase(new DBConnectionConfig("mysql", "54.86.39.142", "3306", "testdb_user", "klF3r2AbOWaBE", "tg_goods"));
		System.out.println(String.format("加载表结构数据成功.... 耗时:%s ms",System.currentTimeMillis() - start));
		//加载db结构
		start = System.currentTimeMillis();
		comparator.compare(left_db, rigth_db);
		System.out.println(String.format("比对数据库结构成功! 耗时:%s ms",System.currentTimeMillis() - start)); 
		
		System.out.println(displayer.getContent().toString());
		
	}
}
