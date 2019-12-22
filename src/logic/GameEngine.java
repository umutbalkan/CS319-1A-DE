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
import entity.Ship;
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
	double yo=0;
	boolean spacePresse = false;
	private int as1;
	private int as1Mut;
	private int asTimer;
	private int a;
	private int b;
	private int con = 1;
	private int boo;
	private int baiterNumber;
	long finishTime;
	public GameEngine() {
		
		start();
	}
	
	private void initGameObject() {
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
		astranoutList = new LinkedList<Astronaut>();
		landerList = new LinkedList<Lander>();
		enemyBulletList = new LinkedList<Bullet>();
		boo=1;
		baiterNumber = 5;
	}
	
	private void processGame() {
		if(ship.getNumberOfLives()!=0) {
		moveShip();
		fireBullet();
		initWave1();
		initWave2();
		initWave3();
		initWave4();
		initWave5();
		}
		}
	

	private void fireBullet() {
		if(inputManager.getBullet()) {
			a = 0;
		}
		if(a==0 && !inputManager.getBullet()) {
			a = 1;
			shipBulletList.add(new Bullet(ship.getX(),ship.getY(),ship.getDirection(),1));
			gamePane.getChildren().add(shipBulletList.get(shipBulletList.size()-1).getImageView());
		}
		
		for(int i=0; i<shipBulletList.size(); i++) {
			shipBulletList.get(i).move(3, 0);
			shipBulletList.get(i).countTimer();
			if(shipBulletList.get(i).getTimer() == 100) {
				removeGameObject(shipBulletList.get(i));
				shipBulletList.remove(i);
			}
		}
	}
	
	private void initWave1(){
		if(waveNumber==1) {
			wave1Set();
			waveNumber=-1;
			wave1 = true;
		}
		if(wave1 == true) {
			processWave1();
		}
		
		if(wave1 == true && landerList.size()==0) {
			long currentTime = (System.currentTimeMillis()/1000);
			if(b==1) {
			finishTime = (System.currentTimeMillis()/1000);
			b = 0;
			}
			if(currentTime-finishTime==5) {
			wave1 = false;
			wave1Final = true;
			boss1 = new GiantM(ship.getX()+400,ship.getY());
			gamePane.getChildren().add(boss1.getImageView());
			}
			
			}
		
		
		if(wave1Final) {
			if(boss1.getLife()<0) {
				wave1Final=false;
				
				waveNumber = 2;
				gamePane.getChildren().remove(boss1.getImageView());
				boss1 = null;
			}
			else {
				processWave1Final();
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
			wave2Set();
			waveNumber=-1;
			wave2 = true;
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
			wave2Set();
			waveNumber=-1;
			wave3 = true;
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
			wave2Set();
			waveNumber=-1;
			wave4 = true;
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
			wave2Set();
			waveNumber=-1;
			wave5 = true;
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
	
	private void moveLanders() {
		for(int i=0; i<landerList.size(); i++) {
			if(landerList.get(i).getHasAstronaut()==false) {
			if(collisionManager.isCollide(landerList.get(i),astranoutList.get(i)))
				landerList.get(i).abductAstronaut(astranoutList.get(i));
			}
			landerList.get(i).controlAstronaut();
			if(landerList.get(i).getY()==650)
			landerList.get(i).setDirection(-1);
			if(landerList.get(i).getY()==200)
				landerList.get(i).setDirection(1);
			
				landerList.get(i).move();
			}
	}
	
	private void moveAst() {
		for(int i=0; i<astranoutList.size(); i++) {
			if(astranoutList.get(i).getOnLand()==true)
				astranoutList.get(i).move(0,1);
			if(astranoutList.get(i).getY()==700)
			{
				removeGameObject(astranoutList.get(i));
				astranoutList.remove(i);
			}
		}
	}
	
	private void enemyFire() {
		int randEnemy = (int)(Math.random() * landerList.size()) + 0;
		int randomFire = (int)(Math.random() * 100) + 0;
		if(randomFire == 5 && landerList.size()!=0) {
		enemyBulletList.add(new Bullet(landerList.get(randEnemy).getX(),landerList.get(randEnemy).getY(),
				(ship.getX()-landerList.get(randEnemy).getX())/200,(ship.getY()-landerList.get(randEnemy).getY())/200));
		gamePane.getChildren().add(enemyBulletList.get(enemyBulletList.size()-1).getImageView());
		}
		for(int i=0; i<enemyBulletList.size(); i++) {
			if(collisionManager.isCollide(ship,enemyBulletList.get(i))) {
				ship.decreaseLife();
			removeGameObject(enemyBulletList.get(i));
			enemyBulletList.remove(i);
			}
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
	
	private void checkCollision() {
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
		
		for(int i=0; i<astranoutList.size(); i++) {
			if(collisionManager.isCollide(astranoutList.get(i),ship))
			if(astranoutList.get(i).getOnLand()==true) {
				removeGameObject(astranoutList.get(i));
				astranoutList.remove(i);
				ship.setScore(ship.getScore()+500);
			}
		}
		
		for(int i=0; i<landerList.size(); i++) {
			if(collisionManager.isCollide(ship, landerList.get(i))) {
				ship.decreaseLife();
				ship.setX(40);
				ship.setY(40);
			}
		}
		
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
		
		if(baiter != null) {
			for(int i=0; i<shipBulletList.size(); i++) {
			if(collisionManager.isCollide(shipBulletList.get(i), baiter)) {
				baiter.decreaseLife();
			}
			}
			if(collisionManager.isCollide(baiter, ship)) {
				ship.decreaseLife();
				ship.setX(40);
				ship.setY(40);
			}
		}
	}
	
	private void processWave1() {
		
		
		moveLanders();
		
		checkCollision();
		
		moveAst();
		
		enemyFire();
		
		
		
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
