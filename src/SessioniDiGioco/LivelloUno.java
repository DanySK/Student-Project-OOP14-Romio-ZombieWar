package SessioniDiGioco;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

public class LivelloUno extends SessioneDiGioco{
	/*Un giocatore*/
	private Giocatore giocatore;
	/*Una mappa*/
	private Mappa mappa;
	/*Vettore contenenti le armi*/
	private ArmaImpl[] armi = new ArmaImpl [3];
	/*Zombie*/
	private List<MammaZombie> list = Collections.synchronizedList(new ArrayList<MammaZombie>());
	private static final int NUMZOMBIE = 50;
	/*Base da difendere*/
	private Base base;
	/*Thread degli zombie*/
	private Thread t;
	private ZombieThread zt;
	/*Thread proiettili*/
	private Thread p;
	private ProiettileThread pt;
	/*Proiettili*/
	private List<Proiettile> proiettili= Collections.synchronizedList(new ArrayList<Proiettile>()) ;
	/*HUD di gioco*/
	private HUD h;
	
	public LivelloUno(ControllerDiSessione cds){
		this.cds = cds;
	}
	@Override
	public void init(){
		/*Inizializziamo la mappa*/
		mappa = new Mappa("/backgrounds/map.png");
		/*Inizializziamo la base*/
		base = Base.getIstance();
		/*Inizializziamo il giocatore*/
		giocatore = Giocatore.getIstance();
		/*Inizializziamo le armi*/
		weaponInit();
		/*Inizializziamo uno zombie*/
		for(int i =0; i <NUMZOMBIE; i++){
			Random rn = new Random();
			int n = 800;
			int j = (rn.nextInt() % n)+100;
			synchronized (list) {
				list.add(new MammaZombie(j,1000,giocatore,base));
			}			
		}		
		/*Inizializziamo il thread per lo zombie*/		
		zt = new ZombieThread(list,30);
		t = new Thread(zt);
		t.start();
		/*Inizializziamo il thread per i proiettili*/
		pt = new ProiettileThread(proiettili,list);
		p = new Thread(pt);
		p.start();
		/*Inizializziamo l'HUD di gioco*/
		h = new HUD();
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
		giocatore.update();		
		/*Spostiamo la posizione della mappa*/
		mappa.update(giocatore.getXMap(), giocatore.getYMap());
		/*Update dell'HUD*/
		h.update(base,giocatore);
	}
	@Override
	public void draw(Graphics2D grafica){		
		/*Disegniamo la mappa*/
		mappa.draw(grafica);
		/*Disegniamo l'HUD di gioco*/
		h.draw(grafica);
		/*Disegniamo il giocatore*/
		giocatore.draw(grafica);		
		/*Disegniamo gli zombie*/
		for(int j =0;j<list.size();j++){
			synchronized (list) {
				list.get(j).draw(grafica);
			}
		}
		/*Disegniamo i proiettili*/		
		synchronized (proiettili) {
			for(int i =0; i<proiettili.size();i++){
				proiettili.get(i).draw(grafica);	
			}			
		}
	}
	@Override
	public void keyPressed(int k){
		/*Imponiamo al personaggio uno spostamento*/
		switch(k){
		case(KeyEvent.VK_A): giocatore.setLeft(true);break;
		case(KeyEvent.VK_D): giocatore.setRight(true);break;
		case(KeyEvent.VK_W): giocatore.setUp(true);break;
		case(KeyEvent.VK_S): giocatore.setDown(true);break;
		case(KeyEvent.VK_1): giocatore.setGun(0);break;
		case(KeyEvent.VK_2): giocatore.setGun(1);break;
		case(KeyEvent.VK_3): giocatore.setGun(2);break;
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
		default: break;
		}
	}
	@Override
	public void mouseClicked(){				
			double xMOUSE=MouseInfo.getPointerInfo().getLocation().x+15*Math.random();
			double yMOUSE=MouseInfo.getPointerInfo().getLocation().y+15*Math.random();
			synchronized (proiettili) {
				giocatore.shoot(xMOUSE, yMOUSE,proiettili);		
			}
	}
	@Override
	public void mouseReleased() {
		System.out.println("released");		
	}	
		
}
