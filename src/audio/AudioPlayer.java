package audio;

import javax.sound.sampled.*;

public class AudioPlayer {
	
	/**
	 * Class to play wav samples sounds
	 * 
	 * @author GiovanniRomio
	 */
	
	private Clip clip;
	private AudioInputStream audioInputStream;
	
	/**
	 * 
	 * @param path contain the path to get to the sound file
	 */
	
	public AudioPlayer(String path){

		try{
			audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);			
		}catch(Exception e){			
		}	

	}
	
	/**
	 *  Play the sound one time 
	 */
	
	public void start(){
		  try {
			  clip.setFramePosition(0);
			  clip.start();
		  }catch(Exception e){			  
		  }
		
	}
	
	/**
	 *  Stop the sound one time 
	 */
	
	public void stop(){
		try{
			clip.stop();
		}catch( Exception e){			
		}
	}
	
	/**
	 * Methos used in case of background sound
	 * @param n how many time the sound will be played
	 */
	
	public void loop(int n){
		try{
			clip.loop(100);
		}catch( Exception e){			
		}
	}
}
