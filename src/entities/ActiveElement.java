package entities;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public interface ActiveElement {
	
	/**
	 * This elements contains an animation made by sprites
	 */
	public BufferedImage setSprite(String path);
	
	/**
	 * Subdivide one BufferedImage in more frames. Those frame showed on sequence will
	 * make the walk animation
	 * 
	 *  @param sprite contain all the sprtes of the animation
	 *  @width width of the single frame
	 *  @height height of the single frame
	 */
	
	public void setWalkAnimation(BufferedImage sprite, int width, int height);
	
	/**
	 * 
	 * @param o rapresent the model to analize
	 * @return true if o intersect the current object
	 */
	
	public boolean intersects(Sprite o);
	
	/**
	 * 
	 * @return the Rectangle associated to this object
	 */
	
	public Rectangle getRectangle();
	public double getXScreen();
	public double getYScreen();
	public double getXMap();
	public double getYMap();
	public boolean isAlive();
	public double getHp();

}
