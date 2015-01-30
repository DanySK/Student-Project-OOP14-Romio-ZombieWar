package weapons;

import java.util.List;

import javax.imageio.ImageIO;

import audio.AudioPlayer;
import entities.Bullet;
import entities.Player;
import weapons.WeaponImpl;

public class AK47 extends WeaponImpl {
	
	public AK47(){
		this.name = "AK 47";
		this.damage = 10;
		this.bulletsPerRound = 30;
		this.bullets = 30;
		this.x = 7;
		this.y = 17;
		try{
			sprite = ImageIO.read(getClass().getResourceAsStream("/sprites/ak47.png"));
			HUDsprite =  ImageIO.read(getClass().getResourceAsStream("/sprites/weaponsHUD/ak47.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
		//load sound
		this.ap = new AudioPlayer("/audio/weaponSound.wav");
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
			this.ap.stop();
			this.ap.start();
			/* Add one single bullet */
			this.bullets--;
			l.add(new Bullet(g,xMouse,yMouse,damage));
		}
		return this.bullets;
	}
	
}
