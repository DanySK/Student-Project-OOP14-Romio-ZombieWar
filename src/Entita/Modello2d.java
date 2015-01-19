package Entita;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Modello2d {
	//Coordinate nella finestra
	protected double xScreen,yScreen;
	//Coordinate nella mappa
	protected double xMap,yMap;
	//Dimensioni immagine
	protected int width,height;
	//Rotazone dell'immagine
	protected double rotazione;
	//Immagine
	protected BufferedImage sprite;	
	//Ciascuno personaggio del nostro gioco ha un'animazione per la camminata
	protected Animazione camminata;
	//Movimenti
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	//Angoli della mappa nella quale si muove il personaggio
	protected static final int leftx=0;
	protected static final int rightx=720;
	protected static final int topy=0;
	protected static final int bottomy=1054;
	//Campi del personaggio
	protected double hp;
	protected boolean alive=true;

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
	
	public boolean intersects(Modello2d o){
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}

	public Rectangle getRectangle(){
		return new Rectangle((int)xMap,(int)yMap,width,height);
	}
	
	public double getXScreen(){
		return xScreen;
	}
	
	public double getYScreen(){
		return yScreen;
	}
	
	public double getXMap(){
		return xMap;
	}
	
	public double getYMap(){
		return yMap;
	}
	
	public boolean isAlive(){
		return this.alive;
	}
	
	public double getHp(){
		return this.hp;
	}
	
	public abstract void update();
	public abstract void draw(Graphics2D g);
}