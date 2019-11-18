package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import io.HighScoreManager;
import io.Score;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
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
	private int canvas_w, canvas_h;
	private int minicanvas_w_left, minicanvas_w_middle, minicanvas_w_right, minicanvas_h;
	private Button playB, settingsB, creditsB, highscoresB, quitB,backB, backB1, backB2;
	private Label defender_label, cred_label, sett_label, high_label;
	private VBox layoutMain, layoutSettings, layoutCredits, layoutHighscores;
	private VBox layoutPlay;
	private HBox topHBox;
	private Canvas canvas;
	private GraphicsContext g;
	private GraphicsContext mini_g_left, mini_g_middle, mini_g_right;
	private Canvas minicanvas_left;
	private Canvas minicanvas_middle;
	private Canvas minicanvas_right;
	private Background background;
	private Font font,font2;
	private StackPane layout;
	private ObservableList<Node> layoutStack;
	private Label planet_label;
	private Image littleShip_img,bomb_img,playBG_img;
	private String scores;
	private Label scores_label;
	private Label credNames_label;
	private Font fontButton;
	private String IDLE_BUTTON_STYLE,HOVERED_BUTTON_STYLE;
	private MediaPlayer mediaPlayer;
	private String musicFile;
	private Media sound;
	private Label pause_label;
	private Button pauseB;
	private Button stopPlayB;
	private VBox layoutPause;

	private Image ship_img;

	private StackPane botStackPane;

	private Group root;

	private ImageView shipView;
	
	@Override
	public void init() throws Exception{
		System.out.println("Game - initializing ui widgets & layouts");
		highscoreManager = HighScoreManager.getInstance();
		scores = highscoreManager.readHighScoreFile();
		IDLE_BUTTON_STYLE = "-fx-text-fill: #b33434;";
		HOVERED_BUTTON_STYLE = "-fx-text-fill: #ff5e5e;";
		setPrimitiveAttributes();
		
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
		
		
		// Display
		view();
		layoutStack.add(layoutMain);
		window.setScene(main);
		window.show();
		music_play(); 
		
	}
	
	
	public void stop() throws Exception{
		System.out.println("Game - Terminating.");
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
	
	private void setPrimitiveAttributes() {
		width = 1280;
		height = 720;
		minicanvas_w_left = width/4;
		minicanvas_w_middle = width/2;
		minicanvas_w_right = width/4;
		minicanvas_h = 120;
		canvas_w = width;
		canvas_h = height - minicanvas_h;
	}
	
	
	private void view() {
		
	    try { 
	        // load a custom font from a specific location
	    	fontButton = Font.loadFont(new FileInputStream(new File("./assets/visitor.ttf")), 32);
	        font = Font.loadFont(new FileInputStream(new File("./assets/visitor.ttf")), 56);
	        font2 = Font.loadFont(new FileInputStream(new File("./assets/visitor.ttf")), 48);
	        // create a image 
	        FileInputStream img = new FileInputStream("./assets/menuBG.png");
	        Image image = new Image(img); 
	        // create a background image 
            BackgroundImage backgroundimage = new BackgroundImage(image,  
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
            
	      } catch (FileNotFoundException e) {
	        e.printStackTrace();
	      }
		
		view_PauseMenu();
        view_MainMenu();
		view_CreditsMenu();
		view_SettingsMenu();
		view_HighScoresMenu();
		view_PlayScreen();
	}
	
	private void view_map(GraphicsContext g) {

	}
	
	private void view_PlayScreen() {
		// create a canvas 
        canvas = new Canvas(canvas_w, canvas_h);
        minicanvas_left = new Canvas(minicanvas_w_left, minicanvas_h);
        minicanvas_middle = new Canvas(minicanvas_w_middle, minicanvas_h);
        minicanvas_right = new Canvas(minicanvas_w_right, minicanvas_h);
        
        
		
        // set GraphicsContext object
        g = canvas.getGraphicsContext2D();
        mini_g_left = minicanvas_left.getGraphicsContext2D();
        mini_g_middle = minicanvas_middle.getGraphicsContext2D();
        mini_g_right = minicanvas_right.getGraphicsContext2D();
        
       
        
        // separator
//        separator_Hline = new Line(0, minicanvas_h-4, width, minicanvas_h-4);
//        separator_Hline.setStroke(Color.GRAY);
//        separator_Hline.setStrokeWidth(4);
        
//        separator_Vline1 = new Line(minicanvas_w_left, 0,minicanvas_w_left , minicanvas_h-4);
//        separator_Vline1.setStroke(Color.GRAY);
//        separator_Vline1.setStrokeWidth(4);
//        
//        separator_Vline2 = new Line(minicanvas_w_middle+minicanvas_w_left, 0,minicanvas_w_middle+minicanvas_w_left, minicanvas_h-4);
//        separator_Vline2.setStroke(Color.GRAY);
//        separator_Vline2.setStrokeWidth(4);
        
        
        // set colors for graphics contexts for all canvas
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, canvas_w, canvas_h);
        
        mini_g_left.setFill(Color.BLACK);
        mini_g_left.fillRect(0, 0, minicanvas_w_left, minicanvas_h);
        
        
        mini_g_right.setFill(Color.BLACK);
        mini_g_right.fillRect(0, 0, minicanvas_w_right, minicanvas_h);
        
        
        mini_g_middle.setFill(Color.BLACK);
        mini_g_middle.fillRect(0, 0, minicanvas_w_middle, minicanvas_h);
        mini_g_middle.setStroke(Color.GRAY);
        mini_g_middle.setLineWidth(4);
        mini_g_middle.strokeLine(0, 0, 0, minicanvas_h);
        mini_g_middle.strokeLine(minicanvas_w_middle, 0, minicanvas_w_middle, minicanvas_h);

        

        
        // draw Graphics
        mini_g_left.drawImage(littleShip_img, 20, 20);
        mini_g_left.drawImage(littleShip_img, 80, 20);
        mini_g_left.drawImage(littleShip_img, 140, 20);
        
        mini_g_left.drawImage(bomb_img, minicanvas_w_left-48,minicanvas_h-32 );
        mini_g_left.drawImage(bomb_img, minicanvas_w_left-48,minicanvas_h-32-32);
        mini_g_left.drawImage(bomb_img, minicanvas_w_left-48,minicanvas_h-32-32-32);
        
        // draw background and horizontal gray line
        g.drawImage(playBG_img, 0, 0,width,height);
        g.setStroke(Color.GRAY);
        g.setLineWidth(4);
        g.strokeLine(0, 0, width, 0);
        
        // draw mountains
		g.setStroke(Color.BROWN);
        g.setLineWidth(2);
        g.strokeLine(0, canvas_h-100,100,canvas_h-100);
        g.strokeLine(100, canvas_h-100,200,canvas_h-canvas_h/2 + 100);
        g.strokeLine(200,canvas_h-canvas_h/2 + 100,300, canvas_h-100);
        g.strokeLine(300,canvas_h-100,350, canvas_h-100);
        
        g.strokeLine(350,canvas_h-100,400, canvas_h-canvas_h/2 + 150);
        g.strokeLine(400,canvas_h-canvas_h/2 + 150,450, canvas_h-canvas_h/2 + 200);
        
        g.strokeLine(450,canvas_h-canvas_h/2 + 200,canvas_w-200, canvas_h-canvas_h/2 + 200);
        g.strokeLine(canvas_w-200,canvas_h-canvas_h/2 + 200,canvas_w, canvas_h-canvas_h/2 + 150);
 
        
        // draw and control ship
        int xmove = 50;
        int ymove = 5;
        int x = 50;
        int y = 250;
        shipView.setX(x);
        shipView.setY(y);
        main.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()==KeyCode.RIGHT) {
            	shipView.setX(x+xmove);
            }
        });
        
        
        
        
        // push in layout
		topHBox = new HBox();
		botStackPane = new StackPane();
		root = new Group(shipView);
		layoutPlay = new VBox();
		
        topHBox.getChildren().addAll(minicanvas_left,minicanvas_middle,minicanvas_right);
        botStackPane.getChildren().addAll(canvas,root);
        layoutPlay.getChildren().addAll(topHBox,botStackPane);
        
        
        
        // pause event
        main.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()==KeyCode.ESCAPE & layoutStack.get(0) == layoutPlay) {
                layoutStack.add(layoutPause);
                layoutStack.remove(0);
            }
            else if(key.getCode()==KeyCode.ESCAPE & layoutStack.get(0) == layoutPause) {
                layoutStack.add(layoutPlay);
                layoutStack.remove(0);
            }

        });
        
        
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
	
	
	private void view_MainMenu() {
		planet_label = new Label("PLANET");
		defender_label = new Label("D E F E N D E R");

		
        defender_label.setFont(font); // use this font with our label
        planet_label.setFont(font2);
        defender_label.setTextFill(Color.web("#ffd500")); // set color of label
        planet_label.setTextFill(Color.web("#ffd500"));
		        
		playB = new Button("> START");
		playB.setMnemonicParsing(true);
		playB.setFont(fontButton);
		playB.setTextFill(Color.web("#b33434"));
		playB.setMaxWidth(300);
	    playB.setWrapText(true);
	    playB.setPadding(Insets.EMPTY);
		playB.setStyle("-fx-background-color: transparent");
		playB.setOnMouseEntered(e -> {
			playB.setTextFill(Color.web("#ff5e5e"));
		});
		playB.setOnMouseExited(e -> {
			playB.setTextFill(Color.web("#b33434"));
		});
		playB.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutPlay);
                layoutStack.remove(0);
            }
        });
		 

		
		settingsB = new Button("> SETTINGS");
		settingsB.setMnemonicParsing(true);
		settingsB.setFont(fontButton);
		settingsB.setTextFill(Color.web("#b33434"));
		settingsB.setMaxWidth(300);
		settingsB.setWrapText(true);
		settingsB.setPadding(Insets.EMPTY);
		settingsB.setStyle("-fx-background-color: transparent");
		settingsB.setOnMouseEntered(e -> settingsB.setTextFill(Color.web("#ff5e5e")));
		settingsB.setOnMouseExited(e -> settingsB.setTextFill(Color.web("#b33434")));
		settingsB.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	//settingsB.setTextFill(Color.web("#c4bd2b"));
            	layoutStack.add(layoutSettings);
                layoutStack.remove(0);
            }
        });
		
		
		
		
		highscoresB = new Button("> HIGH-SCORES");
		highscoresB.setMnemonicParsing(true);
		highscoresB.setFont(fontButton);
		highscoresB.setTextFill(Color.web("#b33434"));
		highscoresB.setMaxWidth(300);
		highscoresB.setWrapText(true);
		highscoresB.setPadding(Insets.EMPTY);
		highscoresB.setStyle("-fx-background-color: transparent");
		highscoresB.setOnMouseEntered(e -> highscoresB.setTextFill(Color.web("#ff5e5e")));
		highscoresB.setOnMouseExited(e -> highscoresB.setTextFill(Color.web("#b33434")));
		highscoresB.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutHighscores);
                layoutStack.remove(0);
            }
        });
		
		creditsB = new Button("> CREDITS");
		creditsB.setMnemonicParsing(true);
		creditsB.setFont(fontButton);
		creditsB.setTextFill(Color.web("#b33434")); //selected color: #ff5e5e
		creditsB.setMaxWidth(300);
		creditsB.setWrapText(true);
		creditsB.setPadding(Insets.EMPTY);
		creditsB.setStyle("-fx-background-color: transparent");
		creditsB.setOnMouseEntered(e -> creditsB.setTextFill(Color.web("#ff5e5e")));
		creditsB.setOnMouseExited(e -> creditsB.setTextFill(Color.web("#b33434")));
		creditsB.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutCredits);
                layoutStack.remove(0);
            }
        });
		
		// QUIT HERE
		quitB = new Button("> QUIT");
		quitB.setMnemonicParsing(true);
		quitB.setFont(fontButton);
		quitB.setTextFill(Color.web("#b33434"));
		quitB.setMaxWidth(300);
		quitB.setWrapText(true);
		quitB.setPadding(Insets.EMPTY);
		quitB.setStyle("-fx-background-color: transparent");
		quitB.setOnMouseEntered(e -> quitB.setTextFill(Color.web("#ff5e5e")));
		quitB.setOnMouseExited(e -> quitB.setTextFill(Color.web("#b33434")));
		quitB.setOnAction(e -> window.close());
		
		layoutMain = new VBox(30);
		layoutMain.setBackground(background);
		layoutMain.setAlignment(Pos.CENTER);
		layoutMain.getChildren().addAll(planet_label,defender_label,playB,settingsB,highscoresB,creditsB,quitB);

		main = new Scene(layout, width,height);
		
	}
	
	private void view_CreditsMenu() {
		cred_label = new Label("Credits");
		cred_label.setFont(font);
		cred_label.setTextFill(Color.web("#ffd500"));
		
		credNames_label = new Label("> Gurkan Gur\n> Umut Balkan");
		credNames_label.setFont(fontButton);
		credNames_label.setTextFill(Color.web("#ffd500"));
		
		backB = new Button("> BACK");
		backB.setMnemonicParsing(true);
		backB.setFont(fontButton);
		backB.setTextFill(Color.web("#b33434"));
		backB.setMaxWidth(300);
		backB.setWrapText(true);
		backB.setPadding(Insets.EMPTY);
		backB.setStyle("-fx-background-color: transparent");
		backB.setOnMouseEntered(e -> backB.setTextFill(Color.web("#ff5e5e")));
		backB.setOnMouseExited(e -> backB.setTextFill(Color.web("#b33434")));
		backB.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutMain);
                layoutStack.remove(0);
            }
        });
		
		layoutCredits = new VBox(20);
		layoutCredits.setBackground(background);
		layoutCredits.setAlignment(Pos.CENTER);
		layoutCredits.getChildren().addAll(cred_label,credNames_label, backB);
	}
	
	private void view_SettingsMenu() {
		sett_label = new Label("Settings");
		sett_label.setFont(font);
		sett_label.setTextFill(Color.web("#ffd500"));
		
		backB1 = new Button("> BACK");
		backB1.setMnemonicParsing(true);
		backB1.setFont(fontButton);
		backB1.setTextFill(Color.web("#b33434"));
		backB1.setMaxWidth(300);
		backB1.setWrapText(true);
		backB1.setPadding(Insets.EMPTY);
		backB1.setStyle("-fx-background-color: transparent");
		backB1.setOnMouseEntered(e -> backB1.setTextFill(Color.web("#ff5e5e")));
		backB1.setOnMouseExited(e -> backB1.setTextFill(Color.web("#b33434")));
		backB1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutMain);
                layoutStack.remove(0);
            }
        });
		
		
//		Slider slider = new Slider();
//	    slider.setMin(0);
//	    slider.setMax(1.0);
//	    slider.setValue(0.5);
		
		
		layoutSettings = new VBox(20);
		layoutSettings.setBackground(background);
		layoutSettings.setAlignment(Pos.CENTER);
		layoutSettings.getChildren().addAll(sett_label, backB1);
	}

	private void view_HighScoresMenu() {
		high_label = new Label("HIGH-SCORES");
		high_label.setFont(font);
		high_label.setTextFill(Color.web("#ffd500"));
		
		scores_label = new Label(scores);
		scores_label.setFont(fontButton);
		scores_label.setTextFill(Color.web("#ffd500"));
		
		backB2 = new Button("> BACK");
		backB2.setMnemonicParsing(true);
		backB2.setFont(fontButton);
		backB2.setTextFill(Color.web("#b33434"));
		backB2.setMaxWidth(300);
		backB2.setWrapText(true);
		backB2.setPadding(Insets.EMPTY);
		backB2.setStyle("-fx-background-color: transparent");
		backB2.setOnMouseEntered(e -> backB2.setTextFill(Color.web("#ff5e5e")));
		backB2.setOnMouseExited(e -> backB2.setTextFill(Color.web("#b33434")));
		backB2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutMain);
                layoutStack.remove(0);
            }
        });
		
		layoutHighscores = new VBox(20);
		layoutHighscores.setBackground(background);
		layoutHighscores.setAlignment(Pos.CENTER);
		layoutHighscores.getChildren().addAll(high_label, scores_label, backB2);
		
	}
	
}
