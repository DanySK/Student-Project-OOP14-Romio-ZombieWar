package Entita;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Proiettile extends Modello2d {
	private double rapporto;
	private int danno;
	public Proiettile(Giocatore g,double xCursore,double yCursore,int danno){
		try {
			this.sprite = ImageIO.read(getClass().getResourceAsStream("/sprites/proiettile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.danno = danno;
		rapporto = Math.atan2(yCursore-(g.getYScreen()+g.height/2),(xCursore-(g.getXScreen()+g.width/2)));
		this.xScreen = g.getXScreen()+g.width/2;
		this.yScreen = g.getYScreen()+g.height/2;
		this.xMap = g.getXMap()+g.width/2;
		this.yMap = g.getYMap()+g.height/2;
	}
	public void calcolaPosizione(){
		/*Calcoliamo la taiettoria del proiettile*/
		this.xScreen += 10*Math.cos(rapporto);
		this.yScreen += 10*Math.sin(rapporto);
		this.xMap += 10*Math.cos(rapporto);
		this.yMap += 10*Math.sin(rapporto);
	}
	public void update(){
		this.calcolaPosizione();
	}
	public void draw(Graphics2D g){
		g.drawImage(sprite,(int)xScreen,(int)yScreen,null);
	}
	public Point getPosition(){
		return new Point((int)xMap,(int)yMap);
	}
	public int getDanno(){
		return this.danno;
	}
}
