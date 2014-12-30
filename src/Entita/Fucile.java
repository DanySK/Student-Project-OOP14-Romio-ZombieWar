package Entita;

import java.util.List;

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
			l.add(new Proiettile(g,xMouse,yMouse));
			this.colpi--;
			System.out.println(""+this.colpi);
		}
		return this.colpi;
	}
}
