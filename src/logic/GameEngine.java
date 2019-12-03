package logic;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import gui.CreditsMenu;
import gui.HighScoresMenu;
import gui.LayoutManager;
import gui.MainMenu;
import gui.PauseMenu;
import gui.PlayScreen;
import gui.SettingsMenu;
import io.HighScoreManager;
import io.Score;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;


public class GameEngine extends Application{
	
	
	private HighScoreManager highscoreManager;
	private LayoutManager layout;
	
	private Scene main;
	private Stage window;
	private int width;
	private int height;
	private MediaPlayer mediaPlayer;
	private ImageView shipView;	
	
	@Override
	public void init() throws Exception{
		System.out.println("Game - initializing ui widgets, layout & read files");
		width = 1280;
		height = 720;
		
		// get singleton
		layout = LayoutManager.getInstance();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Planet Defender");	
		main = new Scene(layout, width,height);
		
		//demo();
		
		
		window.setScene(main);
		window.show();
		music_play(); 
		
	}
	
	
	private void demo() {
		int xmove = 20;
        // control ship
        main.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
        	//if(layout.getStack().get(0) == )
        	System.out.println(shipView.getTranslateX() + ", " + shipView.getTranslateY());
            if(key.getCode()==KeyCode.RIGHT) {
            	shipView.setScaleX(1);
            	shipView.setTranslateX(shipView.getTranslateX()+xmove);
            }
            if(key.getCode()==KeyCode.LEFT) {
            	shipView.setScaleX(-1);
            	shipView.setTranslateX(shipView.getTranslateX()-xmove);
            }
            if(key.getCode()==KeyCode.UP) {
            	shipView.setTranslateY(shipView.getTranslateY()-xmove);
            }
            if(key.getCode()==KeyCode.DOWN) {
            	shipView.setTranslateY(shipView.getTranslateY()+xmove);
            }
        });
	
	}


	public void stop() throws Exception{
		System.out.println("Game - Terminating.");
	}	
	
	public void music_play() {
		
		String musicFile = "./assets/music.mp3"; 
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	    Status status = mediaPlayer.getStatus();
	    if (status == Status.UNKNOWN || status == Status.HALTED)
	    {
	        System.out.println("Player is in a bad or unknown state, can't play.");
	        return;
	    }
	    
	    if (status == Status.PAUSED || status == Status.STOPPED || status == Status.READY)
	    {
	        mediaPlayer.play();
	    }
	}
	
	public static void main(String args[]){   
		
	    Application.launch(args);    
	
	}
}
