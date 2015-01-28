package entities;

import java.awt.image.BufferedImage;

public class Animation {
	/**
	 * This class define all the basics animations of the sprites moving in our game.
	 * Ex. Given all the frames about the walk animation of the player, it
	 * return single frame one by one.
	 * 
	 * @author Giovanni Romio
	 */
	private BufferedImage[] frames;
	private int currentFrame;	
	private long startTime;
	private long delay;
	
	/**
	 * 
	 * @param frames contains all the sprite necessary to make the animation
	 */

	public Animation(BufferedImage[] frames){
		/*Load all the frames for the animation*/
		this.frames= frames;
		currentFrame = 0;
		startTime = System.nanoTime();
	}
	/**
	 * 
	 * @param d is the delay, rapresent how fast will the sprite be changed
	 */
	public void setDelay(long d) {
		delay = d;
	}
	/**
	 * 
	 * @param i set the currentFrame to a specific value
	 */
	public void setFrame(int i) { 
		currentFrame = i;
	}
	public void calculateDefaultDelay(){
		/*Optimal delay for the walk animation in 5 frame*/
		delay= 100;
	}
	/**
	 * This method allow programmer to set a different framerate for the animation 
	 * @param delay rapresent how fast will the frame be switched
	 */
	public void setDelay(int delay){
		this.delay=delay;
	}
	
	public void update() {
		/*if delay isn't set*/
		if(delay == -1) return;	
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if (elapsed > delay) {
			/*Switch to next frame*/
			currentFrame ++;
			startTime = System.nanoTime();
		}
		if(currentFrame == frames.length) {
			/*repeat animation cicle*/
			currentFrame = 0;
		}		

	}
	/**
	 * @return the frame to display on the screen 
	 */
	public int getFrame () {
		return currentFrame;
	}
	/**
	 * 
	 * @return the current frame to diplay in the screen
	 * @throws IndexOutOfBoundsException
	 */
	public BufferedImage getImage() throws IndexOutOfBoundsException { 
		return frames[currentFrame]; 
	}
}

