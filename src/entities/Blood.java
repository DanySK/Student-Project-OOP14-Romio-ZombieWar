package entities;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Blood extends SpriteObject {
	
	/**
	 * Blood of the zombie while hitten
	 * 
	 * @author Giovanni Romio
	 */
	
	private BufferedImage blood;
	private PlayerImpl player;
	
	/**
	 * 
	 * @param xMap x Coordinate of the blood in the Map
	 * @param yMap y Coordinate of the blood in the Map
	 * @param xScreen  x Coordinate of the blood in the Screen
	 * @param yScreen  y Coordinate of the blood in the Screen
	 * 
	 */
	
	public Blood(double xMap, double yMap, double xScreen, double yScreen){
		this.xMap = xMap;
		this.xScreen = xScreen;
		this.yMap = yMap;
		this.yScreen = yScreen;
		/*Carichiamo l'immagine*/
		try{
			blood = ImageIO.read(getClass().getResourceAsStream("/sprites/sangue.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
		player = PlayerImpl.getIstance();
	}
	
	public void init() {
		
	}
	
	public void update() {		
	}
	  
	public void draw(Graphics2D g) {
		AffineTransform at = new AffineTransform();;
		/**
		 * Disegniamo il sangue solo se e nel campo di visibilita del giocatore 
		 */		
		if(player.getXMap() > 320 && player.getXMap() < 410){
			xScreen =  320 + (xMap - player.getXMap());
		}
		if(player.getYMap() > 240 && player.getYMap() < 814){
			yScreen = 240 + (yMap - player.getYMap());
		}
		at.translate(xScreen, yScreen);
		g.drawImage(blood, at , null);		
	}

	

}
