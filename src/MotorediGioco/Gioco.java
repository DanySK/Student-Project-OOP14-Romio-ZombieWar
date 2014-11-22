package MotorediGioco;

import javax.swing.JFrame;


public class Gioco {
	public static void main(String[] args){
		//Creo il frame del gioco utilizzando
		JFrame gameFrame = new JFrame("ProgettoOOP-ZombieWar");
		gameFrame.setContentPane(new PannelloDiGioco());
	}
}
