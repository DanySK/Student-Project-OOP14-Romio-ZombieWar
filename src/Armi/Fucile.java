package Armi;

import java.util.List;

import Armi.ArmaImpl;
import Entita.Giocatore;
import Entita.Proiettile;

public class Fucile extends ArmaImpl {
	public Fucile(){
			this.danno = 10;
			this.caricatore = 30;
			this.colpi = 30;
			this.x = 7;
			this.y = 17;
			this.setImage("/sprites/ak47.png");
	}
	@Override
	public int shoot(Giocatore g,double xMouse,double yMouse,List<Proiettile>l) {
		if(this.colpi>0){
			this.reloading=false;
			/*Aggiungiamo un singolo proiettile*/
			this.colpi--;
			l.add(new Proiettile(g,xMouse,yMouse,danno));
			System.out.println(""+this.colpi);
		}
		return this.colpi;
	}
}
