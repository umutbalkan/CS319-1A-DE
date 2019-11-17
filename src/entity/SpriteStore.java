package entity;
import java.util.HashMap<K,V>;
/*
 * Public class of SpriteStore
 * */
public class SpriteStore {

    private static SpriteStore sSoleInstance = new SpriteStore();
    
    private HashMap sprites;
    private Sprite sprite;
    //private constructor.
    private SpriteStore(){}

    public static SpriteStore getInstance() {
        return sSoleInstance;
    }
    
    public Sprite getSprite(String ref){
      
    }
    
    private void fail(String msg){
      
    }
}