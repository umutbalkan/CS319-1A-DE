package logic;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sound.SoundManager;
import entity.Astronaut;
import entity.Baiter;
import entity.Bomber;
import entity.Bullet;
import entity.GameObject;
import entity.GiantM;
import entity.Lander;
import entity.MotherShip;
import entity.Mutant;
import entity.Ship;
import entity.Swarmer;
import gui.CreditsMenu;
import gui.HighScoresMenu;
import gui.LayoutManager;
import gui.MainMenu;
import gui.PauseMenu;
import gui.SettingsMenu;
import io.HighScoreManager;

public class GameEngine extends Application{

	private LinkedList<Rectangle> rectList;
	private LayoutManager layout;
	private VBox root;
	private MainMenu mainmenu;
	private HighScoresMenu highscoremenu;
	private CreditsMenu creditsmenu;
	private Scene main;
	private Stage window;
	private int width;
	private int height;
	private MediaPlayer mediaPlayer;
	private ImageView shipView;
	private HighScoreManager highscoreManager;
	private InputManager inputManager;
	private CollisionManager collisionManager;
	private SoundManager soundManager;
	private Pane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private AnimationTimer timer;
	private boolean deleteBomb;
	private int bombCount;
	private Ship ship;
	private LinkedList<Bullet> shipBulletList;
	private LinkedList<Astronaut> astranoutList;
	private LinkedList<Lander> landerList;
	private LinkedList<Bullet> enemyBulletList;
	private LinkedList<Swarmer> swarmerList;
	private LinkedList<Mutant> mutantList;
	private LinkedList<Bomber> bomberList;
	private LinkedList<Bullet> mineList;
	private Bullet bomb1;
	private Bullet bomb2;
	private Bullet bomb3;
	private MotherShip pod1;
	private MotherShip pod2;
	private GiantM boss1;
	private Baiter baiter;
	private int bulletNumber;
	private FileInputStream backgroundUrl;
	private ImageView lifeOne;
	private ImageView iBomb1, iBomb2, iBomb3;
	private ImageView lifeTwo;
	private ImageView lifeThree;
	private boolean bombCollide;
	private int waveNumber;
	private boolean wave1;
	private boolean wave2;
	private boolean wave1Final;
	private boolean wave2Final;
	private boolean wave3;
	private boolean wave3Final;
	private boolean wave4;
	private boolean wave4Final;
	private boolean wave5;
	private boolean wave5Final;
	private boolean wave6;
	private boolean wave6Final;
	private boolean wave7;
	private boolean wave7Final;
	double yo=0;
	boolean spacePresse = false;
	private int as1;
	private int as1Mut;
	private int asTimer;
	private int a;
	private int b;
	private int con;
	private int boo;
	private int baiterNumber;
	long finishTime;
	private SettingsMenu settingsmenu;
	private PauseMenu pausemenu;
	private int bomb;
	private HBox topVBox;
	private Pane leftTop;
	private Pane map;
	private Pane rightTop;
	private Font font2;
	private Label score_label;
	private Rectangle clip;
	private int x;	private int wave1con;
	private int wave2con;
	private int wave3con;
	private int wave4con;
	private int wave5con;
	private int wave6con;
	private Rectangle rect;
	private int wave7con;
	private double clipMin;
	private Canvas canvas;
	private GraphicsContext gc;
	private PixelWriter mapDrawer;
	private Canvas cvas;
	private GraphicsContext g;
	private Canvas canvasI;
	private GraphicsContext gr;
	private void initGameObject() {
		//Create lists, ship, wave conditions

		ship = new Ship(640,210);
		gamePane.getChildren().add(ship.getImageView());
		shipBulletList = new LinkedList<Bullet>();
		bomberList = new LinkedList<Bomber>();
		a = 1;
		b = 1;
		as1 = 0;
		as1Mut = 0;
		asTimer = 0;
		bulletNumber = 0;
		wave1= false;
		wave2 = false;
		waveNumber =6 ;
		wave1Final = false;
		wave2Final = false;
		wave3 = false;
		wave3Final = false;
		wave4 = false;
		wave4Final = false;
		wave5 = false;
		wave5Final = false;
		wave6 = true;
		wave6Final = false;
		wave7 = false;
		wave7Final = false;
		astranoutList = new LinkedList<Astronaut>();
		landerList = new LinkedList<Lander>();
		enemyBulletList = new LinkedList<Bullet>();
		swarmerList = new LinkedList<Swarmer>();
		mutantList = new LinkedList<Mutant>();
		mineList = new LinkedList<Bullet>();
		rectList = new LinkedList<Rectangle>();
		boo=1;
		baiterNumber = 5;
		con=0;
		bomb = 1;
		bombCount = 3;
		bombCollide = false;
		deleteBomb = false;
		wave1con = 1; 
		wave2con = 1; 
		wave3con = 1; 
		wave4con = 1; 
		wave5con = 1; 
		wave6con = 1; 
		wave7con = 1; 
	}

	private void processGame() {
		//Process method of the game
		clipMin = ship.getX() - gameScene.getWidth()/2;
		if(clipMin <= 0) {
			clip.setX(0);
		}
		else if(ship.getX() >= 3200 && clipMin > 0) {
			clip.setX(2560);
		}
		else {
		clip.setX(clipMin);
		}
		System.out.println(gamePane.getWidth());
		
		for(int i = 0; i < landerList.size(); i++) {
			double dx = landerList.get(i).getX() / 8.2;
			double dy = landerList.get(i).getY() / 8.2;
			rectList.get(i).setTranslateX(dx);
			rectList.get(i).setTranslateY(dy);
		}
		
		if(ship.getNumberOfLives()!=0) {
			//MoveShip and FireBullet runs in every wave
			update_scoreLabel();
			moveShip();
			fireBullet();
			
			initWave1();
			
			initWave2();
			initWave3();  //To start from a wave, set waveNumber to its wave, and make comment the upper init methods
			initWave4();
			initWave5();
			
			initWave6();
			
			initWave7();
		}
		if(ship.getNumberOfLives() == 0) {
			gameStage.close();
		}
	}


	private void fireBullet() {
		//Conditions of bullet fire
		if(inputManager.getBullet()) { //Get pressed
			a = 0;
		}
		if(a==0 && !inputManager.getBullet()) { //Create bullet when button released
			//Bullet created when button is released, because game loop runs faster than key listener. Every time button pressed, this loop runs 4-5 times.
			//To block that bullet setted on releasing the button
			a = 1;
			shipBulletList.add(new Bullet(ship.getX(),ship.getY(),ship.getDirection(),1));
			gamePane.getChildren().add(shipBulletList.get(shipBulletList.size()-1).getImageView());
			soundManager.play("shoot");

		}

		for(int i=0; i<shipBulletList.size(); i++) { //Move the active bullets
			shipBulletList.get(i).move(3, 0);
			shipBulletList.get(i).countTimer();
			if(shipBulletList.get(i).getTimer() == 100) { //Delete the bullet if it goes too much
				removeGameObject(shipBulletList.get(i));
				shipBulletList.remove(i);
			}
		}
		
		if(inputManager.getBomb()) { //Get pressed
			bomb = 0;
		}
		if(bomb==0 && !inputManager.getBomb()) { //Create bullet when button released
			//Bullet created when button is released, because game loop runs faster than key listener. Every time button pressed, this loop runs 4-5 times.
			//To block that bullet setted on releasing the button
			bomb = 1;
			System.out.println("bombpress");
			if(bombCount>0 && bomb1==null) {
				bomb1 = new Bullet(ship.getX(),ship.getY(),ship.getDirection(),1);
				bomb1.setBomb();
				gamePane.getChildren().add(bomb1.getImageView());
				bombCount = bombCount-1;
				soundManager.play("shoot");

			}
		}
		if(bomb1!=null) {
			bomb1.move(3, 0);
			bomb1.countTimer();
			if(bomb1.getTimer() == 50) { //Delete the bullet if it goes too much
				bombCollide = true;
				
			}
			if(deleteBomb) {
				gamePane.getChildren().remove(bomb1.getImageView());
				bomb1 = null;
				deleteBomb = false;
			}
		}
		if(bombCount == 2) {
			leftTop.getChildren().remove(iBomb1);
			iBomb1 = null;
		}
		if(bombCount == 1) {
			leftTop.getChildren().remove(iBomb2);
			iBomb2 = null;
		}
		if(bombCount == 0) {
			leftTop.getChildren().remove(iBomb3);
			iBomb3 = null;
		}
	}

	private void initWave1(){ //Wave 1
		if(waveNumber==1) { //Set objects specific to wave 1
			wave1Set();
			waveNumber=-1;
			wave1 = true;
		}
		if(wave1 == true) { //Start process when setting is done
			processWave1();
		}

		if(wave1 == true && landerList.size()==0) { //Finish first wave when all landers are dead
			long currentTime = (System.currentTimeMillis()/1000);
			if(wave1con==1) {
			finishTime = (System.currentTimeMillis()/1000);
			wave1con = 0;
			}
			if(currentTime-finishTime==5) { //Wait five seconds and start wave1 final boss
			wave1 = false;
			wave1Final = true;
			boss1 = new GiantM(ship.getX()+400,ship.getY());
			gamePane.getChildren().add(boss1.getImageView());
			}

			}


		if(wave1Final) {
			if(boss1.getLife()<0) { //Wave1Final ending conditions
				wave1Final=false;

				waveNumber = 2; //Pass to other wave
				gamePane.getChildren().remove(boss1.getImageView());
				boss1 = null;
			}
			else {
				processWave1Final(); //Process wave1Final
			}
		}
	}


	private void initWave2() {
		if(waveNumber == 2) {
			int ran = astranoutList.size();
			while(ran>0) {
				removeGameObject(astranoutList.get(0));
				astranoutList.remove(0);
				ran--;
			}
			long currentTime = (System.currentTimeMillis()/1000);
			if(con==0) {
				finishTime = (System.currentTimeMillis()/1000);
				con=1;
			}
			if(currentTime-finishTime==5) {
			wave2Set();
			waveNumber=-1;
			wave2 = true;
			}
		}

		if(wave2) {
			processWave2();
		}
		if(wave2 == true && baiterNumber < 0 && landerList.size()==0) {
			wave2 = false;
			wave2Final = true;
		}
		if(wave2Final) {
			if(wave2con==1) {
				boss1 = new GiantM(ship.getX()+400,ship.getY());
				gamePane.getChildren().add(boss1.getImageView());
				wave2con=0;
			}
			if(boss1.getLife()<0) {
				wave2Final=false;

				waveNumber = 3;
				gamePane.getChildren().remove(boss1.getImageView());
				boss1 = null;
			}
			else {
				processWave1Final();
			}
		}
	}
	private void initWave3() {
		if(waveNumber == 3) {
			int ran = astranoutList.size();
			while(ran>0) {
				removeGameObject(astranoutList.get(0));
				astranoutList.remove(0);
				ran--;
			}
			long currentTime = (System.currentTimeMillis()/1000);
			if(con==1) {
				finishTime = (System.currentTimeMillis()/1000);
				con=0;
			}
			if(currentTime-finishTime==5) {
			wave2Set();
			waveNumber=-1;
			wave3 = true;
			}
		}

		if(wave3) {
			processWave2();
		}
		if(wave3 == true && baiterNumber < -1 && landerList.size()==0) {
			wave3 = false;
			wave3Final = true;
		}
		if(wave3Final) {
			if(wave3con==1) {
				boss1 = new GiantM(ship.getX()+400,ship.getY());
				gamePane.getChildren().add(boss1.getImageView());
				wave3con=0;
			}
			if(boss1.getLife()<0) {
				wave2Final=false;

				waveNumber = 4;
				gamePane.getChildren().remove(boss1.getImageView());
				boss1 = null;
			}
			else {
				processWave1Final();
			}
		}
	}
	private void initWave4() {
		if(waveNumber == 4) {
			int ran = astranoutList.size();
			while(ran>0) {
				removeGameObject(astranoutList.get(0));
				astranoutList.remove(0);
				ran--;
			}
			long currentTime = (System.currentTimeMillis()/1000);
			if(con==0) {
				finishTime = (System.currentTimeMillis()/1000);
				con=1;
			}
			if(currentTime-finishTime==5) {
			wave2Set();
			waveNumber=-1;
			wave4 = true;
			}
		}

		if(wave4) {
			processWave2();
		}
		if(wave4 == true && baiterNumber < -1 && landerList.size()==0) {
			wave4 = false;
			wave4Final = true;
		}
		if(wave4Final) {
			if(wave4con==1) {
				boss1 = new GiantM(ship.getX()+400,ship.getY());
				gamePane.getChildren().add(boss1.getImageView());
				wave4con=0;
			}
			if(boss1.getLife()<0) {
				wave4Final=false;

				waveNumber = 5;
				gamePane.getChildren().remove(boss1.getImageView());
				boss1 = null;
			}
			else {
				processWave1Final();
			}
		}
	}
	private void initWave5() {
		if(waveNumber == 5) {
			int ran = astranoutList.size();
			while(ran>0) {
				removeGameObject(astranoutList.get(0));
				astranoutList.remove(0);
				ran--;
			}
			long currentTime = (System.currentTimeMillis()/1000);
			if(con==1) {
				finishTime = (System.currentTimeMillis()/1000);
				con=0;
			}
			if(currentTime-finishTime==5) {
			wave2Set();
			waveNumber=-1;
			wave5 = true;
			}
		}

		if(wave5) {
			processWave2();
		}
		if(wave5 == true && baiterNumber < -1 && landerList.size()==0) {
			wave5 = false;
			wave5Final = true;
		}
		if(wave5Final) {
			if(wave5con==1) {
				boss1 = new GiantM(ship.getX()+400,ship.getY());
				gamePane.getChildren().add(boss1.getImageView());
				wave5con=0;
			}
			if(boss1.getLife()<0) {
				wave5Final=false;

				waveNumber = 6;
				gamePane.getChildren().remove(boss1.getImageView());
				boss1 = null;
			}
			else {
				processWave1Final();
			}
		}
	}

	private void initWave6() {
		if(waveNumber == 6) {
			int ran = astranoutList.size();
			while(ran>0) {
				removeGameObject(astranoutList.get(0));
				astranoutList.remove(0);
				ran--;
			}
			long currentTime = (System.currentTimeMillis()/1000);
			if(con==0) {
				finishTime = (System.currentTimeMillis()/1000);
				con=1;
			}
			if(currentTime-finishTime==5) {
			wave6Set();
			waveNumber=-1;
			wave6 = true;
			}
		}

		if(wave6) {
			processWave6();
		}
		if(wave6 == true && pod1==null && pod2==null && swarmerList.size()==0) {
			int ran = bomberList.size();
			while(ran>0) {
				removeGameObject(bomberList.get(0));
				bomberList.remove(0);
				ran--;
			}
			wave6 = false;
			wave6Final = true;
		}
		if(wave6Final) {
			if(wave6con==1) {
				boss1 = new GiantM(ship.getX()+400,ship.getY());
				boss1.setBoss2();
				gamePane.getChildren().add(boss1.getImageView());
				wave6con=0;
			}
			if(boss1.getLife()<0) {
				wave6Final=false;

				waveNumber = 7;
				gamePane.getChildren().remove(boss1.getImageView());
				boss1 = null;
			}
			else {
				processWave6Final();
			}
		}
	}

	private void initWave7() {
		if(waveNumber == 7) {
			int ran = astranoutList.size();
			while(ran>0) {
				removeGameObject(astranoutList.get(0));
				astranoutList.remove(0);
				ran--;
			}
			long currentTime = (System.currentTimeMillis()/1000);
			if(con==0) {
				finishTime = (System.currentTimeMillis()/1000);
				con=1;
			}
			if(currentTime-finishTime==5) {
			wave2Set();
			wave6Set();
			waveNumber=-1;
			wave7 = true;
			}
		}

		if(wave7) {
			processWave2();
			processWave6();
		}
		if(wave7 == true && pod1==null && pod2==null && swarmerList.size()==0) {
			int ran = bomberList.size();
			while(ran>0) {
				removeGameObject(bomberList.get(0));
				bomberList.remove(0);
				ran--;
			}
			wave7 = false;
			wave7Final = true;
		}
		if(wave7Final) {
			if(wave7con==1) {
				boss1 = new GiantM(ship.getX()+400,ship.getY());
				boss1.setBoss2();
				gamePane.getChildren().add(boss1.getImageView());
				wave7con=0;
			}
			if(boss1.getLife()<0) {
				wave7Final=false;

				waveNumber = 8;
				gamePane.getChildren().remove(boss1.getImageView());
				boss1 = null;
			}
			else {
				//processFinal();
			}
		}
	}
	
	private void moveLanders() {
		for(int i=0; i<landerList.size(); i++) {
			if(landerList.get(i).getHasAstronaut()==false) {
			// If a lander collides with an astronaut, it abducts it
			if(collisionManager.isCollide(landerList.get(i),astranoutList.get(i)))
				landerList.get(i).abductAstronaut(astranoutList.get(i));
			}
			landerList.get(i).controlAstronaut(); //If the lander hasnt got an astronaut, it can not control it. Specified inside control method

			//Prevent getting out of map
			if(landerList.get(i).getY()==650)
			landerList.get(i).setDirection(-1);
			if(landerList.get(i).getY()==200)
				landerList.get(i).setDirection(1);

			//Move the lander
			landerList.get(i).move();
			}
	}
	
	private void moveBombers() {
		for(int i=0; i<bomberList.size(); i++) {
			//Prevent getting out of map
			if(bomberList.get(i).getY()==650)
				bomberList.get(i).setYDirection(1);
			if(bomberList.get(i).getY()==200)
				bomberList.get(i).setYDirection(-1);
			if(bomberList.get(i).getX()==0)
				bomberList.get(i).setDirection(1);
			if(bomberList.get(i).getX()>1200)
				bomberList.get(i).setDirection(-1);
			//Move the lander
			bomberList.get(i).move();
			}
	}

	private void moveAst() {
		for(int i=0; i<astranoutList.size(); i++) {
			//If an astranout abducted by a lander, and then setted free, it falls
			if(astranoutList.get(i).getOnLand()==true)
				astranoutList.get(i).move(0,1);
			if(astranoutList.get(i).getY()>=700) //Fall to much, delete
			{
				removeGameObject(astranoutList.get(i));
				astranoutList.remove(i);
			}
		}
	}

	private void enemyFire() {
		//Select random lander
		int randEnemy = (int)(Math.random() * landerList.size()) + 0;
		//Select random fire time
		int randomFire = (int)(Math.random() * 100) + 0;
		//Create fire from the coordinates of the lander, direction to ship
		if(randomFire == 5 && landerList.size()!=0) {
		enemyBulletList.add(new Bullet(landerList.get(randEnemy).getX(),landerList.get(randEnemy).getY(),
				(ship.getX()-landerList.get(randEnemy).getX())/200,(ship.getY()-landerList.get(randEnemy).getY())/200));
		gamePane.getChildren().add(enemyBulletList.get(enemyBulletList.size()-1).getImageView());
		}
		int randEnemy2 = (int)(Math.random() * mutantList.size()) + 0;

		if(randomFire == 10 && mutantList.size()!=0) {
			enemyBulletList.add(new Bullet(mutantList.get(randEnemy2).getX(),mutantList.get(randEnemy2).getY(),
					(ship.getX()-mutantList.get(randEnemy2).getX())/200,(ship.getY()-mutantList.get(randEnemy2).getY())/200));
			gamePane.getChildren().add(enemyBulletList.get(enemyBulletList.size()-1).getImageView());
			}
		//Move the fires
		for(int i=0; i<enemyBulletList.size(); i++) {
		enemyBulletList.get(i).move(1, 1);
		enemyBulletList.get(i).countTimer();
		if(enemyBulletList.get(i).getTimer() == 120) {
			removeGameObject(enemyBulletList.get(i));
			enemyBulletList.remove(i);
		}
	}
	}

	private void boss1Fire() {
		//Select random fire time
		int randomFire = (int)(Math.random() * 50) + 0;
		//Create fire from the coordinates of the lander, direction to ship
		if(randomFire == 5 && boss1!=null) {
			if(boss1.getXDirection()>0)
		enemyBulletList.add(new Bullet(boss1.getX()+225,boss1.getY(),
				(ship.getX()-boss1.getX())/200,(ship.getY()-boss1.getY())/200));
			if(boss1.getXDirection()<0)
				enemyBulletList.add(new Bullet(boss1.getX(),boss1.getY(),
						(ship.getX()-boss1.getX())/200,(ship.getY()-boss1.getY())/200));
		enemyBulletList.get(enemyBulletList.size()-1).setSpeed(2);
		enemyBulletList.get(enemyBulletList.size()-1).setBug();
		if(boss1.getXDirection()>0)
		enemyBulletList.add(new Bullet(boss1.getX()+225,boss1.getY()+246,
				(ship.getX()-boss1.getX())/200,(ship.getY()-boss1.getY())/200));
		if(boss1.getXDirection()<0)
			enemyBulletList.add(new Bullet(boss1.getX(),boss1.getY()+246,
					(ship.getX()-boss1.getX())/200,(ship.getY()-boss1.getY())/200));
		enemyBulletList.get(enemyBulletList.size()-1).setSpeed(2);
		enemyBulletList.get(enemyBulletList.size()-1).setBug();
		gamePane.getChildren().add(enemyBulletList.get(enemyBulletList.size()-1).getImageView());
		gamePane.getChildren().add(enemyBulletList.get(enemyBulletList.size()-2).getImageView());
		}
		
		for(int i=0; i<enemyBulletList.size(); i++) {
			enemyBulletList.get(i).move(1, 1);
			enemyBulletList.get(i).countTimer();
			if(enemyBulletList.get(i).getTimer() == 120) {
				removeGameObject(enemyBulletList.get(i));
				enemyBulletList.remove(i);
			}
		}
	}
	
	private void leaveMines() {
		int randEnemy = (int)(Math.random() * bomberList.size()) + 0;
		//Select random fire time
		int randomFire = (int)(Math.random() * 100) + 0;
		//Create fire from the coordinates of the lander, direction to ship
		if(randomFire == 5 && bomberList.size()!=0) {
			mineList.add(new Bullet(bomberList.get(randEnemy).getX(),bomberList.get(randEnemy).getY(),
				1,1));
			mineList.get(mineList.size()-1).setMine();
		gamePane.getChildren().add(mineList.get(mineList.size()-1).getImageView());
		mineList.add(new Bullet(bomberList.get(randEnemy).getX(),bomberList.get(randEnemy).getY(),
				1,-1));
		mineList.get(mineList.size()-1).setMine();
		gamePane.getChildren().add(mineList.get(mineList.size()-1).getImageView());
		mineList.add(new Bullet(bomberList.get(randEnemy).getX(),bomberList.get(randEnemy).getY(),
				-1,1));
		mineList.get(mineList.size()-1).setMine();
		gamePane.getChildren().add(mineList.get(mineList.size()-1).getImageView());
		mineList.add(new Bullet(bomberList.get(randEnemy).getX(),bomberList.get(randEnemy).getY(),
				-1,-1));
		mineList.get(mineList.size()-1).setMine();
		gamePane.getChildren().add(mineList.get(mineList.size()-1).getImageView());
		}
		//Move the fires
		for(int i=0; i<mineList.size(); i++) {
			mineList.get(i).move(1, 1);
			mineList.get(i).countTimer();
		if(mineList.get(i).getTimer() == 120) {
			removeGameObject(mineList.get(i));
			mineList.remove(i);
		}
	}
	}

	private void checkCollision() {

		//Check the collision of ship bullets and the landers
		for(int i=0; i<shipBulletList.size(); i++) {
			for(int j=0; j<landerList.size(); j++) {
				if(collisionManager.isCollide(landerList.get(j),shipBulletList.get(i)))
				{
					if(landerList.get(j).getHasAstronaut()) {
						landerList.get(j).getAstronaut().setFree();
					}
					removeGameObject(landerList.get(j));
					
					rectList.get(j).setFill(Color.BLACK);
					rectList.remove(j);
					removeGameObject(shipBulletList.get(i));
					//shipBulletList.remove(i);
					
					landerList.remove(j);
					ship.setScore(ship.getScore()+150);
					soundManager.play("score");
					System.out.println(landerList.size());
				}
			}
		}

		//Check the collision of enemy bullets and the ship
		for(int i=0; i<enemyBulletList.size(); i++) {
			if(collisionManager.isCollide(ship,enemyBulletList.get(i))) {
			ship.decreaseLife();
			removeGameObject(enemyBulletList.get(i));
			enemyBulletList.remove(i);
			soundManager.play("crash");
			if(lifeThree != null) {
				leftTop.getChildren().remove(lifeThree);
				lifeThree = null;
			}
			else if(lifeTwo != null) {
				leftTop.getChildren().remove(lifeTwo);
				lifeTwo = null;
			}
			else if(lifeOne != null) {
				leftTop.getChildren().remove(lifeOne);
				lifeOne = null;
			}
			}
		}
		
		for(int i=0; i<mineList.size(); i++) {
			if(collisionManager.isCollide(ship,mineList.get(i))) {
				ship.decreaseLife();
			removeGameObject(mineList.get(i));
			mineList.remove(i);
			soundManager.play("crash");
			if(lifeThree != null) {
				gamePane.getChildren().remove(lifeThree);
				lifeThree = null;
			}
			else if(lifeTwo != null) {
				gamePane.getChildren().remove(lifeTwo);
				lifeTwo = null;
			}
			else if(lifeOne != null) {
				gamePane.getChildren().remove(lifeOne);
				lifeOne = null;
			}
			}
		}
		//Check the collision of the astranout and the ship
		for(int i=0; i<astranoutList.size(); i++) {
			if(collisionManager.isCollide(astranoutList.get(i),ship))
			if(astranoutList.get(i).getOnLand()==true) {
				removeGameObject(astranoutList.get(i));
				astranoutList.remove(i);
				ship.setScore(ship.getScore()+500);
				soundManager.play("score");
			}
		}

		//Check the collision of the landers and the ship
		for(int i=0; i<landerList.size(); i++) {
			if(collisionManager.isCollide(ship, landerList.get(i))) {
				removeGameObject(landerList.get(i));
				landerList.remove(i);
				//System.out.println(ship.getNumberOfLives());
				ship.decreaseLife();
				soundManager.play("crash");
				if(lifeThree != null) {
					System.out.println(ship.getNumberOfLives());
					leftTop.getChildren().remove(lifeThree);
					lifeThree = null;
				}
				else if(lifeTwo != null) {
					leftTop.getChildren().remove(lifeTwo);
					lifeTwo = null;
				}
				else if(lifeOne != null) {
					leftTop.getChildren().remove(lifeOne);
					lifeOne = null;
				}
			}
		}

		for(int i=0; i<mutantList.size(); i++) {
			if(collisionManager.isCollide(ship, mutantList.get(i))) {
				ship.decreaseLife();
				ship.setX(40);
				ship.setY(40);
				soundManager.play("crash");
				if(lifeThree != null) {
					leftTop.getChildren().remove(lifeThree);
					lifeThree = null;
				}
				else if(lifeTwo != null) {
					leftTop.getChildren().remove(lifeTwo);
					lifeTwo = null;
				}
				else if(lifeOne != null) {
					leftTop.getChildren().remove(lifeOne);
					lifeOne = null;
				}
			}
		}
		
		for(int i=0; i<swarmerList.size(); i++) {
			if(collisionManager.isCollide(ship, swarmerList.get(i))) {
				ship.decreaseLife();
				ship.setX(40);
				ship.setY(40);
				soundManager.play("crash");
				if(lifeThree != null) {
					leftTop.getChildren().remove(lifeThree);
					lifeThree = null;
				}
				else if(lifeTwo != null) {
					leftTop.getChildren().remove(lifeTwo);
					lifeTwo = null;
				}
				else if(lifeOne != null) {
					leftTop.getChildren().remove(lifeOne);
					lifeOne = null;
				}
			}
		}

		//Check the collision of boss1 and the ship and its bullets
		if(boss1 != null) {
			for(int i=0; i<shipBulletList.size(); i++) {
			if(collisionManager.isCollide(shipBulletList.get(i), boss1)) {
				boss1.decreaseLife();
				removeGameObject(shipBulletList.get(i));
				shipBulletList.remove(i);
			}
			}
			if(collisionManager.isCollide(boss1, ship)) {
				ship.decreaseLife();
				ship.setX(40);
				ship.setY(40);
				soundManager.play("crash");
				if(lifeThree != null) {
					leftTop.getChildren().remove(lifeThree);
					lifeThree = null;
				}
				else if(lifeTwo != null) {
					leftTop.getChildren().remove(lifeTwo);
					lifeTwo = null;
				}
				else if(lifeOne != null) {
					leftTop.getChildren().remove(lifeOne);
					lifeOne = null;
				}
			}
		}
		
		if(bombCollide) {
			for(int j=0; j<landerList.size(); j++) {
				if(collisionManager.isCollide(landerList.get(j),bomb1))
				{
					if(landerList.get(j).getHasAstronaut())
					landerList.get(j).getAstronaut().setFree();
					removeGameObject(landerList.get(j));
					landerList.remove(j);
					ship.setScore(ship.getScore()+150);
					soundManager.play("score");
				}
			}
			
			deleteBomb=true;
			bombCollide = false;
		}

		//Check the collision of baiter and the ship and its bullets
		if(baiter != null) {
			for(int i=0; i<shipBulletList.size(); i++) {
			if(collisionManager.isCollide(shipBulletList.get(i), baiter)) {
				baiter.decreaseLife();
				removeGameObject(shipBulletList.get(i));
				shipBulletList.remove(i);
				soundManager.play("score");
			}
			}
			if(collisionManager.isCollide(baiter, ship)) {
				ship.decreaseLife();
				ship.setX(40);
				ship.setY(40);
				soundManager.play("crash");
				if(lifeThree != null) {
					leftTop.getChildren().remove(lifeThree);
					lifeThree = null;
				}
				else if(lifeTwo != null) {
					leftTop.getChildren().remove(lifeTwo);
					lifeTwo = null;
				}
				else if(lifeOne != null) {
					leftTop.getChildren().remove(lifeOne);
					lifeOne = null;
				}
			}
		}

		//Check the collision of pods and the ship and its bullets
		if(pod1 != null) {
			for(int i=0; i<shipBulletList.size(); i++) {
				if(collisionManager.isCollide(shipBulletList.get(i), pod1)) {
					removeGameObject(shipBulletList.get(i));
					shipBulletList.remove(i);
					swarmerList.add(pod1.releaseSwarms());
					gamePane.getChildren().add(swarmerList.get(swarmerList.size()-1).getImageView());
					pod1.decreaseLife();
					soundManager.play("score");
				}


				}
		}
		if(pod2 != null) {
			for(int i=0; i<shipBulletList.size(); i++) {
		if(collisionManager.isCollide(shipBulletList.get(i), pod2)) {
			removeGameObject(shipBulletList.get(i));
			shipBulletList.remove(i);
			swarmerList.add(pod2.releaseSwarms());
			gamePane.getChildren().add(swarmerList.get(swarmerList.size()-1).getImageView());
			pod2.decreaseLife();
			soundManager.play("score");
		}
			}
		}

		//Check the collision of swarmers and the ship and its bullets
		for(int i=0; i<shipBulletList.size(); i++) {
			for(int j=0; j<swarmerList.size(); j++) {
				if(collisionManager.isCollide(swarmerList.get(j),shipBulletList.get(i)))
				{
					removeGameObject(shipBulletList.get(i));
					shipBulletList.remove(i);
					swarmerList.get(j).decreaseLife();
					ship.setScore(ship.getScore()+150);
					soundManager.play("score");
				}
			}
		}
		
		

		for(int i=0; i<shipBulletList.size(); i++) {
			for(int j=0; j<mutantList.size(); j++) {
				if(collisionManager.isCollide(mutantList.get(j),shipBulletList.get(i)))
				{
					removeGameObject(mutantList.get(j));
					removeGameObject(shipBulletList.get(i));
					shipBulletList.remove(i);
					mutantList.remove(j);
					ship.setScore(ship.getScore()+150);
					soundManager.play("score");
				}
			}
		}

	}

	private void processWave1() {


		moveLanders();

		checkCollision();

		moveAst();

		enemyFire();

		for(int i=0; i<astranoutList.size(); i++) {
			if(astranoutList.get(i).getGrabbes()) {
				astranoutList.get(i).count();
			}

			if(astranoutList.get(i).getGrabbes()== true && astranoutList.get(i).getCount()>1000) {
				astranoutList.get(i).setGrabbed(false);
				gamePane.getChildren().remove(astranoutList.get(i).getImageView());
				mutantList.add(new Mutant(astranoutList.get(i).getX(),astranoutList.get(i).getY()));
				gamePane.getChildren().add(mutantList.get(mutantList.size()-1).getImageView());
			}
		}

		for(int i=0; i<mutantList.size(); i++) {
			if(ship.getX()>mutantList.get(i).getX())
				mutantList.get(i).setXDirection(1);
			else
				mutantList.get(i).setXDirection(-1);

			if(ship.getY()< mutantList.get(i).getY())
				mutantList.get(i).setYDirection(-1);
			else
				mutantList.get(i).setYDirection(1);

			mutantList.get(i).move(1.5,1.5);
		}

	}

	private void processWave2() {
		moveLanders();

		checkCollision();

		moveAst();

		enemyFire();
		if(baiter!=null) {
		if(ship.getX()>baiter.getX())
			baiter.setXDirection(1);
		else
			baiter.setXDirection(-1);

		if(ship.getY()< baiter.getY())
			baiter.setYDirection(-1);
		else
			baiter.setYDirection(1);

		baiter.move(1.5,1.5);
		}

		if(baiter != null && baiter.getLife()<0) {
			gamePane.getChildren().remove(baiter.getImageView());
			baiter = null;
			baiterNumber = baiterNumber - 1;
		}
		checkCollision();

		int random = (int)(Math.random() * 100) + 0;

		if(baiter==null && random==5) {
			baiter = new Baiter(ship.getX()+200,ship.getY());
			gamePane.getChildren().add(baiter.getImageView());
		}
	}

	private void moveShip() {
		if(inputManager.getLeft() && !inputManager.getRight()) {
			ship.setDirection(-1);
			if(inputManager.getDown())
				ship.move(-3, 3);
			else if(inputManager.getUp())
				ship.move(-3,-3);
			else
				ship.move(-3,0);
		}

		else if(inputManager.getRight() && !inputManager.getLeft()) {
			ship.setDirection(1);
			if(inputManager.getDown())
				ship.move(3, 3);
			else if(inputManager.getUp())
				ship.move(3,-3);
			else
				ship.move(3,0);
		}

		else if(inputManager.getDown())
			ship.move(0,3);
		else if(inputManager.getUp())
			ship.move(0, -3);

		if(inputManager.getBoost()) {
			ship.setSpeed(3);
			soundManager.play("speed");
		}
		if(inputManager.getBoost()==false)
			ship.setSpeed(1);
		
		if(inputManager.getHyperJump()) {
			if(ship.getDirection()==1)
			ship.setX(ship.getX()+150);
			else
				ship.setX(ship.getX()-150);
		}
	}

	private void wave1Set() {
		setAstranouts(0,650,10);
		setLanders(0,300,10);
	}

	private void wave2Set() {
		baiterNumber = 5;
		setAstranouts(0,600, 30);
		setLanders(0,300,30);
		baiter = new Baiter(ship.getX()+500,ship.getY());
		gamePane.getChildren().add(baiter.getImageView());
	}

	private void wave6Set() {

		pod1 = new MotherShip(300,200);
		pod2 = new MotherShip(600,200);
		gamePane.getChildren().add(pod1.getImageView());
		gamePane.getChildren().add(pod2.getImageView());
		setBombers(0,300,6);
	}

	private void processWave6() {
		checkCollision();
		for(int i=0; i<swarmerList.size(); i++) {

			if(ship.getX()>swarmerList.get(i).getX())
				swarmerList.get(i).setXDirection(1);
			else
				swarmerList.get(i).setXDirection(-1);

			if(ship.getY()< swarmerList.get(i).getY())
				swarmerList.get(i).setYDirection(-1);
			else
				swarmerList.get(i).setYDirection(1);

			swarmerList.get(i).move(1.5,1.5);


			if(swarmerList.get(i).getLife()<0) {
				removeGameObject(swarmerList.get(i));
				swarmerList.remove(i);
			}
			
			
		}
		if(pod1 != null && pod1.getLife()<0) {
			gamePane.getChildren().remove(pod1.getImageView());
			pod1 = null;
		}
		if(pod2 != null && pod2.getLife()<0) {
			gamePane.getChildren().remove(pod2.getImageView());
			pod2 = null;
		}
		leaveMines();
		moveBombers();
	}

	private void processWave1Final() {
		if(ship.getX()>boss1.getX())
			boss1.setXDirection(1);
		else
			boss1.setXDirection(-1);

		if(ship.getY()< boss1.getY())
			boss1.setYDirection(-1);
		else
			boss1.setYDirection(1);

		boss1.move(1,1);

		checkCollision();

		boss1Fire();
	}
	
	private void processWave6Final(){
		if(ship.getX()>boss1.getX())
			boss1.setXDirection(1);
		else
			boss1.setXDirection(-1);

		if(ship.getY()< boss1.getY())
			boss1.setYDirection(-1);
		else
			boss1.setYDirection(1);

		boss1.move(1,1);

		checkCollision();

		boss1Fire();
	}

	private void setAstranouts(int x, int y, int size) {
		for(int i=0; i<size; i++) {
			astranoutList.add(new Astronaut(x,y));
			gamePane.getChildren().add(astranoutList.get(i).getImageView());
			x = x+400;
		}
	}

	private void setLanders(int x, int y, int size) {
		for(int i=0; i<size; i++) {
			int rand = (int)(Math.random() * 200) + 200;
			landerList.add(new Lander(x,rand));
			gamePane.getChildren().add(landerList.get(i).getImageView());
			double rx = x / 40;
			double ry = (y - 200) / 7.2;
			rect = new Rectangle(rx,ry,4,4);
			rect.setFill(Color.RED);
			rectList.add(rect);
			map.getChildren().add(rect);
			x += 400;
		}
	}

	private void setBombers(int x, int y, int size) {
		for(int i=0; i<size; i++) {
			int rand = (int)(Math.random() * 200) + 200;
			bomberList.add(new Bomber(x,rand));
			gamePane.getChildren().add(bomberList.get(i).getImageView());
			x = x+400;
		}
	}
	private void gameLoop() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				// play screen
				if(layout.getChildren().get(0) == root) {
					if(inputManager.getEsc()) {
						layout.getChildren().remove(0);
						layout.getChildren().add(pausemenu);
					}
					processGame();
				}
				

				// main menu navigation
				if(mainmenu.isPlayClicked()) {
					mainmenu.setPlayClicked(false);
					layout.getChildren().remove(0);
					layout.getChildren().add(root);
				}
				if(mainmenu.isHighScoresClicked()) {
					mainmenu.setHighScoresClicked(false);
					layout.getChildren().remove(0);
					layout.getChildren().add(highscoremenu);
				}
				if(mainmenu.isCreditsClicked()) {
					mainmenu.setCreditsClicked(false);
					layout.getChildren().remove(0);
					layout.getChildren().add(creditsmenu);
				}
				if(mainmenu.isQuitClicked()) {
					gameStage.close();
				}

				if(mainmenu.isSettingsClicked()) {
					mainmenu.setSettingsClicked(false);
					layout.getChildren().remove(0);
					layout.getChildren().add(settingsmenu);
				}
				
				// sub-menus navigation
				if(layout.getChildren().get(0) == pausemenu) {
					if(pausemenu.isExitClicked()) {
						pausemenu.setExitClicked(false);
						layout.getChildren().remove(0);
						layout.getChildren().add(mainmenu);
						ship.setNumberOfLives(0);
					}
					if(pausemenu.isBackClicked()) {
						pausemenu.setBackClicked(false);
						layout.getChildren().remove(0);
						layout.getChildren().add(root);
					}
				}
				
				
				if(layout.getChildren().get(0) == settingsmenu) {
					if(settingsmenu.isBackClicked()) {
						settingsmenu.setBackClicked(false);
						layout.getChildren().remove(0);
						layout.getChildren().add(mainmenu);
					}
				}
				
				if(layout.getChildren().get(0) == highscoremenu) {
					if(highscoremenu.isBackClicked()) {
						highscoremenu.setBackClicked(false);
						layout.getChildren().remove(0);
						layout.getChildren().add(mainmenu);
					}
				}
				if(layout.getChildren().get(0) == creditsmenu) {
					if(creditsmenu.isBackClicked()) {
						creditsmenu.setBackClicked(false);
						layout.getChildren().remove(0);
						layout.getChildren().add(mainmenu);
					}
				}
			}

		};

		timer.start();
	}
	
	
	public void update_scoreLabel() {
		int scr = 0;
		int temp = ship.getScore();
		String score = "";
		if(scr != temp) {
			scr = temp;
			score = Integer.toString(scr);
			score_label.setText(score);
		}
	}

	public void removeGameObject(GameObject obj) {
		gamePane.getChildren().remove(obj.getImageView());
		//obj.setNull();
	}

	public Stage getStage() {
		return gameStage;
	}

	public void init() throws Exception{
		layout = LayoutManager.getInstance();
		mainmenu = layout.getLayoutMain();
		settingsmenu = layout.getLayoutSettings();
		highscoremenu = layout.getLayoutHighScores();
		creditsmenu = layout.getLayoutCredits();
		pausemenu = layout.getLayoutPause();
		root = new VBox();
		canvasI = new Canvas(1280,2);
		gr = canvasI.getGraphicsContext2D();
		gr.setFill(Color.GRAY);
		gr.fillRect(0,100,1280,100);
		gamePane = new Pane();
		gamePane.setPrefSize(3840, 720);
		cvas = new Canvas(3840,720);
		int numOfStars = 210;
		g = cvas.getGraphicsContext2D();
		g.setFill(Color.BLACK);
		g.fillRect(0, 0, 3840, 720);
		g.setFill(Color.WHITE);
		for(int i = 0; i < numOfStars; i++) {
			Random r = new Random();
			int x = r.nextInt(3840); //generate a value between 0 and 100
			int y = r.nextInt(3840);
			g.fillRect(x, y, 6, 5);
		}
		topVBox = new HBox();
		leftTop = new Pane();
		leftTop.setPrefSize(300, 100);
		leftTop.setStyle("-fx-background-color: black;");
		map = new Pane();
		map.setPrefSize(680, 99);
		//map.setStyle("-fx-background-color: black;");
		canvas = new Canvas(680,100);
		gc = canvas.getGraphicsContext2D();
		mapDrawer = canvas.getGraphicsContext2D().getPixelWriter();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, 680, 100);
		map.getChildren().add(canvas);
		
		
		rightTop = new Pane();
		rightTop.setPrefSize(300, 100);
		rightTop.setStyle("-fx-background-color: black;");
		
		clipMin = 0;
		gamePane.getChildren().add(cvas);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		gameScene = new Scene(layout, 1280, 820);
		topVBox.getChildren().addAll(leftTop,map,rightTop);
		
		root.getChildren().add(topVBox);
		root.getChildren().add(canvasI);
		root.getChildren().add(gamePane);
		
		gameStage = primaryStage;
		gameStage.setScene(gameScene);
		gameStage.setResizable(false);
		
		backgroundUrl = null; 
		inputManager = InputManager.getInstance(gameScene);
		collisionManager = CollisionManager.getInstance();
		soundManager = SoundManager.getInstance();
		FileInputStream life = null;
		FileInputStream life1 = null;
		FileInputStream life2 = null;
		FileInputStream fbomb = null;
		FileInputStream fbomb1 = null;
		FileInputStream fbomb2 = null;
		try {
			backgroundUrl = new FileInputStream("./assets/playBG1.png");
			font2 = Font.loadFont(new FileInputStream(new File("./assets/visitor.ttf")), 32);
			life = new FileInputStream("./assets/littleShip.png");
			life1 = new FileInputStream("./assets/littleShip.png");
			life2 = new FileInputStream("./assets/littleShip.png");
			
			fbomb = new FileInputStream("./assets/bomb.png");
			fbomb1 = new FileInputStream("./assets/bomb.png");
			fbomb2 = new FileInputStream("./assets/bomb.png");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image img = new Image(backgroundUrl);
		BackgroundImage image = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		lifeOne = new ImageView(new Image(life1));
		lifeTwo = new ImageView(new Image(life2));
		lifeThree = new ImageView(new Image(life));
		iBomb1 = new ImageView(new Image(fbomb));
		iBomb2 = new ImageView(new Image(fbomb1));
		iBomb3 = new ImageView(new Image(fbomb2));
		
		iBomb1.setX(250);
		iBomb1.setY(0);
		iBomb2.setX(250);
		iBomb2.setY(30);
		iBomb3.setX(250);
		iBomb3.setY(60);
		lifeOne.setX(0);
		lifeOne.setY(0);
		lifeTwo.setX(60);
		lifeTwo.setY(0);
		lifeThree.setX(120);
		lifeThree.setY(0);
		score_label = new Label("000");
		score_label.setFont(font2);
		score_label.setTextFill(Color.web("#ffd500"));
		score_label.setTranslateX(100);
		score_label.setTranslateY(70);
		leftTop.getChildren().addAll(lifeOne,lifeTwo,lifeThree);
		leftTop.getChildren().addAll(iBomb1,iBomb2,iBomb3);
		leftTop.getChildren().add(score_label);
		//gamePane.setBackground(new Background(image));
		//gamePane.setBackground(new Background(new BackgroundFill(Color.web("#0FFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));
		//GraphicsContext gc = new GraphicsContext(null);
		
		clip = new Rectangle();
		//clip.setX(11280);
        clip.widthProperty().bind(gameScene.widthProperty());
        clip.heightProperty().bind(gameScene.heightProperty());
        //double d = ship.getX();
        //clip.xProperty().bind(null);
        		//ship.getX(), 
        //gamePane.setClip(clip);
        gamePane.translateXProperty().bind(clip.xProperty().multiply(-1));

		
        
		initGameObject();
		gameLoop();
		gameStage.show();
	}
	
	private double clampRange(double value, double min, double max) {
	        if (value < min) return min ;
	        if (value > max) return max ;
	        return value ;
	}


	public static void main(String args[]){

	    Application.launch(args);
	    
	}

}
