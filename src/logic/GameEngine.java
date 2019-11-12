package logic;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;



public class GameEngine extends Application{
	
	// GUI ELEMENTS
	private Scene main,credits,settings,play,highscores;
	private Stage window;
	private int width;
	private int height;
	private int canvas_w, canvas_h;
	private int minicanvas_w_left, minicanvas_w_middle, minicanvas_w_right, minicanvas_h;
	private Button playB, settingsB, creditsB, highscoresB, quitB,backB, backB1, backB2;
	private Label def_label, cred_label, sett_label, high_label;
	private VBox layoutMain, layoutSettings, layoutCredits, layoutHighscores;
	private VBox root;
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
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		setPrimitiveAttributes();
		window = primaryStage;
		window.setTitle("Defender");
		root = new VBox();
		  	
		// Display
		view();
		window.setScene(main); // change this to 'main' later.
		window.show();
		
	}
	
	public static void main(String args[]){          
	      launch(args);    
	
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
		
        topHBox.getChildren().addAll(minicanvas_left,separator_Vline1,minicanvas_middle,separator_Vline2,minicanvas_right);
        root.getChildren().addAll(topHBox,separator_Hline,canvas);
	}
	
	private void view_MainMenu() {
		def_label = new Label("D E F E N D E R");
		
		playB = new Button("Play");
		playB.setOnAction(e->window.setScene(play));
		
		play = new Scene(root,width,height);
		
		settingsB = new Button("Settings");
		settingsB.setOnAction(e -> window.setScene(settings));
		
		highscoresB = new Button("High Scores");
		highscoresB.setOnAction(e -> window.setScene(highscores));
		
		creditsB = new Button("Credits");
		creditsB.setOnAction(e -> window.setScene(credits));
		
		// QUIT HERE
		quitB = new Button("Quit");
		quitB.setOnAction(e -> window.close());
		
		layoutMain = new VBox(20);
		layoutMain.setAlignment(Pos.CENTER);
		layoutMain.getChildren().addAll(def_label,playB,settingsB,highscoresB,creditsB,quitB);
		main = new Scene(layoutMain, width,height);	
	}
	
	private void view_CreditsMenu() {
		cred_label = new Label("Credits");
		backB = new Button("Back");
		backB.setOnAction(e -> window.setScene(main));
		
		layoutCredits = new VBox(20);
		layoutCredits.setAlignment(Pos.CENTER);
		layoutCredits.getChildren().addAll(cred_label, backB);
		credits = new Scene(layoutCredits, width, height);
	}
	
	private void view_SettingsMenu() {
		sett_label = new Label("Settings");
		backB1 = new Button("Back");
		backB1.setOnAction(e -> window.setScene(main));
		
		layoutSettings = new VBox(20);
		layoutSettings.setAlignment(Pos.CENTER);
		layoutSettings.getChildren().addAll(sett_label, backB1);
		settings = new Scene(layoutSettings, width, height);
	}

	private void view_HighScoresMenu() {
		high_label = new Label("High Scores");
		backB2 = new Button("Back");
		backB2.setOnAction(e -> window.setScene(main));
		
		layoutHighscores = new VBox(20);
		layoutHighscores.setAlignment(Pos.CENTER);
		layoutHighscores.getChildren().addAll(high_label, backB2);
		highscores = new Scene(layoutHighscores, width, height);
		
		
	}
	
}
