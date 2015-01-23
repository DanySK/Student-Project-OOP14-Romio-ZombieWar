package SessioniDiGioco;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ControllerDiSessione {	
	private ArrayList<SessioneDiGioco> sessioni;
	private int sessioneCorrente;
	public static final int MENU = 0;
	public static final int OPZIONI = 1;
	public static final int CREDITS = 2;
	public static final int SELEZIONEPERSONAGGIO = 3;
	public static final int LIVELLO1 = 4;
	public static final int GAMEPAUSE = 5;

	public ControllerDiSessione(){
		sessioni = new ArrayList<SessioneDiGioco>();
		sessioneCorrente= MENU;
		sessioni.add(new MenuPrincipale(this));
		sessioni.add(new Opzioni(this));
		sessioni.add(new Credits(this));
		//sessioni.add(new SelezionePersonaggio(this));
		//sessioni.add(new LivelloUno(this));
		//sessioni.add(new GamePause(this));
	}
	/*Aggiungiamo una nuova sessione di gioco dinamicamente*/
	public void aggiungiSessione(SessioneDiGioco level){
		sessioni.add(level);
	}
	public void update(){
		sessioni.get(sessioneCorrente).update();
	}
	public void setState(int state){
		sessioneCorrente=state;
		sessioni.get(sessioneCorrente).init();
	}
	public void draw(Graphics2D g){
		sessioni.get(sessioneCorrente).draw(g);
	}
	/*KEYBOARD*/
	public void keyPressed(int k) throws InterruptedException{
		sessioni.get(sessioneCorrente).keyPressed(k);
	}
	public void keyReleased(int k){
		sessioni.get(sessioneCorrente).keyReleased(k);
	}
	/*MOUSE*/
	public void mouseClicked(int x, int y){
		sessioni.get(sessioneCorrente).mouseClicked(x,y);
	}
	public void mouseReleased(){
		sessioni.get(sessioneCorrente).mouseReleased();
	}
	public void MouseMovement(int x, int y){
		sessioni.get(sessioneCorrente).setMouse(x,y);
	}
}
