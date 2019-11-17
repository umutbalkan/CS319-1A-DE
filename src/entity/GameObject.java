package entity;
import java.awt.Graphics;
import java.awt.Rectangle;
/*
 * Public class of GameObject
 * This class represents the game objects. Specific types are inharited. 
 * 
 * */

public class GameObject{
  
//Variables
private Sprite[] spriteFrames;
private Rectangle rect;
private int frameNumber;
private int frameDuration;
private int numberOfLives;
private int ySpeed;
private int xSpeed;
private int xCoordinate;
private int yCoordinate;

//Constructor
public GameObject(){


}


/*
 * Public method of move.
 * @param long delta, int x, int y
 * */
public void move(long delta, int x, int y){

}

/*
 * Public method of draw.
 * @param Graphics g
 * */
void draw(Graphics g){
  

}

}