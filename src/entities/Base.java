package entities;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Base{
	/**
	 * This is the Base, the player must defende his base while trying to stay alive.
	 * The base is defined by a polygon and other stuff is to describe the position on the
	 * map and if it's alive or not.
	 * 
	 * This class use the SingleTon pattern: there is only one istance of this class.
	 * 
	 *  @author GiovanniRomio
	 */
	private static Base base;
	private Polygon p;
	/*Coordinate della base*/
	private int [] xPoint = {200,220,270,470,555,555}; 
	private int [] yPoint = {0,80,120,120,80,0};
	private int hp;
	private boolean alive;
	
	private Base(){
		p = new Polygon(xPoint,yPoint,6);
	}
	/**
	 * 
	 * @return SingleTon istance
	 */
	public static Base getIstance(){
		if(base == null){
			base = new Base();
		}
		return base;
	}
	/**
	 * Initialize the base
	 */
	public void init(){
		hp = 100;
		alive = true;
	}
	/**
	 * @param o rapresent the collision box of the zombies
	 * */
	public boolean intersect(Rectangle o){
		return p.intersects(o);
	}
	
	public void update() {
	}
	/**
	 * 
	 * @param g rapresent the graphic component of the JFrame
	 */
	public void draw(Graphics2D g){
		g.draw(p);
	}
	/**
	 * 
	 * @return the helt poin of the base
	 */
	public int getHp(){
		return hp;
	}
	/**
	 *  @return the position of the Base in the Map
	 */
	public Polygon getCollisionPolygon(){
		return p;
	}
	/**
	 * 
	 * @param d is the damage of the zombie, different zombie make different damage
	 */
	public void hit(double d){
		if(hp == 0 ){
			alive = false;
		}else{
			hp -= d;
		}		
	}
	/**
	 *  @return true if the base has any health point left otherwise return false
	 */
	public boolean isAlive(){
		return alive;
	}	
	
}
