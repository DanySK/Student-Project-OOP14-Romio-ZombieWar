package Entita;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Modello2d {
	//Coordinate nella finestra
	double xScreen,yScreen;
	//Coordinate nella mappa
	double xMap,yMap;
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
	public boolean intesects(Modello2d o){
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}

	public Rectangle getRectangle(){
		return new Rectangle((int)xMap,(int)yMap,width,height);
	}
	public abstract void update();
	public abstract void draw(Graphics2D g);

	public double getXScreen(){ return xScreen; }
	public double getYScreen(){ return yScreen; }
	public double getXMap(){ return xMap; }
	public double getYMap(){ return yMap; }
}