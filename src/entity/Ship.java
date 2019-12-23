package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Public class of Shio
 * This class represents the Ship object.
 * Extends GameObject
 * */
public class Ship extends GameObject{
  
  //Variables
  private int score;
  private int numberOfBombs;
  private int firingInterval;
  private int lastFire;
  private int count;
  private boolean crash;
  //Constructor
  public Ship(double x, double y){
    super(x,y);
    url = null;
	try {
		url = new FileInputStream("./assets/ship.png");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    imageView = new ImageView(new Image(url));
	imageView.setLayoutX(x);
	imageView.setLayoutY(y);
	xSpeed = 1;
	ySpeed = 1;
	xDirection = 1;
	yDirection = 1;
	numberOfLives = 3;
	rect.setWidth(64);
	rect.setHeight(32);
	score = 0;
	count = 0;
	crash = false;
  }
  
  /*
 * Public method of boostSpeed
 * This method boosts the speed of the ship(integer)
 * 
 * */
  public void boostSpeed(){
    //...
  }
  
  /*
 * Public method of hyperJump
 * This method runs the "hyper jump" movement
 * */
  public void hyperJump(){
    //...
  }
  
  /*
 * Public method of bomb
 * This method brings a bomb
 * */
  public void bomb(){
    //...
  }
  
  /*
 * Public method for rescuing an astronaut
 * 
 * */
  public void rescueAstronaut(Astronaut astronaut){
    
  }
  
  public void setDirection(int d) {
	  xDirection = d;
  }
  
  public double getDirection() {
	  return xDirection;
  }
  
  public int getNumberOfLives() {
	  return numberOfLives;
  }
  
  public void setNumberOfLives(int n) {
	  numberOfLives = n;
  }

  public void decreaseLife() {
	  numberOfLives = numberOfLives - 1;
  }
  
  public int getScore() {
	  return score;
  }
  
  public void setScore(int x) {
	  score = x;
  }
  
  public void animCounter() {
	  count = count + 1;
  }
  
  public void resCount() {
	  count = 0;
  }
  
  public int getCount() {
	  return count;
  }
  
  public void crashAnim(int i) {
	  if(i==1)
		  imageView.setImage(null);
	  else
		  imageView.setImage(new Image(url));
  }
}