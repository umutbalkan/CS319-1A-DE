package logic;
	
import java.io.File;

import logic.GameEngine;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class test extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GameEngine manager = new GameEngine();
			primaryStage = manager.getStage();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
