package Armi;
import java.util.List;

import Entita.Giocatore;
import Entita.Proiettile;

public class Mitra extends ArmaImpl{
	private double newX;
	private double newY;
	public Mitra(){
		this.danno = 8;
		this.caricatore = 60;
		this.colpi = 60;
		this.x = 7;
		this.y = 17;
		this.setImage("/sprites/minigun.png");
	}
	@Override
	public int shoot(Giocatore g,double xMouse,double yMouse,List<Proiettile>l) {
		if(this.colpi>0){
			this.reloading=false;
			/*Aggiungiamo i tre proiettili*/
			this.colpi-=3;
			/**
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
