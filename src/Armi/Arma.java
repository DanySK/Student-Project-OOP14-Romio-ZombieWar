package Armi;
import java.awt.image.BufferedImage;
import java.util.List;

import Entita.Giocatore;
import Entita.Proiettile;


public interface Arma {
	public void reload();
	public void setImage(String path);
	public BufferedImage getImage();
	public int shoot(Giocatore g, double xMouse, double yMouse,List<Proiettile>l);
}
