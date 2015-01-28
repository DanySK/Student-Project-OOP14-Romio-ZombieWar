package weapons;

import java.util.List;

import javax.imageio.ImageIO;

import entities.Bullet;
import entities.Player;
import weapons.ArmaImpl;

public class Fucile extends ArmaImpl {
	public Fucile(){
		this.nome = "AK 47";
		this.danno = 10;
		this.caricatore = 30;
		this.colpi = 30;
		this.x = 7;
		this.y = 17;
		try{
			sprite = ImageIO.read(getClass().getResourceAsStream("/sprites/ak47.png"));
			HUDsprite =  ImageIO.read(getClass().getResourceAsStream("/sprites/weaponsHUD/ak47.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * This metod let the player to shoot adding bullets to the current gameSession.
	 * @param g rapresent the player
	 * @param xMouse Xcoordinate of the Mouse relative to the JFrame
	 * @param yMouse Ycoordinate of the Mouse relative to the JFrame
	 * @param l rapresent the List wich contains all the bullets that are current displayed 
	 */
	public int shoot(Player g,double xMouse,double yMouse,List<Bullet>l) {
		if(this.colpi>0){
			this.reloading=false;
			/* Add one single bullet */
			this.colpi--;
			l.add(new Bullet(g,xMouse,yMouse,danno));
		}
		return this.colpi;
	}
	
}
