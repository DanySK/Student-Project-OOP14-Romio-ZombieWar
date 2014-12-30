package Entita;

import java.util.List;

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
			/*Aggiungiamo un singolo proiettile*/
			l.add(new Proiettile(g,xMouse,yMouse));
			this.colpi--;
			System.out.println(""+this.colpi);
		}
		return this.colpi;
	}


}
