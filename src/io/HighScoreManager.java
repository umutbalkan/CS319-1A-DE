package io;

import java.io.*;
/*
 * Public Class for HighScoreManager
 * This class writes the received score to the right position in the created text file.
 *
 * */
public class HighScoreManager{
  private static HighScoreManager instance = new HighScoreManager();
  //Variables
  private String filePath;
  private String fileName;
  private String line;
  private FileWriter writer;
  private FileReader reader;
  private File file;

    //private constructor.
    private HighScoreManager(){

      fileName = "scores.txt";
      try{

      file = new File(fileName);

      // creates the file
      if(file.createNewFile()){

      // creates a FileWriter Object
      writer = new FileWriter(file);

      // Writes the content to the file
      writer.write("gurkan\n5\nsumut\n1\nDoe\n14");
      writer.flush();
      writer.close();
      }
         else{
           System.out.println("File already exists.");
         }
            }
      catch(java.io.IOException e){
                System.out.println("createDirectory failed:" + e);
            }

    }

    /*
     * Get instance method of the singleton class which returns instance of HighScoreManager
     * @return      instance
     * */
    public static HighScoreManager getInstance() {
        if (instance == null)
            instance = new HighScoreManager();

        return instance;
    }

    /*
     * Public method of createScore which crates a new score according to the received parameters
     * @param     String name
     * @param     int    score
     * @return    Score
     * */
    public Score createScore(String name, int score){
      Score a = new Score(name,score);
      updateHighScores(a);
      return a;
    }

    /*
     * Public method of updateHighScores which writes the received score to the text file.
     * @param    Score receivedScore
     * @return   boolean true
     * */
    public boolean updateHighScores(Score receivedScore){
      String currentTextString = readHighScoreFile(); //Read current text file

      int indexToWrite = 0;  //Declare an int to initilaze with index to write new score
      int scoreValue = receivedScore.getScore();  //Get the score value of the received score object

      int counter = 1;
      int firstIndexOfNewLine=0;
      int lastIndexOfNewLine=0;
      boolean con = false;
      System.out.println(scoreValue);

      //This algorithm finds the right place on the text for the new score with finding "new line" chars
      for (int i = 0; i < currentTextString.length(); i++) {
        if (con == false && currentTextString.charAt(i) == '\n' && counter == 1){
            firstIndexOfNewLine = i+1;
            counter = 2;
        }
        if(firstIndexOfNewLine != i+1 && currentTextString.charAt(i) == '\n' && counter == 2){
          lastIndexOfNewLine = i;
          counter = 1;
          con = true;
          //System.out.println("o");
        }
        if(con == true && scoreValue<=Integer.parseInt(currentTextString.substring(firstIndexOfNewLine,lastIndexOfNewLine))){
          indexToWrite = lastIndexOfNewLine+1;
          con = false;
        }
    }

      String firstPartOfTheCurrentText = currentTextString.substring(0,indexToWrite);
      String lastPartOfTheCurrentText = currentTextString.substring(indexToWrite,currentTextString.length());

      currentTextString = firstPartOfTheCurrentText + receivedScore.getName() + "\n" + scoreValue +"\n" + lastPartOfTheCurrentText;

      //Write the new string to the text file
      try{
      writer = new FileWriter(file);

      // Writes the content to the file
      writer.write(currentTextString);
      writer.flush();
      writer.close();
      }
      catch(java.io.IOException e){
                System.out.println("createDirectory failed:" + e);
            }
      return true;
    }

    /* Public method of readHighScoreFile which reads the text file and returns its contents in a string
     * @return String currentTextContents
     *
     * */
    public String readHighScoreFile(){
      String currentTextContents = "";

      try{
      reader = new FileReader(file);
      BufferedReader bufreader = new BufferedReader(reader);
      while ((line = bufreader.readLine()) != null) {
       currentTextContents = currentTextContents+line+"\n";
    }
      reader.close();
      bufreader.close();
       }
      catch(java.io.IOException e){
                System.out.println("createDirectory failed:" + e);
            }

     return currentTextContents;
    }
}
