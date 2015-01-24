package Entita;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
public class HUD {
	/*Entità da visualizzare nell'HUD*/
	private int vitaBase;
	private int vitaGiocatore;
	//private BufferedImage[] armi;
	/*Grafica dell'HUD*/
	private Color HUDColor;
	private Font HUDFont;
	
	public HUD(){
		/*Import delle immagini*/
		/*Modifica font e colore scritte dell'HUD*/
		HUDColor = new Color(255,255,255);
		try {
			HUDFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/TrueLies.ttf")).deriveFont(12f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	public void update(Base b, Giocatore g){
		vitaBase = b.getVita();
		vitaGiocatore = (int)g.getHp();
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
