package entity;
import java.awt.Graphics;
import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
/*
 * Public class of GameObject
 * This class represents the game objects. Specific types are inharited. 
 * 
 * */

public class GameObject{
  
//Variables
private Sprite[] spriteFrames;
protected Rectangle rect;
protected ImageView imageView;
private int frameNumber;
private int frameDuration;
protected int numberOfLives;
protected double xDirection;
protected double yDirection;
protected int ySpeed;
protected int xSpeed;
protected double xCoordinate;
protected double yCoordinate;
protected FileInputStream url;
protected int timer;
//Constructor
public GameObject(double x, double y){
	rect = new Rectangle();
	xCoordinate = x;
	yCoordinate = y;
	rect.setX(x);
	rect.setY(y);
}


/*
 * Public method of move.
 * @param long delta, int x, int y
 * */
public void move(int x, int y){
	if(x<0)
		imageView.setScaleX(-1.0);
	if(x>0)
		imageView.setScaleX(1.0);
	xCoordinate = xCoordinate + x*xSpeed;
	yCoordinate = yCoordinate + y*ySpeed;
	rect.setX(xCoordinate);
	rect.setY(yCoordinate);
	imageView.setLayoutX(xCoordinate);
	imageView.setLayoutY(yCoordinate);
}

public void setX(double x) {
	xCoordinate = x;
	rect.setX(xCoordinate);
	imageView.setLayoutX(xCoordinate);
}

public void setY(double y) {
	yCoordinate = y;
	rect.setY(yCoordinate);
	imageView.setLayoutY(yCoordinate);
}

public double getX() {
	return xCoordinate;
}

public double getY() {
	return yCoordinate;
}

public double getWidth() {
	return rect.getWidth();
}

public double getHeight() {
	return rect.getHeight();
}

public ImageView getImageView() {
	return imageView;
}

public void setDirection(int x) {
	xDirection = x;
}

public void setYDirection(int x) {
	yDirection = x;
}

public void setSpeed(int x) {
	xSpeed = x;
	ySpeed = x;
}

public void setNull() {
	imageView = null;
	rect = null;
	
}



}