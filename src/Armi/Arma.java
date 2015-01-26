package Armi;
import java.awt.image.BufferedImage;
import java.util.List;

import Entita.Giocatore;
import Entita.Proiettile;


public interface Arma {
	/**
	 * This is the interface for each Weapon in our game, it implements methods for
	 * set up the image of the gun and other stuff as reload and shoot.
	 * 
	 * @author Giovanni Romio
	 * 
	 */
	public void reload();
	public BufferedImage getImage();
	public int shoot(Giocatore g, double xMouse, double yMouse,List<Proiettile>l);
}
