package MotorediGioco;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import SessioniDiGioco.ControllerDiSessione;

@SuppressWarnings("serial")
public class PannelloDiGioco extends JPanel implements Runnable,KeyListener,MouseListener,MouseMotionListener {
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
	private static PannelloDiGioco p;
	private PannelloDiGioco(){
		super();
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		/*Per utilizzare il keyListener dobbiamo imporre il requestFocus al nostro JPanel*/
		setFocusable(true);
		requestFocus();
		/*Inizializziamo il gameThread*/		
		if(gameThread== null){
			gameThread= new Thread(this);
			/*Aggiungiamo i controlli per le periferiche*/	
			addKeyListener(this);
			addMouseListener(this);
			addMouseMotionListener(this);
			gameThread.start();
		}	

	}

	public static PannelloDiGioco getIstanceof(){
		if(PannelloDiGioco.p == null){
			p = new PannelloDiGioco();
		}
		return p;
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
	public Graphics2D getgraphics(){
		return g;
	}

	@Override
	public void run() {
		this.init();
		long start;
		long elapsed;
		long wait;
		while(running){	
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
			elapsed = System.nanoTime() - start;
			/*Detraggo al tempo di attesa standard la durata dell'ultime 3 operazioni*/
			wait = targetTime - elapsed/1000000;
			if(wait < 0) wait =0;
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}			
	}
	/*MOUSE*/
	@Override
	public void mousePressed(MouseEvent e) {
		cds.mouseClicked(e.getX(),e.getY());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		cds.mouseReleased();
	}	

	@Override
	public void mouseMoved(MouseEvent e) {
		try{
			cds.MouseMovement(e.getX(), e.getY());	
		}catch(NullPointerException n){			
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}	
	/*KEYBOARD*/
	@Override
	public void keyPressed(KeyEvent e) {
		try {
			cds.keyPressed(e.getKeyCode());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		cds.keyReleased(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
