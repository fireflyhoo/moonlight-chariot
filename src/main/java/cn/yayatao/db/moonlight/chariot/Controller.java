package cn.yayatao.db.moonlight.chariot;

import java.util.List;

import cn.yayatao.db.moonlight.chariot.config.DBConnectionConfig;
import cn.yayatao.db.moonlight.chariot.model.Database;
import cn.yayatao.db.moonlight.chariot.output.Displayer;
import cn.yayatao.db.moonlight.chariot.output.HTMLDisplayer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

/**
 * GUI 控制器
 * 
 * @author Huyahui
 *
 */
public class Controller {

	DatabaseStructureLoader databaseStructureLoader = new DatabaseStructureLoader();

	@FXML
	private TextField left_ip;

	@FXML
	private TextField left_port;

	@FXML
	private ChoiceBox left_type;

	@FXML
	private TextField left_user;

	@FXML
	private TextField left_password;

	@FXML
	private ChoiceBox left_database;

	@FXML
	private TextField right_ip;

	@FXML
	private TextField right_port;

	@FXML
	private ChoiceBox right_type;

	@FXML
	private TextField right_user;

	@FXML
	private TextField right_password;

	@FXML
	private ChoiceBox right_database;

	@FXML
	private WebView web_view;

	@FXML
	public void doConnectionLeft() {
		DBConnectionConfig conf = new DBConnectionConfig(String.valueOf(left_type.getValue()).toLowerCase(),
				left_ip.getText(), left_port.getText(), left_user.getText(), left_password.getText(),
				null);
		List<String> names = databaseStructureLoader.loadAllDatabaseNames(conf);
		left_database.setItems(FXCollections.observableArrayList(names.toArray(new String[0])));
	}

	@FXML
	public void doConnectionRight() {
		DBConnectionConfig conf = new DBConnectionConfig(String.valueOf(right_type.getValue()).toLowerCase(),
				right_ip.getText(), right_port.getText(), right_user.getText(), right_password.getText(),
				null);
		List<String> names = databaseStructureLoader.loadAllDatabaseNames(conf);
		right_database.setItems(FXCollections.observableArrayList(names.toArray(new String[0])));
	}

	@FXML
	public void doCompare() {

		DatabaseComparator comparator = new DatabaseComparator();
		Displayer displayer = new HTMLDisplayer();
		comparator.setDisplayer(displayer);
		long start = System.currentTimeMillis();
		Database left_db = databaseStructureLoader.loadDatabase(new DBConnectionConfig(
				String.valueOf(left_type.getValue()).toLowerCase(), left_ip.getText(), left_port.getText(),
				left_user.getText(), left_password.getText(), String.valueOf(left_database.getValue())));
		Database rigth_db = databaseStructureLoader.loadDatabase(new DBConnectionConfig(
				String.valueOf(right_type.getValue()).toLowerCase(), right_ip.getText(), right_port.getText(),
				right_user.getText(), right_password.getText(), String.valueOf(right_database.getValue())));
		System.out.println(String.format("加载表结构数据成功.... 耗时:%s ms", System.currentTimeMillis() - start));
		// 加载db结构
		start = System.currentTimeMillis();
		comparator.compare(left_db, rigth_db);
		System.out.println(String.format("比对数据库结构成功! 耗时:%s ms", System.currentTimeMillis() - start));
		web_view.getEngine().loadContent(displayer.getContent().toString());

	}

}
