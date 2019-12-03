package gui;

import io.HighScoreManager;
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

public class HighScoresMenu extends VBox{

	private Label high_label, scores_label;
	private Button backB2;
	private String scores;
	private HighScoreManager highscoreManager;
	public boolean backClicked;
	private Background background;
	
	public HighScoresMenu(int spacing, Font font,Font fontButton, BackgroundImage bg) {
		super(spacing);
		
		// set labels buttons and background
		init_label(font,fontButton);
		init_button(fontButton);
		background = new Background(bg);

		
		setBackground(background);
		setAlignment(Pos.CENTER);
		getChildren().addAll(high_label, scores_label, backB2);
	}

	private void init_button(Font fontButton) {
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
		backB2.setOnAction(e -> backClicked = true);
		
	}

	private void init_label(Font font, Font fontButton) {
		high_label = new Label("HIGH-SCORES");
		high_label.setFont(font);
		high_label.setTextFill(Color.web("#ffd500"));
		
		
		highscoreManager = HighScoreManager.getInstance();
		scores = highscoreManager.readHighScoreFile();
		scores_label = new Label(scores);
		scores_label.setFont(fontButton);
		scores_label.setTextFill(Color.web("#ffd500"));
		
	}
}
