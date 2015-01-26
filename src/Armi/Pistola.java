package Armi;

import java.util.List;

import javax.imageio.ImageIO;

import Entita.Giocatore;
import Entita.Proiettile;

public class Pistola extends ArmaImpl {
	/**
	 * This is a small gun, with low bullets capacity and low damage
	 * 
	 *  @author Giovanni Romio
	 */
	public Pistola(){
		this.nome = "GLOCK 21";
		this.danno = 5;
		this.caricatore = 15;
		this.colpi = 15;
		this.x = 10;
		this.y = 20;
		try{
			sprite = ImageIO.read(getClass().getResourceAsStream("/sprites/glock21.png"));
			HUDsprite =  ImageIO.read(getClass().getResourceAsStream("/sprites/weaponsHUD/glock21.png"));
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
	public int shoot(Giocatore g,double xMouse,double yMouse,List<Proiettile>l) {
		
		if(this.colpi>0){
			this.reloading=false;
			/* Add one single bullet */
			this.colpi--;
			l.add(new Proiettile(g,xMouse,yMouse,danno));
		}
		return this.colpi;
	}


}
