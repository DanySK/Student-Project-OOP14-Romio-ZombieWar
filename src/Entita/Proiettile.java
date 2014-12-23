package Entita;

import java.awt.Graphics2D;
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
		rapporto = Math.atan2((yCursore-(g.getYScreen()+g.height/2)),(xCursore-(g.getXScreen()+g.width/2)));
		this.xScreen = g.getXScreen()+g.width/2;
		this.yScreen = g.getYScreen()+g.height/2;
	}
	public void calcolaPosizione(){
		/*Calcoliamo la taiettoria del proiettile*/
		this.xScreen += 5*Math.cos(rapporto);
		this.yScreen += 5*Math.sin(rapporto);
	}
	public void update(){
		this.calcolaPosizione();
	}
	public void draw(Graphics2D g){
		g.drawImage(sprite,(int)xScreen,(int)yScreen,null);
	}
	
}
