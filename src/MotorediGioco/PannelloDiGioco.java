package MotorediGioco;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import SessioniDiGioco.ControllerDiSessione;

@SuppressWarnings("serial")
public class PannelloDiGioco extends JPanel implements Runnable,KeyListener,MouseListener {
	//Dimensioni
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	//Componenti per stampare gli sprite
	private Graphics2D g;
	private BufferedImage image;
	//GameThread
	private Thread gameThread;
	private boolean running;	
	private int FPS = 60;
	/*Quanto tempo deve passare tra una draw() e l'altra*/
	private long targetTime = 1000/FPS;
	/*Controller di Sessione*/
	private ControllerDiSessione cds;
	
	public PannelloDiGioco(){
		super();
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		/*Per utilizzare il keyListener dobbiamo imporre il requestFocus al nostro JPanel*/
		setFocusable(true);
		requestFocus();
		this.init();
		/*Inizializziamo il gameThread*/
		if(gameThread== null){
			gameThread= new Thread(this);
			/*Aggiungiamo i controlli per le periferiche*/		
			addKeyListener(this);
			addMouseListener(this);
			gameThread.start();
		}				
	}
	
	private void init(){
		/*Inizializziamo l'immagine di default e il componente g che permettono di stampare
		i vari sprites nel gioco nel nostro schermo */
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g= (Graphics2D) image.getGraphics();
		/*Inizializiamo la variabile per avviare il gameThread*/
		running = true;
		/* Creiamo un'istanza di cds che permette di effettuare lo switch tra le varie sessioni
		di gioco */
		cds = new ControllerDiSessione();

	}	
	
	private void update(){
		/*Il gamePanel richiama il metodo update del controllerDiSessione che a sua volta ritrasmette
		tale compito alla Sessione di Gioco interessata*/
		cds.update();
	}
	private void draw(){
		cds.draw(this.g);
	}
	private void drawToScreen(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0,WIDTH,HEIGHT, null);
		g2.dispose();
	}
		
	public void run() {
		this.init();
		long start;
		long elapsed;
		long wait;
		while(running){	
			start = System.currentTimeMillis();
			update();
			draw();
			drawToScreen();
			elapsed = System.currentTimeMillis() - start;
			/*Detraggo al tempo di attesa standard la durata dell'ultime 3 operazioni*/
			wait = targetTime - elapsed;
			if(wait < 0) wait = 5;
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
				
	}
	/*MOUSE*/
	public void mouseClicked(MouseEvent e) {
		cds.mouseClicked();
	}
	
	public void mouseEntered(MouseEvent e) {
			
	}
	
	public void mouseExited(MouseEvent e) {
			
	}
	
	public void mousePressed(MouseEvent e) {
			
	}
	
	public void mouseReleased(MouseEvent e) {
			
	}
	/*KEYBOARD*/
	public void keyPressed(KeyEvent e) {
		cds.keyPressed(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		cds.keyReleased(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {
		
	}
}
