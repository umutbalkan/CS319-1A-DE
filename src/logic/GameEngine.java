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
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import entity.Astronaut;
import entity.Baiter;
import entity.Bullet;
import entity.GameObject;
import entity.GiantM;
import entity.Lander;
import entity.MotherShip;
import entity.Mutant;
import entity.Ship;
import entity.Swarmer;
import io.HighScoreManager;

public class GameEngine{
	
	
	private HighScoreManager highscoreManager;
	private InputManager inputManager;
	private CollisionManager collisionManager;
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private AnimationTimer timer;
	
	private Ship ship;
	private LinkedList<Bullet> shipBulletList;
	private LinkedList<Astronaut> astranoutList;
	private LinkedList<Lander> landerList;
	private LinkedList<Bullet> enemyBulletList;
	private LinkedList<Swarmer> swarmerList;
	private LinkedList<Mutant> mutantList;
	private MotherShip pod1;
	private MotherShip pod2;
	private GiantM boss1;
	private Baiter baiter;
	private int bulletNumber;
	private FileInputStream backgroundUrl;
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
	public GameEngine() {
		
		start();
	}
	
	private void initGameObject() {
		//Create lists, ship, wave conditions
		
		ship = new Ship(40,40);
		gamePane.getChildren().add(ship.getImageView());
		shipBulletList = new LinkedList<Bullet>();
		a = 1;
		b = 1;
		as1 = 0;
		as1Mut = 0;
		asTimer = 0;
		bulletNumber = 0;
		wave1= false;
		wave2 = false;
		waveNumber = 1;
		wave1Final = false;
		wave2Final = false;
		wave3 = false;
		wave3Final = false;
		wave4 = false;
		wave4Final = false;
		wave5 = false;
		wave5Final = false;
		wave6 = false;
		wave6Final = false;
		astranoutList = new LinkedList<Astronaut>();
		landerList = new LinkedList<Lander>();
		enemyBulletList = new LinkedList<Bullet>();
		swarmerList = new LinkedList<Swarmer>();
		mutantList = new LinkedList<Mutant>();
		boo=1;
		baiterNumber = 5;
		con=0;
	}
	
	private void processGame() {
		//Process method of the game
		if(ship.getNumberOfLives()!=0) {
		//MoveShip and FireBullet runs in every wave
		moveShip();
		fireBullet();
		
		//Start first wave
		initWave1();
		//Start second wave
		initWave2();
		initWave3();  //To start from a wave, set waveNumber to its wave, and make comment the upper init methods
		initWave4();
		initWave5();
		
		initWave6();
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
		}
		
		for(int i=0; i<shipBulletList.size(); i++) { //Move the active bullets
			shipBulletList.get(i).move(3, 0);
			shipBulletList.get(i).countTimer();
			if(shipBulletList.get(i).getTimer() == 100) { //Delete the bullet if it goes too much
				removeGameObject(shipBulletList.get(i));
				shipBulletList.remove(i);
			}
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
			if(b==1) {
			finishTime = (System.currentTimeMillis()/1000);
			b = 0;
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
			if(b==0) {
				boss1 = new GiantM(ship.getX()+400,ship.getY());
				gamePane.getChildren().add(boss1.getImageView());
				b=1;
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
			if(b==1) {
				boss1 = new GiantM(ship.getX()+400,ship.getY());
				gamePane.getChildren().add(boss1.getImageView());
				b=0;
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
			if(b==0) {
				boss1 = new GiantM(ship.getX()+400,ship.getY());
				gamePane.getChildren().add(boss1.getImageView());
				b=1;
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
			if(b==1) {
				boss1 = new GiantM(ship.getX()+400,ship.getY());
				gamePane.getChildren().add(boss1.getImageView());
				b=0;
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
	
	private void checkCollision() {
		
		//Check the collision of ship bullets and the landers
		for(int i=0; i<shipBulletList.size(); i++) {
			for(int j=0; j<landerList.size(); j++) {
				if(collisionManager.isCollide(landerList.get(j),shipBulletList.get(i)))
				{
					if(landerList.get(j).getHasAstronaut())
					landerList.get(j).getAstronaut().setFree();
					removeGameObject(landerList.get(j));
					removeGameObject(shipBulletList.get(i));
					shipBulletList.remove(i);
					landerList.remove(j);
					ship.setScore(ship.getScore()+150);
				}
			}
		}
		
		//Check the collision of enemy bullets and the ship
		for(int i=0; i<enemyBulletList.size(); i++) {
			if(collisionManager.isCollide(ship,enemyBulletList.get(i))) {
				ship.decreaseLife();
			removeGameObject(enemyBulletList.get(i));
			enemyBulletList.remove(i);
			}
			}
		
		//Check the collision of the astranout and the ship
		for(int i=0; i<astranoutList.size(); i++) {
			if(collisionManager.isCollide(astranoutList.get(i),ship))
			if(astranoutList.get(i).getOnLand()==true) {
				removeGameObject(astranoutList.get(i));
				astranoutList.remove(i);
				ship.setScore(ship.getScore()+500);
			}
		}
		
		//Check the collision of the landers and the ship
		for(int i=0; i<landerList.size(); i++) {
			if(collisionManager.isCollide(ship, landerList.get(i))) {
				ship.decreaseLife();
				ship.setX(40);
				ship.setY(40);
			}
		}
		
		for(int i=0; i<mutantList.size(); i++) {
			if(collisionManager.isCollide(ship, mutantList.get(i))) {
				ship.decreaseLife();
				ship.setX(40);
				ship.setY(40);
			}
		}
		
		//Check the collision of boss1 and the ship and its bullets
		if(boss1 != null) {
			for(int i=0; i<shipBulletList.size(); i++) {
			if(collisionManager.isCollide(shipBulletList.get(i), boss1)) {
				boss1.decreaseLife();
				
			}
			}
			if(collisionManager.isCollide(boss1, ship)) {
				ship.decreaseLife();
				ship.setX(40);
				ship.setY(40);
			}
		}
		
		//Check the collision of baiter and the ship and its bullets
		if(baiter != null) {
			for(int i=0; i<shipBulletList.size(); i++) {
			if(collisionManager.isCollide(shipBulletList.get(i), baiter)) {
				baiter.decreaseLife();
				removeGameObject(shipBulletList.get(i));
				shipBulletList.remove(i);
			}
			}
			if(collisionManager.isCollide(baiter, ship)) {
				ship.decreaseLife();
				ship.setX(40);
				ship.setY(40);
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
			System.out.println(baiterNumber);
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
		
		if(inputManager.getBoost())
			ship.setSpeed(2);
		if(inputManager.getBoost()==false)
			ship.setSpeed(1);
	}
	
	private void wave1Set() {
		setAstranouts(0,600,6);
		setLanders(0,300,6);
	}

	private void wave2Set() {
		baiterNumber = 5;
		setAstranouts(0,600, 6);
		setLanders(0,300,6);
		baiter = new Baiter(ship.getX()+500,ship.getY());
		gamePane.getChildren().add(baiter.getImageView());
	}
	
	private void wave6Set() {
		
		pod1 = new MotherShip(ship.getX()+100,ship.getY()+50);
		pod2 = new MotherShip(ship.getX()+200,ship.getY()+100);
		gamePane.getChildren().add(pod1.getImageView());
		gamePane.getChildren().add(pod2.getImageView());
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
		
		
	}
	
	private void setAstranouts(int x, int y, int size) {
		for(int i=0; i<size; i++) {
			astranoutList.add(new Astronaut(x,y));
			gamePane.getChildren().add(astranoutList.get(i).getImageView());
			x = x+200;
		}
	}
	
	private void setLanders(int x, int y, int size) {
		for(int i=0; i<size; i++) {
			int rand = (int)(Math.random() * 200) + 200; 
			landerList.add(new Lander(x,rand));
			gamePane.getChildren().add(landerList.get(i).getImageView());
			x = x+200;
		}
	}
	
	private void start() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, 600, 800);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
		
		backgroundUrl = null;
		try {
			backgroundUrl = new FileInputStream("./assets/playBG.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BackgroundImage image = new BackgroundImage(new Image(backgroundUrl), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		inputManager = InputManager.getInstance(gameScene);
		collisionManager = CollisionManager.getInstance();
		gamePane.setBackground(new Background(image));
		initGameObject();
		gameLoop();
		gameStage.show();
	}
	
	private void gameLoop() {
		timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				processGame();
			}
			
		};
		
		timer.start();
	}
	
	public void removeGameObject(GameObject obj) {
		gamePane.getChildren().remove(obj.getImageView());
		obj.setNull();
	}
	
	public Stage getStage() {
		return gameStage;
	}
	
	
}
