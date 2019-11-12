package io;

import java.io.*;

public class HighScoreManager{
  private static HighScoreManager instance = new HighScoreManager();
  
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

    public static HighScoreManager getInstance() {
        if (instance == null) 
            instance = new HighScoreManager(); 
  
        return instance; 
    }
   
    public Score createScore(String name, int score){
      Score a = new Score(name,score);
      updateHighScores(a);
      return a;
    }
    
    
    
    public boolean updateHighScores(Score sco){
      
      boolean con = false;
      String o = readHighScoreFile();
      int maxIndex = 0;
      int sc = sco.getScore();
      int counter = 1;
      int first=0;
      int last=0;
      //System.out.println(sc);
      for (int i = 0; i < o.length(); i++) {
        if (con == false && o.charAt(i) == '\n' && counter == 1){
            first = i+1;
            counter = 2;
        }
        if(first != i+1 && o.charAt(i) == '\n' && counter == 2){
          last = i;
          counter = 1;
          con = true;
          //System.out.println("o");
        }
        if(con == true && sc<=Integer.parseInt(o.substring(first,last))){
          maxIndex = last+1;
          con = false;
        }
    } 
      //System.out.println(maxIndex);
      
      String a = o.substring(0,maxIndex);
      String b = o.substring(maxIndex,o.length());
      
      o = a + sco.getName() + "\n" + sco.getScore() +"\n" + b;
      
      try{
      writer = new FileWriter(file); 
      
      // Writes the content to the file
      writer.write(o); 
      writer.flush();
      writer.close();
      }
      catch(java.io.IOException e){
                System.out.println("createDirectory failed:" + e);
            }
      return true;
    }
    
    public String readHighScoreFile(){
      String s = "";
      try{
      reader = new FileReader(file);
      BufferedReader bufreader = new BufferedReader(reader);
      
      while ((line = bufreader.readLine()) != null) {
       s = s+line+"\n";
       //System.out.println(line);
    }
      reader.close();
      bufreader.close();
       } 
      catch(java.io.IOException e){
                System.out.println("createDirectory failed:" + e);
            }
     return s; 
    }
}