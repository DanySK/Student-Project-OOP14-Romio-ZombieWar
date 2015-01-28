package gameSessions;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Victory extends GameSession {
	
	public Victory(SessionController cds){
		this.cds = cds;		
	}
		
	public void init() {		
	}

	
	public void update() {				
	}

	
	public void draw(Graphics2D g) {
		g.setFont(g.getFont().deriveFont(40f));
		g.drawString("CONGRATULATION", 180, 240);
	}

	
	public void keyPressed(int k) throws InterruptedException {
		if(k == KeyEvent.VK_ENTER){
			/*Reinizializzo il gioco*/
			this.cds.reset();
			/*Ritorno al menu*/
			this.cds.setState(SessionController.MENU);		
		}		
	}

	
	public void keyReleased(int k) {		
	}

	
	public void mouseClicked(int x, int y) {		
	}

	
	public void mouseReleased() {		
	}

	
	public void setMouse(int x, int y) {		
	}

}
