package cn.yayatao.db.moonlight.chariot;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.stage.Stage;

public class GUI extends Application {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(GUI.class.getClassLoader().getResource("GUI.fxml"));

		Scene scene = new Scene(root);
		
		ChoiceBox left_type = (ChoiceBox)root.lookup("#left_type");
		left_type.setItems(FXCollections.observableArrayList(
			    "Mysql", 
			    new Separator(), "Oracle","PostgreSQL","SQLServer")
			);
		ChoiceBox right_type = (ChoiceBox)root.lookup("#right_type");
		right_type.setItems(FXCollections.observableArrayList(
			    "Mysql", 
			    new Separator(), "Oracle","PostgreSQL","SQLServer")
			);
		stage.setScene(scene);
		stage.setResizable(false);  
		stage.setTitle("moonlight-chariot");
		stage.show();
	}

	

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(GUI.class, args);
	}

}
