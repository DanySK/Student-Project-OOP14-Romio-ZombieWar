package Entita;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Proiettile extends Modello2d {
	private double rapporto;
	public Proiettile(Giocatore g,int xCursore,int yCursore){
		try {
			this.sprite = ImageIO.read(getClass().getResourceAsStream("/sprites/proiettile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		rapporto = Math.atan2((yCursore-g.getYScreen()),(xCursore-g.getXScreen()));
		this.xScreen = g.getXScreen();
		this.yScreen = g.getYScreen();
	}
	public void calcolaPosizione(){
		/*Calcoliamo la taiettoria del proiettile*/
		this.xScreen += 5*Math.cos(rapporto);
		this.yScreen += 5*Math.sin(rapporto);
	}
}
