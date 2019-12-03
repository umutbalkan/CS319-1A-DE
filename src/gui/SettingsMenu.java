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

public class SettingsMenu extends VBox{

	
	private Label sett_label;
	private Button backB;
	public boolean backClicked;
	private Background background;
	
	public SettingsMenu(int spacing, Font font, Font fontButton, BackgroundImage bg) {
		super(spacing);
		
		init_label(font);
		init_button(fontButton);
		
		background = new Background(bg);	
		
		setBackground(background);
		setAlignment(Pos.CENTER);
		getChildren().addAll(sett_label, backB);
	}

	private void init_button(Font fontButton) {
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
		backB.setOnAction(e -> backClicked = true);
		
	}

	private void init_label(Font font) {
		sett_label = new Label("Settings");
		sett_label.setFont(font);
		sett_label.setTextFill(Color.web("#ffd500"));
	}
}
