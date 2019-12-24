package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Public class of Bomber
 * Extends Enemy class
 * */
public class Bomber extends Enemy{


  //Constructor
  public Bomber(double x, double y){
    super(x,y);
    try {
		url = new FileInputStream("./assets/bomber.png");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	imageView = new ImageView(new Image(url));
	imageView.setLayoutX(x);
	imageView.setLayoutY(y);
	rect.setWidth(24);
	rect.setHeight(24);
	xSpeed = 1;
	ySpeed = 1;
	xDirection = 1;
	yDirection = 1;
  }
  
  /*
 * Public method of leaveMine
 * */
  public void leaveMine(){
    
  }
  
  public void move() {
	  if(xDirection==1) {
	  	xCoordinate = xCoordinate + 1*xSpeed;
	  }
	  if(xDirection==-1) {
		  xCoordinate = xCoordinate - 1*xSpeed;
	  }
	  if(yDirection==1) {
		  	yCoordinate = yCoordinate - 1*ySpeed;
		  }
	  if(yDirection==-1) {
			  yCoordinate = yCoordinate + 1*ySpeed;
		  }
		rect.setX(xCoordinate);
		rect.setY(yCoordinate);
		imageView.setLayoutX(xCoordinate);
		imageView.setLayoutY(yCoordinate);
  }
}