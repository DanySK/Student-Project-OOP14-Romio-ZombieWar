package Armi;

import java.util.List;
import Entita.Giocatore;
import Entita.Proiettile;

public class Pistola extends ArmaImpl {
	/**
	 * This is a small gun, with low bullets capacity and low damage
	 * 
	 *  @author Giovanni Romio
	 */
	public Pistola(){
		this.danno = 5;
		this.caricatore = 15;
		this.colpi = 15;
		this.x = 10;
		this.y = 20;
		this.setImage("/sprites/glock21.png");
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
			System.out.println(""+this.colpi);
		}
		return this.colpi;
	}


}
