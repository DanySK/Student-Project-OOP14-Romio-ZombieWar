package SessioniDiGioco;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Entita.Arma;
import Entita.Mappa;
import Entita.Player;

public class LivelloUno extends SessioneDiGioco{
	/*Un giocatore*/
	Player player;
	/*Una mappa*/
	Mappa mappa;
	/*Vettore contenenti le armi*/
	Arma[] armi = new Arma [2];
	public LivelloUno(){
		this.init();
	}
	@Override
	public void init(){
		/*Inizializziamo la mappa*/
		mappa = new Mappa("/backgrounds/map.png");
		/*Inizializziamo il giocatore*/
		player = Player.getIstance();
		/*Inizializziamo le armi*/
		weaponInit();		
	}
	private void weaponInit(){
		armi[0]=new Arma("glock21", 2, 15, false);
		armi[0].setTRANSFORM(10, 20, 1.6,0,-20,25);	
		armi[1]=new Arma("ak47", 4, 30, true);
		armi[1].setTRANSFORM(7, 14, 1.3,-0.3,-8,40);
		player.setWeapons(armi);
	}
	@Override
	public void update(){
		/*Spostiamo la posizione della mappa*/
		mappa.update(player.getXMap(), player.getYMap());
		/*Imponiamo l'update al giocatore*/
		player.update();
	}
	@Override
	public void draw(Graphics2D g){
		/*Disegniamo la mappa*/
		mappa.draw(g);
		/*Disegniamo il giocatore*/
		player.draw(g);
	}
	@Override
	public void keyPressed(int k){
		/*Imponiamo al personaggio uno spostamento*/
		switch(k){
			case(KeyEvent.VK_A): player.setLeft(true);break;
			case(KeyEvent.VK_D): player.setRight(true);break;
			case(KeyEvent.VK_W): player.setUp(true);break;
			case(KeyEvent.VK_S): player.setDown(true);break;
		}
	}
	@Override
	public void keyReleased(int k){
		/*Imponiamo al personaggio di stare fermo*/
		switch(k){
		case(KeyEvent.VK_A): player.setLeft(false);break;
		case(KeyEvent.VK_D): player.setRight(false);break;
		case(KeyEvent.VK_W): player.setUp(false);break;
		case(KeyEvent.VK_S): player.setDown(false);break;
	}
	}
	@Override
	public void mouseClicked(){
		
	}
}
