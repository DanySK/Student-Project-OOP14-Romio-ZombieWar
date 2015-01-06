package Armi;

import java.util.List;

import Entita.Giocatore;
import Entita.Proiettile;

public class Mitra extends ArmaImpl{
	private double rapporto;
	public Mitra(){
		this.danno = 8;
		this.caricatore = 50;
		this.colpi = 50;
		this.x = 7;
		this.y = 17;
		this.setImage("/sprites/minigun.png");
	}
	@Override
	public int shoot(Giocatore g,double xMouse,double yMouse,List<Proiettile>l) {
		if(this.colpi>0){
			this.reloading=false;
			/*Aggiungiamo un singolo proiettile*/
			this.colpi-=3;
			rapporto = Math.atan2(yMouse, xMouse);
			l.add(new Proiettile(g,xMouse,yMouse,8));
			l.add(new Proiettile(g,xMouse+50,yMouse+50,danno));
			l.add(new Proiettile(g,xMouse-50,yMouse-50,danno));
			System.out.println(""+this.colpi);
		}
		return this.colpi;
	}
}
