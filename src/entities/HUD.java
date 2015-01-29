package entities;

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

	private Player player;
	private Base base;
	private int baseHp;
	private int playerHp;
	private Color HUDColor;
	private Font HUDFont;
	
	public HUD(){
		/*
		 * Import images
		 * Modify font and color
		 */
		HUDColor = new Color(255,255,255);
		try {
			HUDFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/TrueLies.ttf")).deriveFont(12f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		this.player = Player.getIstance();
		this.base = Base.getIstance();
				
	}
	
	/**
	 * This method is called by the main thread
	 */
	
	public void update(){
		
		baseHp = base.getVita();
		playerHp = (int) player.getHp();
	}
	
	/**
	 * 
	 * @param g is the grapich componenet of the main Jframe
	 */
	
	public void draw(Graphics2D g){
		
		g.setColor(HUDColor);
		g.setFont(HUDFont);
		//DRAW WEAPON NAME
		g.drawString(player.getWeapon().getWeaponName(), 5, 20);
		// DRAW WEAPON IMAGE
		g.drawImage(player.getWeapon().getHUDImage(), 5, 25,null);
		// DRAW BULLETS
		if(!player.getWeapon().isReloading())
			g.drawString("X "+ player.getWeapon().getColpi(), 150,80);
		else g.drawString("Reloading", 150, 80);
		//DRAW HEALT POINT
		g.drawString("HP: "+playerHp, 5, 110);
		g.drawString("BASE: "+baseHp, 5, 140);
	}
	
}
