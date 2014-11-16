package MotorediGioco;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PanelGame extends JPanel implements Runnable {
	//Dimensioni
	private static final int WIDTH = 640;
	private static final int HEIGTH = 480;
	//Componenti per stampare gli sprite
	private Graphics2D g;
	private BufferedImage image;
	//GameThread
	private Thread gameThread;
	private boolean running;
	private int FPS = 60;
	
	public PanelGame(){
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	}
		
	public void run() {
		while(running){
			
		}
		
	}
}
