package Entita;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
public class HUD {
	/**
	 * This class is used to display the player status, current weapon and the status of the Base.
	 * It is still incompled, must replace Weapon Name and bullet numbers
	 * 
	 *  @autor Giovanni Romio
	 */	

	private Giocatore giocatore;
	private Base base;
	/*Entità da visualizzare nell'HUD*/
	private int vitaBase;
	private int vitaGiocatore;
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
		this.giocatore = Giocatore.getIstance();
		this.base = Base.getIstance();
				
	}
	public void update(){
		
		vitaBase = base.getVita();
		vitaGiocatore = (int)giocatore.getHp();
	}
	public void draw(Graphics2D g){
		
		g.setColor(HUDColor);
		g.setFont(HUDFont);
		//DISEGNARE NOME ARMA
		g.drawString(giocatore.getWeapon().getWeaponName(), 5, 20);
		// DISEGNARE ARMA
		g.drawImage(giocatore.getWeapon().getHUDImage(), 5, 25,null);
		// DISEGNARE COLPI
		if(!giocatore.isRealoading())
			g.drawString("X "+ giocatore.getWeapon().getColpi(), 150,80);
		else g.drawString("Reloading", 150, 80);
		//DISEGNARE PUNTI VITA
		g.drawString("HP: "+vitaGiocatore, 5, 110);
		g.drawString("BASE: "+vitaBase, 5, 140);
	}
	
}
