package Armi;
import java.util.List;

import Entita.Giocatore;
import Entita.Proiettile;

public class Mitra extends ArmaImpl{
	/**
	 * This class rapresent the machinegun, it's a weapon that shoot 3 bullet at same time
	 * 
	 *  @author Giovanni Romio
	 */
	
	private double newX;
	private double newY;
	/**
	 * Define the gun properties, could implements more dettails as accuracy and realoadTime
	 */
	public Mitra(){
		this.danno = 8;
		this.caricatore = 60;
		this.colpi = 60;
		this.x = 7;
		this.y = 17;
		this.setImage("/sprites/minigun.png");
	}
	/**
	 * This metod let the player to shoot adding bullets to the current gameSession.
	 * This gun can shoot up to 3 bullets at the same time.
	 * @param g rapresent the player
	 * @param xMouse Xcoordinate of the Mouse relative to the JFrame
	 * @param yMouse Ycoordinate of the Mouse relative to the JFrame
	 * @param l rapresent the List wich contains all the bullets that are current displayed
	 */
	public int shoot(Giocatore g,double xMouse,double yMouse,List<Proiettile>l) {
		if(this.colpi>0){
			this.reloading=false;
			/*Add 3 bullets*/
			this.colpi-=3;
			/*
			 * Per Ottenere gli altri due colpi:
			 * Trasliamo il cursore all'origine
			 * Ruotiamo attorno all'origine dell'angolo ricercato
			 * Trasliamo nuovamente nella posizione originale
			 */
			l.add(new Proiettile(g,xMouse,yMouse,8));
			newX = g.getXScreen() + (xMouse-g.getXScreen())*Math.cos(0.5) - (yMouse-g.getYScreen())*Math.sin(0.5);
			newY = g.getYScreen() + (xMouse-g.getXScreen())*Math.sin(0.5) + (yMouse-g.getYScreen())*Math.cos(0.5);
			l.add(new Proiettile(g,newX,newY,danno));
			newX = g.getXScreen() + (xMouse-g.getXScreen())*Math.cos(-0.5) - (yMouse-g.getYScreen())*Math.sin(-0.5);
			newY = g.getYScreen() + (xMouse-g.getXScreen())*Math.sin(-0.5) + (yMouse-g.getYScreen())*Math.cos(-0.5);
			l.add(new Proiettile(g,newX,newY,danno));
			System.out.println(""+this.colpi);
			System.out.println(""+l.size());
		}
		return this.colpi;
	}
}
