package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GiantM extends Enemy{

	public GiantM(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		
		url = null;
		try {
			url = new FileInputStream("./assets/boss.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageView = new ImageView(new Image(url));
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		rect.setWidth(239);
		rect.setHeight(224);
		xSpeed = 2;
		ySpeed = 2;
		xDirection = 1;
		yDirection = 1;
		numberOfLives = 150;
	}
	
	public void move(int x, int y) {
		
		  	
		  	xCoordinate = xCoordinate + xDirection*x*xSpeed;
			yCoordinate = yCoordinate + yDirection*y*ySpeed;
			
			rect.setX(xCoordinate);
			rect.setY(yCoordinate);
			imageView.setLayoutX(xCoordinate);
			imageView.setLayoutY(yCoordinate);
	}
	
	public void setXDirection(double x){
		xDirection = x;
	}
	
	public void setYDirection(double y) {
		yDirection = y;
	}

	public void decreaseLife() {
		numberOfLives = numberOfLives-1;
	}
	
	public int getLife() {
		return numberOfLives;
	}
}
