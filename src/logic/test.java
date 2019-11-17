package logic;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition; 
public class test extends Application{

	//private GameEngine game;
	
	public void init() throws Exception {
		//System.out.println("Master initialization.");
		//game.init();
	}
	
	public void stop() throws Exception{
		//System.out.println("Master terminating.");
	}
	
	public void start(Stage primaryStage) throws Exception {
		//game = GameEngine.getInstance();
		//game.start(primaryStage);
		
	}
	
	public static void main(String args[]){   
		
	    Application.launch(GameEngine.class,args);    
	
	}

}
