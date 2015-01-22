package SessioniDiGioco;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class GamePause extends SessioneDiGioco{
	private String[] pausa={
			"PAUSE",
			"QUIT"
	};
	public GamePause(ControllerDiSessione cds){
		this.cds = cds;
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		for(int i = 0; i<pausa.length; i++){			
			g.drawString(pausa[i], 320, 200+i*50);
		}
		
	}

	@Override
	public void keyPressed(int k) {
		if(k== KeyEvent.VK_P ){
			this.cds.setState(ControllerDiSessione.LIVELLO1);
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(int x,int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setMouse(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
