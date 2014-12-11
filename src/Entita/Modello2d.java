package Entita;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Modello2d {
	//Coordinate nella finestra
	int xScreen,yScreen;
	//Coordinate nella mappa
	int xMap,yMap;
	//Dimensioni immagine
	int width,height;
	//Rotazone dell'immagine
	double rotazione;
	//Immagine
	BufferedImage sprite;	
	//Ciascuno personaggio del nostro gioco ha un'animazione per la camminata
	Animazione camminata;
	//Movimenti
	boolean left;
	boolean right;
	boolean up;
	boolean down;
	//Angoli della mappa nella quale si muove il personaggio
	static final int leftx=0;
	static final int rightx=720;
	static final int topy=0;
	static final int bottomy=1054;
	

	public BufferedImage setSprite(String path){
		try {
			sprite= ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}
	public void setCamminata(BufferedImage sprite, int width, int height){
		/*Ritaglio i frame dallo sprite*/
		BufferedImage[] tmp = new BufferedImage[sprite.getWidth()/height];
		for(int i=0; i<tmp.length; i++){
			/*Ritagliamo gli sprite dal file png di origine uno alla volta*/
			tmp[i] = sprite.getSubimage(i*width,0, width, height);
		}
		/*Imposto l'animazione della camminata*/
		camminata= new Animazione(tmp);
		camminata.calculateDefaultDelay();
		
	}
	public int getXScreen(){ return xScreen; }
	public int getYScreen(){ return yScreen; }
	public int getXMap(){ return xMap; }
	public int getYMap(){ return yMap; }
}