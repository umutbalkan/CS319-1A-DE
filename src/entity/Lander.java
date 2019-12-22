package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Public class of Lander
 * Extends Enemy class
 * */
public class Lander extends Enemy{

	private boolean hasAstronaut;
	private Astronaut takenAstronaut;
  //Constructor
  public Lander(double x, double y){
    super(x,y);
    url = null;
	try {
		url = new FileInputStream("./assets/lander.png");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	imageView = new ImageView(new Image(url));
	imageView.setLayoutX(x);
	imageView.setLayoutY(y);
	rect.setWidth(40);
	rect.setHeight(42);
	xSpeed = 1;
	ySpeed = 1;
	hasAstronaut = false;
	takenAstronaut = null;
	xDirection = 1;
	yDirection = 1;
  }
  
  /*
 * Public method of findClosestAstronaut
 * @return Astronaut result
 * */
  public Astronaut findClosestAstronaut(){
    Astronaut result = null;
    
    
    
    
    return result;
  }
  
  /*
 * Public method of abductAstronaut
 * This method makes Lander to abduct the given astronaut
 * @param Astronaut astronaut
 * */
  public void abductAstronaut(Astronaut astronaut){
	  hasAstronaut = true;
	  takenAstronaut = astronaut;
  }
  
  /*
 * Public method of attackShip
 * This method makes Lander to attack to the ship
 * @param Ship ship
 * */
  public void attackShip(Ship ship){
    
  }
  
  public void controlAstronaut() {
	  if(hasAstronaut==true) {
	  takenAstronaut.setX(xCoordinate+20);
	  takenAstronaut.setY(yCoordinate+30);
	  }
  }
  
  public void move() {
	  if(xDirection==1) {
	  	xCoordinate = xCoordinate + 0*xSpeed;
		yCoordinate = yCoordinate + 1*ySpeed;
	  }
	  if(xDirection==-1) {
		  xCoordinate = xCoordinate + 0*xSpeed;
			yCoordinate = yCoordinate - 1*ySpeed;
	  }
		rect.setX(xCoordinate);
		rect.setY(yCoordinate);
		imageView.setLayoutX(xCoordinate);
		imageView.setLayoutY(yCoordinate);
  }
  
  public Astronaut getAstronaut() {
	  return takenAstronaut;
  }
  
  public boolean getHasAstronaut() {
	  return hasAstronaut;
  }
}