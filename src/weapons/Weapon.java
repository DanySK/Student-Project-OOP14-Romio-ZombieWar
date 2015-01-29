package weapons;
import java.awt.image.BufferedImage;
import java.util.List;

import entities.Bullet;
import entities.PlayerImpl;


public interface Weapon {
	/**
	 * This is the interface for each Weapon in our game, it implements methods for
	 * set up the image of the gun and other stuff as reload and shoot.
	 * 
	 * @author Giovanni Romio
	 * 
	 */
	public void reload();
	public BufferedImage getImage();
	/**
	 * Makes the gun fire
	 * 
	 * @param g is the Player
	 * @param xMouse is the x location of the mouse in the Frame
	 * @param yMouse is the y location of the mouse in the Frame
	 * @param l is the list wich contains all bullets displayer
	 * @return
	 */
	public int shoot(PlayerImpl g, double xMouse, double yMouse,List<Bullet>l);
}
