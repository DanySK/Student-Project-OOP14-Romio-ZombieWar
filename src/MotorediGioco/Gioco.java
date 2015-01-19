package MotorediGioco;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Gioco {
	public static void main(String[] args){
		//Creo il frame del gioco utilizzando
		JFrame gameFrame = new JFrame("ProgettoOOP-ZombieWar");
		gameFrame.setContentPane(PannelloDiGioco.getIstanceof());		
		gameFrame.setResizable(false);
		gameFrame.pack();	
		gameFrame.setVisible(true);
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		// Create a new blank cursor.		 
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");	
		// Set the blank cursor to the JFrame.
		gameFrame.getContentPane().setCursor(blankCursor);
		 
		
	}
}
