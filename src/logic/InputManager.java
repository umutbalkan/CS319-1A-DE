package logic;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class InputManager {
	private static InputManager instance = null;
	
	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private boolean isUpKeyPressed;
	private boolean isDownKeyPressed;
	private boolean isBoostKeyPressed;
	private boolean isHyperJumpPressed;
	private boolean isBulletPressed;
	private boolean isBombPressed;
	public InputManager(Scene gameScene) {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent arg0) {
				if(arg0.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed=true;
				}
				else if(arg0.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed=true;
				}
				else if(arg0.getCode() == KeyCode.UP) {
					isUpKeyPressed=true;
				}
				else if(arg0.getCode() == KeyCode.DOWN) {
					isDownKeyPressed=true;
				}
				else if(arg0.getCode() == KeyCode.SHIFT) {
					isBoostKeyPressed=true;
				}
				else if(arg0.getCode() == KeyCode.W) {
					isHyperJumpPressed=true;
				}
				else if(arg0.getCode() == KeyCode.Q) {
					isBombPressed=true;
				}
				else if(arg0.getCode() == KeyCode.SPACE) {
					isBulletPressed=true;
					System.out.println("spacepressed");
				}
			}
			
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				if(arg0.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed=false;
				}
				else if(arg0.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed=false;
				}
				else if(arg0.getCode() == KeyCode.UP) {
					isUpKeyPressed=false;
				}
				else if(arg0.getCode() == KeyCode.DOWN) {
					isDownKeyPressed=false;
				}
				else if(arg0.getCode() == KeyCode.SHIFT) {
					isBoostKeyPressed=false;
				}
				else if(arg0.getCode() == KeyCode.W) {
					isHyperJumpPressed=false;
				}
				else if(arg0.getCode() == KeyCode.Q) {
					isBombPressed=false;
				}
				else if(arg0.getCode() == KeyCode.SPACE) {
					isBulletPressed=false;
					System.out.println("spacereleased");
				}
			}
			
		});
	}
	
	public static InputManager getInstance(Scene gameScene) {
        if (instance == null) 
            instance = new InputManager(gameScene); 
  
        return instance; 
    }
	
	public boolean getLeft() {
		return isLeftKeyPressed;
	}
	
	public boolean getRight() {
		return isRightKeyPressed;
	}
	
	public boolean getUp() {
		return isUpKeyPressed;
	}
	
	public boolean getDown() {
		return isDownKeyPressed;
	}
	
	public boolean getBoost() {
		return isBoostKeyPressed;
	}
	
	public boolean getHyperJump() {
		return isHyperJumpPressed;
	}
	
	public boolean getBomb() {
		return isBombPressed;
	}
	
	public boolean getBullet() {
		return isBulletPressed;
	}
}