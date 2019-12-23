package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Public class of Bullet
 * Exrends GameObject
 * */
public class Bullet extends GameObject{

	private FileInputStream bombIm;
  //Constructor
  public Bullet(double x, double y, double d, double f){
    super(x,y);
    xDirection = d;
    yDirection = f;
    url = null;
	try {
		url = new FileInputStream("./assets/bullet.png");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	bombIm = null;
	try {
		bombIm = new FileInputStream("./assets/bomb.png");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    imageView = new ImageView(new Image(url));
	imageView.setLayoutX(x);
	imageView.setLayoutY(y);
	xSpeed = 2;
	ySpeed = 2;
	rect.setWidth(2);
	rect.setHeight(2);
  }

  public void move(int x,int y) {
	  	if(xDirection < 0) {
		imageView.setScaleX(-1.0);
		}
	  	else {
			imageView.setScaleX(1.0);
	  	}
	  	
	  	xCoordinate = xCoordinate + xDirection*x*xSpeed;
		yCoordinate = yCoordinate + yDirection*y*ySpeed;
		
		rect.setX(xCoordinate);
		rect.setY(yCoordinate);
		imageView.setLayoutX(xCoordinate);
		imageView.setLayoutY(yCoordinate);
  }
  public void countTimer() {
	  timer = timer + 1;
  }
  
  public void setWidth() {
	  rect.setWidth(1200);
  }
  
  public void setHeight() {
	  rect.setHeight(800);
  }
  
  public int getTimer() {
		return timer;
	}
  
  public void setBomb() {
	  imageView.setImage(new Image(bombIm));
	  setWidth();
	  setHeight();
  }
}