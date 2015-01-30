package gameSessions;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.*;

import entities.Base;
import entities.Blood;
import entities.Bullet;
import entities.EnrageZombie;
import entities.HUD;
import entities.MyMap;
import entities.MyMapImpl;
import entities.Player;
import entities.Zombie;
import entities.ZombieMom;
import threads.BulletThread;
import threads.ZombieThread;

public class LevelOne extends GameSession{
	
	/**
	 *  Level one, this is the blueprint for each other levels.
	 *  This level istance 2 different Thread:
	 *  ZombieThread: call the update methods for each zombie
	 *  ProiettileThread: call the update methods for each bullets in the Frame
	 *  Printing object in the JPanel is still assigned to the main Thread.
	 *  
	 *  @author Giovanni Romio
	 */
	
	
	/* Map */
	private MyMap map;
	/* Zombie */
	private List<Zombie> zombies;
	private static final int NUMZOMBIE = 50;
	private int zombieCreated = 0;
	/* Sangue */
	private List<Blood> sangue;
	/* Base da difendere */
	private Base base;
	/* Thread degli zombie */
	private Thread t;
	private ZombieThread zt;
	/* Thread proiettili */
	private Thread p;
	private BulletThread pt;
	/* Proiettili */
	private List<Bullet> proiettili;
	/* HUD di gioco */
	private HUD h;
	private boolean pause = false;
	/* Posizione del mouse */
	private int xMouse;
	private int yMouse;
	/*Timer per la creazione di zombie */
	private long start;
	private long delay = 500;
	/**
	 * 
	 * @param cds is thse SessionController wich allow to switch between session
	 */
	
	public LevelOne(SessionController cds){
		
		this.cds = cds;			
		/*Inizializziamo la mappa*/
		map = new MyMapImpl("/backgrounds/map.png");		
		/*Inizializziamo la base*/
		base = Base.getIstance();
		base.init();		
		/*Inizializziamo il giocatore*/
		player = Player.getIstance();
		player.init();				
		/*Inizializziamo la lista degli zombie*/
		zombies = Collections.synchronizedList(new ArrayList<Zombie>());	
		/*Inizializziamo la lista dei proiettili*/
		proiettili =  Collections.synchronizedList(new ArrayList<Bullet>()) ;
		/*Inizializziamo la lista delle chiazze di sangue*/
		sangue = Collections.synchronizedList(new ArrayList<Blood>());		
		/*Inizializziamo il thread per lo zombie*/		
		zt = new ZombieThread(zombies,30);
		t = new Thread(zt);
		t.start();		
		/*Inizializziamo il thread per i proiettili*/
		pt = new BulletThread(proiettili,zombies,sangue);
		p = new Thread(pt);
		p.start();		
		/*Inizializziamo l'HUD di gioco*/
		h = new HUD();
		/* Inizializziamo il timer*/
		start = System.currentTimeMillis();
	}
	
	/**
	 * Everytime we Pause/Unpause the game the threads stops to run thanks to a
	 * flag variable. 
	 */
	
	public void init(){
		if(this.pause == true){
			/*togliamo la pause*/
			zt.setPausa(false);
			pt.setPausa(false);
		}
	}
	
	/*Create a zombie to the Left Spawn */
	private void zombieLeftSpawn(){
		/*Left spawn*/
		Random rn = new Random();
		int n = 1000;
		int j = (rn.nextInt() % n)+100;
		synchronized (zombies) {
			if(j % 2 != 0){
				zombies.add(new ZombieMom(-500,Math.abs(j)));
			}else{
				zombies.add(new EnrageZombie(-500,Math.abs(j)));
			}				
		}
	}
	/*Create a zombie to the Bottom Spawn */
	private void zombieBottomSpawn(){
		/*Bottom spawn*/
		Random rn = new Random();
		int n = 700;
		int j = (rn.nextInt() % n)+100;
		synchronized (zombies) {
			if(j % 2 != 0){
				zombies.add(new ZombieMom(Math.abs(j),1054));
			}else{
				zombies.add(new EnrageZombie(Math.abs(j),1054));
			}				
		}
	}
	/*Create a zombie to the Right Spawn */
	private void zombieRightSpawn(){
		/*Right spawn*/
		Random rn = new Random();
		int n = 1000;
		int j = (rn.nextInt() % n)+100;
		synchronized (zombies) {
			if(j % 2 != 0){
				zombies.add(new ZombieMom(1300,Math.abs(j)));
			}else{
				zombies.add(new EnrageZombie(1300,Math.abs(j)));
			}

		}
	}	
	
	private void addZombies(){
		/* Timer create new zombies */
		Random rn = new Random();
		int choice = rn.nextInt(3) + 1;
		/* Must add other zombies to the level */
		/* Random position */
		switch(choice){
		case 1: zombieLeftSpawn(); break;
		case 2: zombieBottomSpawn(); break;
		case 3: zombieRightSpawn(); break;
		}
	}

	/**
	 * This method is called by the main thread.
	 * It update the player, the map and the HUD.
	 * Also check if we won or lost the game. 
	 * 
	 */
	
	public void update(){		
		if(System.currentTimeMillis()> start + delay){
			/* Add zombies to the game */
			if(zombieCreated < NUMZOMBIE){
				addZombies();
				zombieCreated ++ ;
			}
			start = System.currentTimeMillis();
		}			
		/* Imponiamo l'update al giocatore*/
		player.update(xMouse,yMouse);		
		/* Spostiamo la posizione della mappa*/
		map.update(player.getXMap(), player.getYMap());
		/* Update HUD */
		h.update();
		/* al massimo 50 schizzi di sangue*/
		synchronized (sangue) {
			if(sangue.size()>50){			
				sangue.remove(0);
			}
		}
		/* Check if player and base are still alive */
		if(!player.isAlive() || !base.isAlive()){
			/*Terminiamo i thread*/
			t.interrupt();
			p.interrupt();
			player.setLeft(false);
			player.setRight(false);
			player.setUp(false);
			player.setDown(false);
			this.cds.aggiungiSessione(new Defeat(cds));
			this.cds.setState(SessionController.DEFEAT);
		}
		/*Check if all zombies died*/		
		if(zombieCreated == NUMZOMBIE){
			synchronized (zombies) {
				if(zombies.size()==0){
					t.interrupt();
					p.interrupt();
					player.setLeft(false);
					player.setRight(false);
					player.setUp(false);
					player.setDown(false);
					this.cds.aggiungiSessione(new Victory(cds));
					this.cds.setState(SessionController.VICTORY);
				}
			}		
		}		
		
	}
	
	public void draw(Graphics2D grafica){		
		/* Draw Map */
		map.draw(grafica);
		/* Draw blood */
		synchronized (sangue) {
			for(int x =0;x<sangue.size();x++){
				sangue.get(x).draw(grafica);
			}
		}
		/* Draw player */
		player.draw(grafica);		
		/* Draw zombies */
		synchronized (zombies) {
			for(int x=0;x<zombies.size();x++){
				zombies.get(x).draw(grafica);
			}
		}
		/* Draw bullets */		
		synchronized (proiettili) {
			for(int x =0; x<proiettili.size();x++){
				proiettili.get(x).draw(grafica);	
			}			
		}
		
		/* Draw HUD */
		h.draw(grafica);
		
		
	}
	
	/**
	 * KeyListener of the Keyboard.
	 * Each key is assigned to an operation.
	 */
	
	public void keyPressed(int k) throws InterruptedException{
		/* Set player moving */
		switch(k){
		case(KeyEvent.VK_A): player.setLeft(true);break;
		case(KeyEvent.VK_D): player.setRight(true);break;
		case(KeyEvent.VK_W): player.setUp(true);break;
		case(KeyEvent.VK_S): player.setDown(true);break;
		case(KeyEvent.VK_1): player.setGun(0);break;
		case(KeyEvent.VK_2): player.setGun(1);break;
		case(KeyEvent.VK_3): player.setGun(2);break;
		case(KeyEvent.VK_P):{
			pause = true;
			zt.setPausa(pause);
			pt.setPausa(pause);
			/* lock player movements */
			player.setLeft(false);
			player.setRight(false);
			player.setUp(false);
			player.setDown(false);
			/* Set Pause State */
			this.cds.setState(SessionController.GAMEPAUSE);}
			break;		
		}
		
	}	
	
	public void keyReleased(int k){
		/*Imponiamo al personaggio di stare fermo*/
		switch(k){
		case(KeyEvent.VK_A): player.setLeft(false);break;
		case(KeyEvent.VK_D): player.setRight(false);break;
		case(KeyEvent.VK_W): player.setUp(false);break;
		case(KeyEvent.VK_S): player.setDown(false);break;		
		}
	}
	
	public void mouseClicked(int x,int y){
			double xMOUSE=x+15*Math.random();
			double yMOUSE=y+15*Math.random();
			synchronized (proiettili) {
				player.shoot(xMOUSE, yMOUSE,proiettili);
			}
	}
	
	public void mouseReleased() {	
	}
	
	public void setMouse(int x, int y) {
		this.xMouse = x;
		this.yMouse = y;		
	}	
		
}
