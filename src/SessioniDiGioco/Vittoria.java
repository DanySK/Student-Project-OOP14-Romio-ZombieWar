package SessioniDiGioco;

import java.awt.Graphics2D;

public class Vittoria extends SessioneDiGioco {
	
	public Vittoria(ControllerDiSessione cds){
		this.cds = cds;		
	}
	
	@Override
	public void init() {		
		
	}

	@Override
	public void update() {
				
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(g.getFont().deriveFont(40f));
		g.drawString("CONGRATULATION", 180, 240);
	}

	@Override
	public void keyPressed(int k) throws InterruptedException {
		this.cds.reset();
		this.cds.setState(ControllerDiSessione.MENU);		
	}

	@Override
	public void keyReleased(int k) {
		
	}

	@Override
	public void mouseClicked(int x, int y) {
		
	}

	@Override
	public void mouseReleased() {
		
	}

	@Override
	public void setMouse(int x, int y) {
		
	}

}
