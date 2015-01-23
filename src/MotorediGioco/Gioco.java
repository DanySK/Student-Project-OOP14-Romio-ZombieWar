package MotorediGioco;

import java.awt.Cursor;
import javax.swing.JFrame;


public class Gioco {
	public static void main(String[] args){
		//Creo il frame del gioco utilizzando
		JFrame gameFrame = new JFrame("ProgettoOOP-ZombieWar");
		gameFrame.setContentPane(PannelloDiGioco.getIstanceof());		
		gameFrame.setResizable(false);
		gameFrame.pack();	
		gameFrame.setVisible(true);
		//Impostiamo un mirino al posto della freccia
		gameFrame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		
	}
}
