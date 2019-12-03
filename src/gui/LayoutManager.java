package gui;

import java.io.File;
import java.io.FileInputStream;

import io.HighScoreManager;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LayoutManager extends StackPane{

	private static LayoutManager instance;
	
	
	private HighScoreManager highscoreManager;
	private ObservableList<Node> layoutStack;
	private MainMenu layoutMain;
	private CreditsMenu layoutCredits;
	private SettingsMenu layoutSettings;
	private HighScoresMenu layoutHighscores;
	private PlayScreen layoutPlay;
	private PauseMenu layoutPause;
	
	private String scores;
	private Image littleShip_img,bomb_img,playBG_img;
	private Font font,font2,fontButton;
	private BackgroundImage backgroundimage;
	private Image ship_img;
	private ImageView shipView;	
	private Background background;
	private int width, height;
	
	private LayoutManager() {
		super();
		
		// get singleton
		highscoreManager = HighScoreManager.getInstance();
		scores = highscoreManager.readHighScoreFile();
		
		layoutStack = this.getChildren();
		
		// init widgets, throws exception when file read fails.
		try {
			init_widgets();
		} catch (Exception e) {
			e.printStackTrace();
		}
       
		// init menus and add main menu to visible screen
		init_layouts();
		layoutStack.add(layoutMain);
	}
	
	public static LayoutManager getInstance() {
		if(instance == null) {
			instance = new LayoutManager();
		}
			
		return instance;
	}
	
	public ObservableList<Node> getStack(){
		if(layoutStack == null) {
			getInstance();
		}
		return layoutStack;
	}
	
	private void init_layouts() {
		layoutMain = new MainMenu(30, font,font2,fontButton,backgroundimage);
		layoutCredits = new CreditsMenu(30, font, fontButton, backgroundimage);
		layoutSettings = new SettingsMenu(30,font,fontButton,backgroundimage);
		layoutHighscores = new HighScoresMenu(30,scores,font,fontButton,backgroundimage);
		layoutPlay = new PlayScreen(width, height,littleShip_img, bomb_img, playBG_img,shipView);
		layoutPause = new PauseMenu(30,font,fontButton,backgroundimage);
		
	}

	private void init_widgets() throws Exception{
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


	
}
