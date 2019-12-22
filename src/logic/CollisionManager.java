package logic;

import entity.GameObject;

public class CollisionManager {
	private static CollisionManager instance = null;
	
	public CollisionManager() {
	}
	
	public static CollisionManager getInstance() {
        if (instance == null) 
            instance = new CollisionManager(); 
  
        return instance; 
    }
	
	public boolean isCollide(GameObject a, GameObject b) {
		return a.getX() < b.getX() + b.getWidth() && a.getX() + a.getWidth() > b.getX() && a.getY() < b.getY() + b.getHeight() && a.getY() + a.getHeight() > b.getY();
	}
}