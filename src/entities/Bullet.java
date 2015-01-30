package entities;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends SpriteObject {
	
	/**
	 * This class rapreset a asingle bullet of the game
	 * 
	 * @author Giovanni Romio
	 */
	
	private double rapporto;
	private int damage;
	
	/**
	 * 
	 * @param g rapresent the Player
	 * @param xCursore rapresent xMouse coordinate
	 * @param yCursore rapresent yMouse coordinate
	 * @param damage rapresent damage of the current weapon
	 */
	public Bullet(Player g,double xCursore,double yCursore,int damage){
		try {
			this.sprite = ImageIO.read(getClass().getResourceAsStream("/sprites/proiettile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.damage = damage;
		rapporto = Math.atan2(yCursore - (g.getYScreen() + g.height / 2), (xCursore - (g.getXScreen() + g.width / 2)));
		this.xScreen = g.getXScreen() + g.width / 2;
		this.yScreen = g.getYScreen() + g.height / 2;
		this.xMap = g.getXMap() + g.width / 2;
		this.yMap = g.getYMap() + g.height / 2;
	}
	/**
	 * Calculate the position of the Bullet:
	 * Start>> Player(X,Y)
	 * Forward>> Crosshair(X,Y)
	 */
	private void calculatePosition(){
		/** Calcoliamo la taiettoria del proiettile */
		this.xScreen += 20 * Math.cos(rapporto);
		this.yScreen += 20 * Math.sin(rapporto);
		this.xMap += 20 * Math.cos(rapporto);
		this.yMap += 20 * Math.sin(rapporto);
	}
	
	public void update(){
		this.calculatePosition();
	}
	
	public void draw(Graphics2D g){
		g.drawImage(sprite, (int)xScreen, (int)yScreen, null);
	}
	/**
	 * 
	 * @return the position of the bullet in the Map
	 */
	public Point getPosition(){
		return new Point((int)xMap,(int)yMap);
	}
	
	/**
	 * 
	 * @return the damage associated to this bullet
	 */
	public int getDamage(){
		return damage;
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
