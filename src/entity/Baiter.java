package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Public class of Baiter
 * Extends Enemy
 * */
public class Baiter extends Enemy{


  //Constructore
  public Baiter(double x, double y){
	  	super(x, y);
		// TODO Auto-generated constructor stub
		
		url = null;
		try {
			url = new FileInputStream("./assets/baiter.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageView = new ImageView(new Image(url));
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		rect.setWidth(45);
		rect.setHeight(17);
		xSpeed = 2;
		ySpeed = 2;
		xDirection = 1;
		yDirection = 1;
		numberOfLives = 4;
  }
  
  /*
 * Public method of follow
 * @param Ship ship
 * */
  
  public void move(double d, double e) {
		
	  	
	  	xCoordinate = xCoordinate + xDirection*d*xSpeed;
		yCoordinate = yCoordinate + yDirection*e*ySpeed;
		
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
	
  public void follow(Ship ship){
	  
  }
  
}