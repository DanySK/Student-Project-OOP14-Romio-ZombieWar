package Armi;

import java.util.List;

import Entita.Giocatore;
import Entita.Proiettile;

public class Pistola extends ArmaImpl {
	public Pistola(){
		this.danno = 5;
		this.caricatore = 15;
		this.colpi = 15;
		this.x = 10;
		this.y = 20;
		this.setImage("/sprites/glock21.png");
	}
	@Override
	public int shoot(Giocatore g,double xMouse,double yMouse,List<Proiettile>l) {
		if(this.colpi>0){
			this.reloading=false;
			/** Aggiungiamo un singolo proiettile */
			this.colpi--;
			l.add(new Proiettile(g,xMouse,yMouse,danno));
			System.out.println(""+this.colpi);
		}
		return this.colpi;
	}


}
