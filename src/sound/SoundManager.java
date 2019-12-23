package sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SoundManager {
	private static SoundManager instance = null;
	private Media sound;
	private MediaPlayer mediaPlayer;
	private Media mainSound;
	private MediaPlayer mainMediaPlayer;
	public SoundManager() {
		mainSound = new Media(new File("./assets/music.mp3").toURI().toString());
		mainMediaPlayer = new MediaPlayer(mainSound);
		mainMediaPlayer.play();
	}
	
	public static SoundManager getInstance() {
        if (instance == null) 
            instance = new SoundManager(); 
  
        return instance; 
    }
	
	public void applySettings() {
		
	}
	
	public void play(String in) {
		sound = new Media(new File("./assets/"+in+".mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		if(mediaPlayer.getStatus()==MediaPlayer.Status.PLAYING)
			mediaPlayer.stop();
		
		mediaPlayer.play();
	}
	
	public void stopMainMusic() {
		mainMediaPlayer.stop();
	}
	
}