package SessioniDiGioco;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.*;

import Armi.ArmaImpl;
import Armi.Fucile;
import Armi.Mitra;
import Armi.Pistola;
import Entita.Base;
import Entita.HUD;
import Entita.Mappa;
import Entita.Giocatore;
import Entita.MammaZombie;
import Entita.Proiettile;
import Entita.Sangue;

public class LivelloUno extends SessioneDiGioco{
	/** Una mappa */
	private Mappa mappa;
	/** Vettore contenenti le armi */
	private ArmaImpl[] armi = new ArmaImpl [3];
	/** Zombie */
	private List<MammaZombie> zombies;
	private static final int NUMZOMBIE = 50;
	/** Sangue */
	private List<Sangue> sangue;
	/** Base da difendere */
	private Base base;
	/** Thread degli zombie */
	private Thread t;
	private ZombieThread zt;
	/** Thread proiettili */
	private Thread p;
	private ProiettileThread pt;
	/** Proiettili */
	private List<Proiettile> proiettili;
	/** HUD di gioco */
	private HUD h;
	private int nprocs;
	private boolean pause = false;
	/** Posizione del mouse */
	private int xMouse;
	private int yMouse;
	
	
	public LivelloUno(ControllerDiSessione cds){
		this.cds = cds;		
		/*Test cpu*/
		nprocs = Runtime.getRuntime().availableProcessors();
		System.out.println(nprocs);		
		/*Inizializziamo la mappa*/
		mappa = new Mappa("/backgrounds/map.png");		
		/*Inizializziamo la base*/
		base = Base.getIstance();
		base.init();		
		/*Inizializziamo il giocatore*/
		giocatore = Giocatore.getIstance();
		giocatore.init();		
		/*Inizializziamo le armi*/
		weaponInit();		
		/*Inizializziamo la lista degli zombie*/
		zombies = Collections.synchronizedList(new ArrayList<MammaZombie>());		
		/*Inizializziamo la lista dei proiettili*/
		proiettili =  Collections.synchronizedList(new ArrayList<Proiettile>()) ;
		/*Inizializziamo la lista delle chiazze di sangue*/
		sangue = Collections.synchronizedList(new ArrayList<Sangue>());
		/*Inizializziamo uno zombie*/
		for(int i =0; i <NUMZOMBIE; i++){
			Random rn = new Random();
			int n = 800;
			int j = (rn.nextInt() % n)+100;
			synchronized (zombies) {
				zombies.add(new MammaZombie(j,1000,giocatore,base));
			}			
		}
		
		/*Inizializziamo il thread per lo zombie*/		
		zt = new ZombieThread(zombies,30);
		t = new Thread(zt);
		t.start();
		
		/*Inizializziamo il thread per i proiettili*/
		pt = new ProiettileThread(proiettili,zombies,sangue);
		p = new Thread(pt);
		p.start();
		
		/*Inizializziamo l'HUD di gioco*/
		h = new HUD();
	}
	@Override
	public void init(){
		if(this.pause == true){
			/*togliamo la pause*/
			zt.setPausa(false);
			pt.setPausa(false);
		}
	}
	private void weaponInit(){
		/*TODO!!!!! Inizializzazione dell'arsenale in base al livello*/
		armi[0] = new Pistola();
		armi[1] = new Fucile();
		armi[2] = new Mitra();
		giocatore.setWeapons(armi);
	}
	
	@Override
	public void update(){	
		/*Imponiamo l'update al giocatore*/
		giocatore.update(xMouse,yMouse);		
		/*Spostiamo la posizione della mappa*/
		mappa.update(giocatore.getXMap(), giocatore.getYMap());
		/*Update dell'HUD*/
		h.update(base,giocatore);
		/*al massimo 50 schizzi di sangue*/
		synchronized (sangue) {
			if(sangue.size()>50){			
				sangue.remove(0);
			}
		}
		
	}
	@Override
	public void draw(Graphics2D grafica){		
		/*Disegniamo la mappa*/
		mappa.draw(grafica);
		/*Disegniamo il sangue*/
		synchronized (sangue) {
			for(int x =0;x<sangue.size();x++){
				sangue.get(x).draw(grafica);
			}
		}
		/*Disegniamo il giocatore*/
		giocatore.draw(grafica);		
		/*Disegniamo gli zombie*/
		synchronized (zombies) {
			for(int x=0;x<zombies.size();x++){
				zombies.get(x).draw(grafica);
			}
		}
		/*Disegniamo i proiettili*/		
		synchronized (proiettili) {
			for(int x =0; x<proiettili.size();x++){
				proiettili.get(x).draw(grafica);	
			}			
		}
		
		/*Disegniamo l'HUD di gioco*/
		h.draw(grafica);
		
		/*Controlliamo se il giocatore o la base sono ancora vivi*/
		if(!giocatore.isAlive() || !base.isAlive()){
			/*Terminiamo i thread*/
			t.interrupt();
			p.interrupt();
			giocatore.setLeft(false);
			giocatore.setRight(false);
			giocatore.setUp(false);
			giocatore.setDown(false);
			this.cds.aggiungiSessione(new Sconfitta(cds));
			this.cds.setState(ControllerDiSessione.SCONFITTA);
		}
		/*Controlliamo se ci sono ancora degli zombie altrimenti abbiamo vinto*/
		synchronized (zombies) {
			if(zombies.size()==0){
				t.interrupt();
				p.interrupt();
				giocatore.setLeft(false);
				giocatore.setRight(false);
				giocatore.setUp(false);
				giocatore.setDown(false);
				this.cds.aggiungiSessione(new Vittoria(cds));
				this.cds.setState(ControllerDiSessione.VITTORIA);
			}
		}		
	}
	@Override
	public void keyPressed(int k) throws InterruptedException{
		/*Imponiamo al personaggio uno spostamento*/
		switch(k){
		case(KeyEvent.VK_A): giocatore.setLeft(true);break;
		case(KeyEvent.VK_D): giocatore.setRight(true);break;
		case(KeyEvent.VK_W): giocatore.setUp(true);break;
		case(KeyEvent.VK_S): giocatore.setDown(true);break;
		case(KeyEvent.VK_1): giocatore.setGun(0);break;
		case(KeyEvent.VK_2): giocatore.setGun(1);break;
		case(KeyEvent.VK_3): giocatore.setGun(2);break;
		case(KeyEvent.VK_P):{
			pause = true;
			zt.setPausa(pause);
			pt.setPausa(pause);
			/*Blocchiamo i movimenti del giocatore*/
			giocatore.setLeft(false);
			giocatore.setRight(false);
			giocatore.setUp(false);
			giocatore.setDown(false);
			/*Passiamo allo stato di pausa*/
			this.cds.setState(ControllerDiSessione.GAMEPAUSE);}
			break;		
		}
		
	}
	
	@Override
	public void keyReleased(int k){
		/*Imponiamo al personaggio di stare fermo*/
		switch(k){
		case(KeyEvent.VK_A): giocatore.setLeft(false);break;
		case(KeyEvent.VK_D): giocatore.setRight(false);break;
		case(KeyEvent.VK_W): giocatore.setUp(false);break;
		case(KeyEvent.VK_S): giocatore.setDown(false);break;		
		}
	}
	@Override
	public void mouseClicked(int x,int y){
			double xMOUSE=x+15*Math.random();
			double yMOUSE=y+15*Math.random();
			synchronized (proiettili) {
				giocatore.shoot(xMOUSE, yMOUSE,proiettili);
			}
	}
	@Override
	public void mouseReleased() {
		System.out.println("released");		
	}
	@Override
	public void setMouse(int x, int y) {
		this.xMouse = x;
		this.yMouse = y;		
	}	
		
}
