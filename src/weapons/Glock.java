package weapons;

import java.util.List;

import javax.imageio.ImageIO;

import audio.AudioPlayer;
import entities.Bullet;
import entities.Player;

public class Glock extends WeaponImpl {
	
	/**
	 * This is a small gun, with low bullets capacity and low damage
	 * 
	 *  @author Giovanni Romio
	 */
	
	public Glock(){		
		this.name = "GLOCK 21";
		this.damage = 5;
		this.bulletsPerRound = 15;
		this.bullets = 15;
		this.x = 10;
		this.y = 20;
		try{
			sprite = ImageIO.read(getClass().getResourceAsStream("/sprites/glock21.png"));
			HUDsprite =  ImageIO.read(getClass().getResourceAsStream("/sprites/weaponsHUD/glock21.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
		ap = new AudioPlayer("/audio/weaponSound.wav");
	}
	
	/**
	 * This metod let the player to shoot adding bullets to the current gameSession.
	 * @param g rapresent the player
	 * @param xMouse Xcoordinate of the Mouse relative to the JFrame
	 * @param yMouse Ycoordinate of the Mouse relative to the JFrame
	 * @param l rapresent the List wich contains all the bullets that are current displayed 
	 */
	
	public int shoot(Player g,double xMouse,double yMouse,List<Bullet>l) {
		if(this.bullets>0){
			this.reloading=false;
			this.ap.start();
			/* Add one single bullet */
			this.bullets--;
			l.add(new Bullet(g,xMouse,yMouse,damage));
		}
		return this.bullets;
	}
}
