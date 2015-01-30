package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class SpriteObject {
	
	/**
	 * Absrtarct class define 2dModels as Player or Zombies
	 * 
	 * @author Giovanni Romio
	 */	
	
	/* Coordinate nella finestra */
	protected double xScreen,yScreen;
	/* Coordinate nella mappa */
	protected double xMap,yMap;
	/* Dimensioni immagine */
	protected int width,height;
	/* Rotazone dell'immagine */
	protected double rotation;
	/* Immagine */
	protected BufferedImage sprite;	
	/* Ciascuno personaggio del nostro gioco ha un'animazione per la camminata */
	protected Animation walk;
	/*Object velocity while moving*/
	protected double speed;
	/* Campi del personaggio */
	protected int hp;
	protected boolean alive = true;

	public BufferedImage setSprite(String path){
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}
	
	/**
	 * Subdivide one BufferedImage in more frames. Those frame showed on sequence will
	 * make the walk animation
	 * 
	 *  @param sprite contain all the sprtes of the animation
	 *  @param width width of the single frame
	 *  @param height height of the single frame
	 */
	
	public void setWalkAnimation(BufferedImage sprite, int width, int height){
		/**Cut frame sprite from original image*/
		BufferedImage[] tmp = new BufferedImage[sprite.getWidth() / height];
		for(int i = 0; i < tmp.length; i++){			
			tmp[i] = sprite.getSubimage(i*width, 0, width, height);
		}
		/** Set walk animation*/
		walk = new Animation(tmp);
		walk.calculateDefaultDelay();
	}	
	/**
	 * 
	 * @param o rapresent the model to analize
	 * @return true if o intersect the current object
	 */
	
	public boolean intersects(SpriteObject o){
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}

	/**
	 * 
	 * @return the Rectangle associated to this object
	 */	
	public Rectangle getRectangle(){
		return new Rectangle((int)xMap,(int)yMap,width,height);
	}
	
	/**
	 * 
	 * @return xPosition on the screen
	 */	
	public double getXScreen(){
		return xScreen;
	}
	
	/**
	 * 
	 * @return yPosition on the screen
	 */	
	public double getYScreen(){
		return yScreen;
	}
	
	/**
	 * 
	 * @return xPosition on the Map
	 */	
	public double getXMap(){
		return xMap;
	}
	
	/**
	 * 
	 * @return yPosition on the Map
	 */
		public double getYMap(){
		return yMap;
	}
	
	/**
	 * 
	 * @return if the Object is alive
	 */	
	public boolean isAlive(){
		return this.alive;
	}
	
	/**
	 * 
	 * @return the Health point of the current object
	 */	
	public double getHp(){
		return this.hp;
	}
	
	/**
	 * Initialize the component
	 */	
	public abstract  void init();
	
	/**
	 * Methos called in the main thread ruotine, it contains operation to calculate
	 */	
	public abstract void update();
	
	/**
	 * 
	 * @param g rapresent the graphic component of the Panel
	 */	
	public abstract void draw(Graphics2D g);
	
}