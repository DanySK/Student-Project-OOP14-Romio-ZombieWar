package SessioniDiGioco;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Entita.Giocatore;

public class ControllerDiSessione {	
	private ArrayList<SessioneDiGioco> sessioni;
	private int sessioneCorrente;
	protected Giocatore g;
	public static final int MENU=0;	
	public static final int SELEZIONEPERSONAGGIO=1;
	public static final int LIVELLO1=2;

	public ControllerDiSessione(){
		g = Giocatore.getIstance();
		sessioni = new ArrayList<SessioneDiGioco>();
		sessioneCorrente= MENU;
		sessioni.add(new MenuPrincipale(this));	
		sessioni.add(new SelezionePersonaggio(this));
		sessioni.add(new LivelloUno(this));
	}
	/*Aggiungiamo una nuova sessione di gioco dinamicamente*/
	public void aggiungiSessione(SessioneDiGioco level){
		sessioni.add(level);
	}
	public void update(){
		sessioni.get(sessioneCorrente).update();
	}
	public void setState(int state){
		sessioni.get(state).init();
		sessioneCorrente=state;		
	}
	public void draw(Graphics2D g){
		sessioni.get(sessioneCorrente).draw(g);
	}
	/*KEYBOARD*/
	public void keyPressed(int k){
		sessioni.get(sessioneCorrente).keyPressed(k);
	}
	public void keyReleased(int k){
		sessioni.get(sessioneCorrente).keyReleased(k);
	}
	/*MOUSE*/
	public void mouseClicked(){
		sessioni.get(sessioneCorrente).mouseClicked();
	}
	public void mouseReleased(){
		sessioni.get(sessioneCorrente).mouseReleased();
	}
}
