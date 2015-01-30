package gameEngine;

import java.awt.Cursor;
import javax.swing.JFrame;


public class Main {
	
	/**
	 * Main Class of the game, instance a Jframe and a Jpanel
	 * 
	 * @author Giovanni Romio
	 * 
	 */
	
	/**
	 * 
	 * @param args contains the supplied command-line arguments as an array of String objects
	 */
	public static void main(String[] args){
		/* Create gameFrame */
		JFrame gameFrame = new JFrame("ProgettoOOP-ZombieWar");
		gameFrame.setContentPane(GamePanel.getIstanceof());		
		gameFrame.setResizable(false);
		gameFrame.pack();	
		gameFrame.setVisible(true);
		/* Setting crosshair cursor */
		gameFrame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		/* Terminate all process of the game*/
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
