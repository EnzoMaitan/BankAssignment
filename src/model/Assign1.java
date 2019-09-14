package model;
/*
 * Name:	Enzo Maitan Favaro
 * Student ID:	40941046
 * Couse & Section:	CST8132 300/301
 * Assignment: Lab 9 
 * Professor: Istiaque Shahriar
 * Date: 2019-04-19
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Assign1 extends Application{
	
	@Override
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
