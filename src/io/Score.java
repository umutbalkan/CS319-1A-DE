package io;
/*
 * Public Class of Score
 * 
 * */
public class Score{
  //Variables
  private String name;
  private int score;
  
  //Constructor
  public Score(String nam, int scor){
    name = nam;
    score = scor;
  }
  
  //Get method of score
  public int getScore(){
    return this.score;
  }
  
  //Get methode of name
  public String getName(){
    return this.name;
  }
}
