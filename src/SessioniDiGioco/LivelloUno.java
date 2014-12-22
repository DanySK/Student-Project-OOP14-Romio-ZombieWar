package SessioniDiGioco;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Entita.Arma;
import Entita.Base;
import Entita.Mappa;
import Entita.Giocatore;
import Entita.MammaZombie;

public class LivelloUno extends SessioneDiGioco{
	/*Un giocatore*/
	Giocatore g;
	/*Una mappa*/
	Mappa mappa;
	/*Vettore contenenti le armi*/
	Arma[] armi = new Arma [2];
	/*Zombie*/
	List<MammaZombie> list = Collections.synchronizedList(new ArrayList<MammaZombie>());
	/*Bse da difendere*/
	Base base;
	/*Thread dello zombie*/
	Thread t;
	ZombieThread zt;
	public LivelloUno(){
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
		for(int i =0; i <10; i++){
			list.add(new MammaZombie(0+i*50,0,g,base));
		}		
		/*Inizializziamo il thread per lo zombie*/
		zt = new ZombieThread(list);
		t = new Thread(zt);
		t.start();
	}
	private void weaponInit(){
		armi[0]=new Arma("glock21", 2, 15, false);
		armi[0].setTRANSFORM(10, 20, 1.6,0,-20,25);	
		armi[1]=new Arma("ak47", 4, 30, true);
		armi[1].setTRANSFORM(7, 14, 1.3,-0.3,-8,40);
		g.setWeapons(armi);
	}
	@Override
	public void update(){
		/*Spostiamo la posizione della mappa*/
		mappa.update(g.getXMap(), g.getYMap());
		/*Imponiamo l'update al giocatore*/
		g.update();
		/*Imponiamo l'update allo zombie*/
		//zombie.update();
	}
	@Override
	public void draw(Graphics2D grafica){
		/*Disegniamo la mappa*/
		mappa.draw(grafica);
		/*Disegniamo il giocatore*/
		g.draw(grafica);
		/*Disegniamo lo zombie*/
		for(int i = 0;i<10;i++){
			synchronized (list) {
				list.get(i).draw(grafica);
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

	}	
}
