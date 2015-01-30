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
			audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);			
		}catch(Exception ex){
			ex.printStackTrace();
		}	

	}
	
	/**
	 *  Play the sound one time 
	 */
	
	public void start(){
		  try {		 
			  clip.start();
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		
	}
	
	/**
	 *  Stop the sound one time 
	 */
	
	public void stop(){
		try{
			clip.setFramePosition(1);
		}catch( Exception e){
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
}
