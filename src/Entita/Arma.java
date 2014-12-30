package Entita;
import java.awt.image.BufferedImage;
import java.util.List;


public interface Arma {
	public void reload();
	public void setImage(String path);
	public BufferedImage getImage();
	public int shoot(Giocatore g, double xMouse, double yMouse,List<Proiettile>l);
}
