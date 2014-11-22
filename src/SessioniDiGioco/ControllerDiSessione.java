package SessioniDiGioco;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ControllerDiSessione {	
	private ArrayList<SessioneDiGioco> sessioni;
	private int sessioneCorrente;
	
	public static final int MENU=0;	
	public static final int SELEZIONEPERSONAGGIO=1;
	public static final int LIVELLO1=2;
		
	public ControllerDiSessione(){		
		sessioni = new ArrayList<SessioneDiGioco>();
		
		sessioneCorrente= MENU;
		sessioni.add(new MenuPrincipale(this));	
		sessioni.add(new SelezionePersonaggio(this));
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
}
