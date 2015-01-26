package Entita;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sangue extends Modello2d{
	private BufferedImage sangue;
	private Giocatore giocatore;
	public Sangue(double xMap, double yMap, double xScreen, double yScreen){
		this.xMap = xMap;
		this.xScreen = xScreen;
		this.yMap = yMap;
		this.yScreen = yScreen;
		/*Carichiamo l'immagine*/
		try{
			sangue = ImageIO.read(getClass().getResourceAsStream("/sprites/sangue.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
		giocatore = Giocatore.getIstance();
	}
	
	public void update() {		
	}
	  
	public void draw(Graphics2D g) {
		AffineTransform at = new AffineTransform();;
		/**
		 * Disegniamo il sangue solo se e nel campo di visibilita del giocatore 
		 */		
		if(giocatore.getXMap() > 320 && giocatore.getXMap() < 410){
			xScreen =  320 + (xMap - giocatore.getXMap());
		}
		if(giocatore.getYMap() > 240 && giocatore.getYMap() < 814){
			yScreen = 240 + (yMap - giocatore.getYMap());
		}
		at.translate(xScreen, yScreen);
		g.drawImage(sangue, at , null);		
	}

}
