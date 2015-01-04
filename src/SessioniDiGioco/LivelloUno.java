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
import Entita.Mappa;
import Entita.Giocatore;
import Entita.MammaZombie;
import Entita.Proiettile;

public class LivelloUno extends SessioneDiGioco{
	private ControllerDiSessione cds;
	/*Un giocatore*/
	private Giocatore g;
	/*Una mappa*/
	private Mappa mappa;
	/*Vettore contenenti le armi*/
	private ArmaImpl[] armi = new ArmaImpl [3];
	/*Zombie*/
	private List<MammaZombie> list = Collections.synchronizedList(new ArrayList<MammaZombie>());
	private static final int NUMZOMBIE = 1000;
	/*Bse da difendere*/
	private Base base;
	/*Thread degli zombie*/
	private Thread t;
	private ZombieThread zt;
	/*Thread proiettili*/
	private Thread p;
	private ProiettileThread pt;
	/*Proiettili*/
	private List<Proiettile> proiettili= Collections.synchronizedList(new ArrayList<Proiettile>()) ;
	public LivelloUno(ControllerDiSessione cds){
		this.cds = cds;
		this.init();
	}
	@Override
	public void init(){
		/*Inizializziamo la mappa*/
		mappa = new Mappa("/backgrounds/map.png");
		/*Inizializziamo la base*/
		base = Base.getIstance();
		/*Inizializziamo il giocatore*/
		g = Giocatore.getIstance();
		/*Inizializziamo le armi*/
		weaponInit();
		/*Inizializziamo uno zombie*/
		for(int i =0; i <NUMZOMBIE; i++){
			Random rn = new Random();
			int n = 400;
			int j = (rn.nextInt() % n)+100;
			list.add(new MammaZombie(j,800,g,base));
		}		
		/*Inizializziamo il thread per lo zombie*/		
		zt = new ZombieThread(list,30);
		t = new Thread(zt);
		t.start();
		/*Inizializziamo il thread per i proiettili*/
		pt = new ProiettileThread(proiettili,list);
		p = new Thread(pt);
		p.start();
	}
	private void weaponInit(){
		/*TODO!!!!! Inizializzazione dell'arsenale in base al livello*/
		armi[0] = new Pistola();
		armi[1] = new Fucile();
		armi[2] = new Mitra();
		g.setWeapons(armi);
	}
	
	@Override
	public void update(){		
		/*Imponiamo l'update al giocatore*/
		g.update();		
		/*Spostiamo la posizione della mappa*/
		mappa.update(g.getXMap(), g.getYMap());
		/*Controlliamo se sono ancora vivi degli zombie*/
		if(list.isEmpty()){
			cds.setState(3);
		}
	}
	@Override
	public void draw(Graphics2D grafica){
		/*Disegniamo la mappa*/
		mappa.draw(grafica);
		/*Disegniamo il giocatore*/
		g.draw(grafica);		
		/*Disegniamo gli zombie*/
		synchronized (list) {
			for(int i = 0;i<NUMZOMBIE;i++){			
				list.get(i).draw(grafica);
			}
		}
		/*Disegniamo i proiettili*/		
		for(int i =0; i<proiettili.size();i++){
			synchronized (proiettili) {
				proiettili.get(i).draw(grafica);	
			}			
		}
	}
	@Override
	public void keyPressed(int k){
		/*Imponiamo al personaggio uno spostamento*/
		switch(k){
		case(KeyEvent.VK_A): g.setLeft(true);break;
		case(KeyEvent.VK_D): g.setRight(true);break;
		case(KeyEvent.VK_W): g.setUp(true);break;
		case(KeyEvent.VK_S): g.setDown(true);break;
		case(KeyEvent.VK_1): g.setGun(0);break;
		case(KeyEvent.VK_2): g.setGun(1);break;
		case(KeyEvent.VK_3): g.setGun(2);break;
		}
	}
	@Override
	public void keyReleased(int k){
		/*Imponiamo al personaggio di stare fermo*/
		switch(k){
		case(KeyEvent.VK_A): g.setLeft(false);break;
		case(KeyEvent.VK_D): g.setRight(false);break;
		case(KeyEvent.VK_W): g.setUp(false);break;
		case(KeyEvent.VK_S): g.setDown(false);break;
		default: break;
		}
	}
	@Override
	public void mouseClicked(){				
			double xMOUSE=MouseInfo.getPointerInfo().getLocation().x+15*Math.random();
			double yMOUSE=MouseInfo.getPointerInfo().getLocation().y+15*Math.random();
			synchronized (proiettili) {
				g.shoot(xMOUSE, yMOUSE,proiettili);		
			}
	}
	@Override
	public void mouseReleased() {
		System.out.println("released");		
	}	
		
}
