package entity;
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
  
  //Constructor
  public Ship(){
    super();
  
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
    //...
  }
  

}