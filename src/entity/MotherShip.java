package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Public class of MotherShip
 * Extends Enemy class
 * */
public class MotherShip extends Enemy{


  //Constructor
  public MotherShip(double x, double y){
    super(x,y);
    
    url = null;
	try {
		url = new FileInputStream("./assets/pod.png");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    imageView = new ImageView(new Image(url));
	imageView.setLayoutX(x);
	imageView.setLayoutY(y);
	xSpeed = 2;
	ySpeed = 2;
	rect.setWidth(29);
	rect.setHeight(28);
	numberOfLives = 10;
  }
  
  /*
 * Public method of releaseSwarms
 * 
 * */
  public Swarmer releaseSwarms(){
    Swarmer a = new Swarmer(xCoordinate+200,yCoordinate+200);
    return a;
  }
  
  /*
 * Public method of attackShip
 * @param Ship ship
 * */
  public void attackShip(Ship ship){
    
  }
  
  public void decreaseLife() {
	  numberOfLives = numberOfLives-1;
  }

public int getLife() {
	return numberOfLives;
}
  
}