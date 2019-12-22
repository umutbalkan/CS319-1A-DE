package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Public class of Astronaut
 * Extends GameObject class
 * */
public class Astronaut extends GameObject{
  //Variables
  private boolean onLand;
  private boolean isGrabbedByLander;
  private int count;
  private Lander myLander;
  //Constructor
  public Astronaut(double x, double y){
    super(x,y);
    url = null;
	try {
		url = new FileInputStream("./assets/ast.png");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    imageView = new ImageView(new Image(url));
	imageView.setLayoutX(x);
	imageView.setLayoutY(y);
	rect.setWidth(20);
	rect.setHeight(34);
	xSpeed = 1;
	ySpeed = 3;
	onLand=false;
	isGrabbedByLander = false;
	count = 0;
  }
  
  /*
 * Public method of getMutated
 * */
  public void getMutated(){
  }
  
  public void setLander(Lander lander) {
	  myLander = lander;
  }
  
  public Lander getLander() {
	  return myLander;
  }
  
  public void setGrabbed(boolean con) {
	  isGrabbedByLander = con;
  }
  
  public boolean getGrabbes() {
	  return isGrabbedByLander;
  }

  public void setFree() {
	  onLand=true;
  }
  
  public void count() {
	  count = count + 1;
  }
  
  public int getCount() {
	  return count;
  }
  
  public boolean getOnLand() {
	  return onLand;
  }
}