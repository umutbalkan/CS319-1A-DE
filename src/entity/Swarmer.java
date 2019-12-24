package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Public class of Swarmer
 * This class represents the enemy type "Swarmer". 
 * Extends Enemy class
 * */
public class Swarmer extends Enemy{


  //Constructor
  public Swarmer(double x, double y){
    super(x,y);
    url = null;
	try {
		url = new FileInputStream("./assets/swarmer.png");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	imageView = new ImageView(new Image(url));
	imageView.setLayoutX(x);
	imageView.setLayoutY(y);
	rect.setWidth(20);
	rect.setHeight(18);
	xSpeed = 2;
	ySpeed = 2;
	xDirection = 1;
	yDirection = 1;
	numberOfLives = 1;
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