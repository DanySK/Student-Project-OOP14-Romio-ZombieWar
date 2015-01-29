package entities;

import java.awt.Graphics2D;

public interface DisplayObject {
	/**
	 * Initialize the component
	 */
	public void init();
	/**
	 * Methos called in the main thread ruotine, it contains operation to calculate
	 */
	public void update();
	/**
	 * 
	 * @param g rapresent the graphic component of the Panel
	 */
	public void draw(Graphics2D g);
}
