package SessioniDiGioco;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ControllerDiSessione {
	
	/**
	 * This is the controller wich allow to switch by different Game Sessions
	 * 
	 * @author Giovanni Romio
	 */
	
	private ArrayList<SessioneDiGioco> sessioni;
	private int sessioneCorrente;
	public static final int MENU = 0;
	public static final int OPZIONI = 1;
	public static final int CREDITS = 2;
	public static final int SELEZIONEPERSONAGGIO = 3;
	public static final int LIVELLO1 = 4;
	public static final int GAMEPAUSE = 5;
	public static final int SCONFITTA = 6;
	public static final int VITTORIA = 6;

	public ControllerDiSessione(){
		sessioni = new ArrayList<SessioneDiGioco>();
		sessioneCorrente= MENU;

		sessioni.add(new MenuPrincipale(this));
		sessioni.add(new Opzioni(this));
		sessioni.add(new Credits(this));
	}
	/**
	 * 
	 * @param level add the specific level to the current session list
	 */
	public void aggiungiSessione(SessioneDiGioco level){
		sessioni.add(level);
	}
	/**
	 * Update the current Session
	 */
	public void update(){
		sessioni.get(sessioneCorrente).update();
	}
	/**
	 * 
	 * @param state rapresent the state we want to go for
	 */
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
	/**
	 * 
	 * @param x coordinatex of the Mouse
	 * @param y coordiantey of the Mouse
	 */
	public void mouseClicked(int x, int y){
		sessioni.get(sessioneCorrente).mouseClicked(x,y);
	}
	public void mouseReleased(){
		sessioni.get(sessioneCorrente).mouseReleased();
	}
	/**
	 * 
	 * @param x Position of the mouse
	 * @param y Position of the mouse
	 */
	public void MouseMovement(int x, int y){
		sessioni.get(sessioneCorrente).setMouse(x,y);
	}
	/**
	 * Reset all states add after performing action during the game
	 */
	public void reset(){		
		sessioni.remove(VITTORIA);
		sessioni.remove(GAMEPAUSE);
		sessioni.remove(LIVELLO1);
		sessioni.remove(SELEZIONEPERSONAGGIO);
	}
}
