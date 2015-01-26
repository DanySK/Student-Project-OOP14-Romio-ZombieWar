package SessioniDiGioco;

import java.awt.Graphics2D;

public class Vittoria extends SessioneDiGioco {
	
	public Vittoria(ControllerDiSessione cds){
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
		this.cds.reset();
		this.cds.setState(ControllerDiSessione.MENU);		
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
