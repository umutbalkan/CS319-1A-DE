package logic;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import gui.CreditsMenu;
import gui.HighScoresMenu;
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
	
	
	//private static GameEngine game = new GameEngine();
	private HighScoreManager highscoreManager;
	
	// GUI ELEMENTS
	private Scene main;
	private Stage window;
	private int width;
	private int height;
	private MainMenu layoutMain; 
	private SettingsMenu layoutSettings;
	private CreditsMenu  layoutCredits;
	private HighScoresMenu layoutHighscores;
	private PlayScreen layoutPlay;
	private Background background;
	private Font font,font2;
	private StackPane layout;
	private ObservableList<Node> layoutStack;
	private Image littleShip_img,bomb_img,playBG_img;
	private Font fontButton;
	private MediaPlayer mediaPlayer;
	private String musicFile;
	private Media sound;
	private Label pause_label;
	private Button pauseB;
	private Button stopPlayB;
	private VBox layoutPause;
	private BackgroundImage backgroundimage;
	private Image ship_img;
	private ImageView shipView;	
	private String scores;

	@Override
	public void init() throws Exception{
		System.out.println("Game - initializing ui widgets & read files");
		width = 1280;
		height = 720;
		highscoreManager = HighScoreManager.getInstance();
		scores = highscoreManager.readHighScoreFile();
		
        // load a custom font from a specific location
    	fontButton = Font.loadFont(new FileInputStream(new File("./assets/visitor.ttf")), 32);
        font = Font.loadFont(new FileInputStream(new File("./assets/visitor.ttf")), 56);
        font2 = Font.loadFont(new FileInputStream(new File("./assets/visitor.ttf")), 48);
        
        // create a image 
        FileInputStream img = new FileInputStream("./assets/menuBG.png");
        Image image = new Image(img); 
        // create a background image 
        backgroundimage = new BackgroundImage(image,  
                                         BackgroundRepeat.NO_REPEAT,  
                                         BackgroundRepeat.NO_REPEAT,  
                                         BackgroundPosition.DEFAULT,  
                                         new BackgroundSize(1.0, 1.0, true, true, false, false)); 

        // create Background 
        background = new Background(backgroundimage);    
        
        // image files
        FileInputStream litteship_file = new FileInputStream("./assets/littleship.png");
        littleShip_img = new Image(litteship_file); 
        
        FileInputStream bomb_file = new FileInputStream("./assets/bomb.png");
        bomb_img = new Image(bomb_file); 
        
        FileInputStream playBG_file = new FileInputStream("./assets/playBG.png");
        playBG_img = new Image(playBG_file); 
        
        FileInputStream ship_file = new FileInputStream("./assets/ship.png");
        ship_img = new Image(ship_file);
        
        shipView = new ImageView(ship_img);
		
	}
	
	
//	public static GameEngine getInstance() {
//		return game;
//	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		String musicFile = "./assets/music.mp3"; 
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		window = primaryStage;
		window.setTitle("Planet Defender");
		
		layout = new StackPane(); 
		layoutStack = layout.getChildren();
		
		
		main = new Scene(layout, width,height);
		// Display
		view();
		
		layoutMain = new MainMenu(30, font,font2,fontButton,backgroundimage);
		layoutCredits = new CreditsMenu(30, font, fontButton, backgroundimage);
		layoutSettings = new SettingsMenu(30,font,fontButton,backgroundimage);
		layoutHighscores = new HighScoresMenu(30,scores,font,fontButton,backgroundimage);
		layoutPlay = new PlayScreen(width, height,littleShip_img, bomb_img, playBG_img,shipView);
		layoutPause = new PauseMenu(30,font,fontButton,backgroundimage);
		layoutStack.add(layoutMain);
		
		
		demo();
		
		
		System.out.println(layoutMain.playClicked);
		window.setScene(main);
		window.show();
		music_play(); 
		
	}
	
	
	private void demo() {
		int xmove = 20;
        // control ship
        main.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
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
		System.out.println(layoutMain.playClicked);
	}
	
	public static void main(String args[]){   
		
	    Application.launch(args);    
	
	}
	
	
	public void music_play() {
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
	
	
	private void view() {
		
		
		view_PauseMenu();
		view_PlayScreen();
	}
	
	private void view_map(GraphicsContext g) {

	}
	
	private void view_PlayScreen() {
        
//        // pause event
//        main.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            if(key.getCode()==KeyCode.ESCAPE & layoutStack.get(0) == foolayout) {
//                layoutStack.add(layoutPause);
//                layoutStack.remove(0);
//            }
//            else if(key.getCode()==KeyCode.ESCAPE & layoutStack.get(0) == layoutPause) {
//                layoutStack.add(layoutPlay);
//                layoutStack.remove(0);
//            }
//
//        });     
        
	}
	
	private void view_PauseMenu() {
		pause_label = new Label("PAUSE");
		pause_label.setFont(font);
		pause_label.setTextFill(Color.web("#ffd500"));
		
		
		
		pauseB = new Button("> CONTINUE");
		pauseB.setMnemonicParsing(true);
		pauseB.setFont(fontButton);
		pauseB.setTextFill(Color.web("#b33434"));
		pauseB.setMaxWidth(300);
		pauseB.setWrapText(true);
		pauseB.setPadding(Insets.EMPTY);
		pauseB.setStyle("-fx-background-color: transparent");
		pauseB.setOnMouseEntered(e -> pauseB.setTextFill(Color.web("#ff5e5e")));
		pauseB.setOnMouseExited(e -> pauseB.setTextFill(Color.web("#b33434")));
		pauseB.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutPlay);
                layoutStack.remove(0);
            }
        });
		
		
		stopPlayB = new Button("> EXIT");
		stopPlayB.setMnemonicParsing(true);
		stopPlayB.setFont(fontButton);
		stopPlayB.setTextFill(Color.web("#b33434"));
		stopPlayB.setMaxWidth(300);
		stopPlayB.setWrapText(true);
		stopPlayB.setPadding(Insets.EMPTY);
	    stopPlayB.setStyle("-fx-background-color: transparent");
	    stopPlayB.setOnMouseEntered(e -> stopPlayB.setTextFill(Color.web("#ff5e5e")));
	    stopPlayB.setOnMouseExited(e -> stopPlayB.setTextFill(Color.web("#b33434")));
	    stopPlayB.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutMain);
                layoutStack.remove(0);
            }
        });
	    
	    layoutPause = new VBox(30);
	    layoutPause.setBackground(background);
	    layoutPause.setAlignment(Pos.CENTER);
	    layoutPause.getChildren().addAll(pause_label, stopPlayB,pauseB);
	}
	
}
