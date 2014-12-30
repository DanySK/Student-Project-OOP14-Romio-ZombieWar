package Entita;

import java.util.List;

public class Mitra extends ArmaImpl{
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
			l.add(new Proiettile(g,xMouse,yMouse));
			l.add(new Proiettile(g,xMouse+30,yMouse+30));
			l.add(new Proiettile(g,xMouse-30,yMouse-30));
			this.colpi-=3;
			System.out.println(""+this.colpi);
		}
		return this.colpi;
	}
}
