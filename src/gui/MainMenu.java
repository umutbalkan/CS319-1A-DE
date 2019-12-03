package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainMenu extends VBox {

	public boolean playClicked,settingsClicked,highscoresClicked,creditsClicked,quitClicked;
	
	private Label planet_label,defender_label;
	private Button playB, settingsB, highscoresB,creditsB,quitB;
	private Background background;
	
	public MainMenu(int spacing, Font font,Font font2, Font fontButton, BackgroundImage bg) {
		super(spacing);
		
		// set labels buttons and background
		init_labels(font,font2);
		init_buttons(fontButton);
		background = new Background(bg);   

		
		setBackground(background);
		setAlignment(Pos.CENTER);
		getChildren().addAll(planet_label,defender_label,playB,settingsB,highscoresB,creditsB,quitB);
	}
	
	private void init_buttons(Font fontButton) {
		playB = new Button("> START");
		playB.setFont(fontButton);
		playB.setTextFill(Color.web("#b33434"));
		playB.setMaxWidth(300);
	    playB.setWrapText(true);
	    playB.setPadding(Insets.EMPTY);
		playB.setStyle("-fx-background-color: transparent");
		playB.setOnMouseEntered(e -> playB.setTextFill(Color.web("#ff5e5e")));
		playB.setOnMouseExited(e -> playB.setTextFill(Color.web("#b33434")));
		playB.setOnAction(e -> playClicked = true);
		
		settingsB = new Button("> SETTINGS");
		settingsB.setFont(fontButton);
		settingsB.setTextFill(Color.web("#b33434"));
		settingsB.setMaxWidth(300);
		settingsB.setWrapText(true);
		settingsB.setPadding(Insets.EMPTY);
		settingsB.setStyle("-fx-background-color: transparent");
		settingsB.setOnMouseEntered(e -> settingsB.setTextFill(Color.web("#ff5e5e")));
		settingsB.setOnMouseExited(e -> settingsB.setTextFill(Color.web("#b33434")));
		settingsB.setOnAction(e -> settingsClicked = true);
		
		highscoresB = new Button("> HIGH-SCORES");
		highscoresB.setFont(fontButton);
		highscoresB.setTextFill(Color.web("#b33434"));
		highscoresB.setMaxWidth(300);
		highscoresB.setPadding(Insets.EMPTY);
		highscoresB.setWrapText(true);
		highscoresB.setStyle("-fx-background-color: transparent");
		highscoresB.setOnMouseEntered(e -> highscoresB.setTextFill(Color.web("#ff5e5e")));
		highscoresB.setOnMouseExited(e -> highscoresB.setTextFill(Color.web("#b33434")));
		highscoresB.setOnAction(e -> highscoresClicked = true);
		
		creditsB = new Button("> CREDITS");
		creditsB.setFont(fontButton);
		creditsB.setTextFill(Color.web("#b33434")); //selected color: #ff5e5e
		creditsB.setMaxWidth(300);
		creditsB.setWrapText(true);
		creditsB.setPadding(Insets.EMPTY);
		creditsB.setStyle("-fx-background-color: transparent");
		creditsB.setOnMouseEntered(e -> creditsB.setTextFill(Color.web("#ff5e5e")));
		creditsB.setOnMouseExited(e -> creditsB.setTextFill(Color.web("#b33434")));
		creditsB.setOnAction(e -> creditsClicked = true);
		
		quitB = new Button("> QUIT");
		quitB.setFont(fontButton);
		quitB.setTextFill(Color.web("#b33434"));
		quitB.setMaxWidth(300);
		quitB.setWrapText(true);
		quitB.setPadding(Insets.EMPTY);
		quitB.setStyle("-fx-background-color: transparent");
		quitB.setOnMouseEntered(e -> quitB.setTextFill(Color.web("#ff5e5e")));
		quitB.setOnMouseExited(e -> quitB.setTextFill(Color.web("#b33434")));
		quitB.setOnAction(e -> quitClicked = true);
		
	}

	private void init_labels(Font font, Font font2) {
		planet_label = new Label("PLANET");
		planet_label.setFont(font2);
      	defender_label = new Label("D E F E N D E R");
      	defender_label.setFont(font); // use this font with our label
		defender_label.setTextFill(Color.web("#ffd500")); // set color of label
		planet_label.setTextFill(Color.web("#ffd500"));
		
	}

}
