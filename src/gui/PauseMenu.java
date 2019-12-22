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

public class PauseMenu extends VBox{
	
	private Label pause_label;
	private Button pauseB, stopPlayB;
	private boolean pauseClicked, exitClicked;
	private Background background;
	public PauseMenu(int spacing, Font font, Font fontButton, BackgroundImage bg) {
		super(spacing);
		
		background = new Background(bg);
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
		pauseB.setOnAction(e -> pauseClicked = true);
		
		
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
	    stopPlayB.setOnAction(e -> exitClicked = true);
	    
	    setBackground(background);
	    setAlignment(Pos.CENTER);
	    getChildren().addAll(pause_label, stopPlayB,pauseB);
	}

	
	public boolean isBackClicked() {
		return pauseClicked;
	}
	
	public void setBackClicked(boolean b) {
		pauseClicked = b;
	}
	
	public boolean isExitClicked() {
		return exitClicked;
	}
	
	public void setExitClicked(boolean b) {
		exitClicked = b;
	}
	
}
