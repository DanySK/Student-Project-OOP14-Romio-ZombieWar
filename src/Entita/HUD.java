package Entita;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class HUD {
	/*Entità da visualizzare nell'HUD*/
	private Base b;
	private Giocatore p;
	private int vitaBase;
	private int vitaGiocatore;
	private BufferedImage[] armi;
	/*Grafica dell'HUD*/
	private Color HUDColor;
	private Font HUDFont;
	
	public HUD(){
		this.b = Base.getIstance();
		this.p = Giocatore.getIstance();
		/*Import delle immagini*/
		/*Modifica font e colore scritte dell'HUD*/
		HUDColor = new Color(255,255,255);
		HUDFont = new Font("AR DESTINE",Font.PLAIN,20);
	}
	public void update(){
		vitaBase = b.getVita();
		vitaGiocatore = (int)p.getHp();
	}
	public void draw(Graphics2D g){
		g.setColor(HUDColor);
		g.setFont(HUDFont);
		//DISEGNARE NOME ARMA
		g.drawString("Test", 5, 20);
		// DISEGNARE ARMA
		// DISEGNARE COLPI
		
		//DISEGNARE PUNTI VITA
		g.drawString("HP: "+vitaGiocatore, 5, 110);
		g.drawString("BASE: "+vitaBase, 5, 140);
	}
	
}
