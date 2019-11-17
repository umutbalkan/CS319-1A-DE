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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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



public class GameEngine extends Application{
	
	
	private static GameEngine game = new GameEngine();
	
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
	private Line separator_Hline;
	private Line separator_Vline1;
	private Line separator_Vline2;
	private Background background;
	private Font font,font2;
	private StackPane layout;
	private ObservableList<Node> layoutStack;
	private Label planet_label;
	
	@Override
	public void init() throws Exception{
		System.out.println("Game - initializing ui widgets & layouts");
	}
	
	
	public static GameEngine getInstance() {
		return game;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		setPrimitiveAttributes();
		window = primaryStage;
		window.setTitle("Planet Defender");
		
		layout = new StackPane(); 
		layoutStack = layout.getChildren();
		
		
		// Display
		view();
		layoutStack.add(layoutMain);
		window.setScene(main); // change this to 'main' later.
		window.show();
		
	}
	
	
	public void stop() throws Exception{
		System.out.println("Game - Terminating.");
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
                                                BackgroundSize.DEFAULT); 
  
            // create Background 
            background = new Background(backgroundimage); 
	      } catch (FileNotFoundException e) {
	        e.printStackTrace();
	      }
		
		
        view_MainMenu();
		view_CreditsMenu();
		view_SettingsMenu();
		view_HighScoresMenu();
		view_PlayScreen();
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
        separator_Hline = new Line(0, minicanvas_h-4, width, minicanvas_h-4);
        separator_Hline.setStroke(Color.BLUE);
        separator_Hline.setStrokeWidth(4);
        
        separator_Vline1 = new Line(minicanvas_w_left, 0,minicanvas_w_left , minicanvas_h-4);
        separator_Vline1.setStroke(Color.BLUE);
        separator_Vline1.setStrokeWidth(4);
        
        separator_Vline2 = new Line(minicanvas_w_middle+minicanvas_w_left, 0,minicanvas_w_middle+minicanvas_w_left, minicanvas_h-4);
        separator_Vline2.setStroke(Color.BLUE);
        separator_Vline2.setStrokeWidth(4);
        
        
        // set colors for graphics contexts for all canvas
        g.setFill(Color.GRAY);
        g.fillRect(0, 0, canvas_w, canvas_h);
        mini_g_left.setFill(Color.BLACK);
        mini_g_left.fillRect(0, 0, minicanvas_w_left, minicanvas_h);
        mini_g_middle.setFill(Color.GREEN);
        mini_g_middle.fillRect(0, 0, minicanvas_w_middle, minicanvas_h);
        mini_g_right.setFill(Color.BLACK);
        mini_g_right.fillRect(0, 0, minicanvas_w_right, minicanvas_h);
        
        // push in layout
		topHBox = new HBox();
		layoutPlay = new VBox();
        topHBox.getChildren().addAll(minicanvas_left,separator_Vline1,minicanvas_middle,separator_Vline2,minicanvas_right);
        layoutPlay.getChildren().addAll(topHBox,separator_Hline,canvas);
	}
	
	private void view_MainMenu() {
		planet_label = new Label("PLANET");
		defender_label = new Label("D E F E N D E R");

		
        defender_label.setFont(font); // use this font with our label
        planet_label.setFont(font2);
        defender_label.setTextFill(Color.web("#ffd500")); // set color of label
        planet_label.setTextFill(Color.web("#ffd500"));
		
		playB = new Button("START");
		playB.setMaxWidth(100);
		playB.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutPlay);
                layoutStack.remove(0);
            }
        });
		
		settingsB = new Button("Settings");
		settingsB.setMaxWidth(100);
		
		settingsB.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutSettings);
                layoutStack.remove(0);
            }
        });
		
		highscoresB = new Button("High Scores");
		highscoresB.setMaxWidth(100);
		highscoresB.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutHighscores);
                layoutStack.remove(0);
            }
        });
		
		creditsB = new Button("Credits");
		creditsB.setMaxWidth(100);
		creditsB.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutCredits);
                layoutStack.remove(0);
            }
        });
		
		// QUIT HERE
		quitB = new Button("Quit");
		quitB.setMaxWidth(100);
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
		
		backB = new Button("Back");
		backB.setMaxWidth(100);
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
		layoutCredits.getChildren().addAll(cred_label, backB);
	}
	
	private void view_SettingsMenu() {
		sett_label = new Label("Settings");
		sett_label.setFont(font);
		sett_label.setTextFill(Color.web("#ffd500"));
		
		backB1 = new Button("Back");
		backB1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	layoutStack.add(layoutMain);
                layoutStack.remove(0);
            }
        });
		
		layoutSettings = new VBox(20);
		layoutSettings.setBackground(background);
		layoutSettings.setAlignment(Pos.CENTER);
		layoutSettings.getChildren().addAll(sett_label, backB1);
	}

	private void view_HighScoresMenu() {
		high_label = new Label("High Scores");
		high_label.setFont(font);
		high_label.setTextFill(Color.web("#ffd500"));
		
		backB2 = new Button("Back");
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
		layoutHighscores.getChildren().addAll(high_label, backB2);
		
	}
	
}
