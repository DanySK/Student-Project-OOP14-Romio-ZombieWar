package Entita;

import java.awt.image.BufferedImage;

public class Animazione {
	
	private BufferedImage[] frames;
	private int currentFrame;	
	private long startTime;
	private long delay;
	
	public Animazione(BufferedImage[] frames){
		/*Carica i frame per l'animazione*/
		this.frames= frames;
		currentFrame = 0;
		startTime = System.nanoTime();
	}		
	public void setDelay(long d) { delay = d; }
	public void setFrame(int i) { currentFrame = i; }
	public void calculateDefaultDelay(){
		/*Tempo assegnato a ciascun frame di default: cambio animazione ogni 1/60 sec*/
		delay= 1000/60;
	}
	public void setDelay(int delay){
		/*Possibilità di impostare il framerate per la nostra animazione a piacere*/
		this.delay=delay;
	}
	public void update() {
		/*Se il delay non è stato impostato*/
		if(delay == -1) return;	
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if (elapsed > delay) {
			/*Passiamo al frame successivo*/
			currentFrame ++;
			startTime = System.nanoTime();
		}
		if(currentFrame == frames.length) {
			/*Ripetiamo il ciclo dei frame dell'animazione*/
			currentFrame = 0;
		}		
			
	}
	public int getFrame () { return currentFrame; }
	public BufferedImage getImage() { return frames[currentFrame]; }
}

