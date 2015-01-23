package SessioniDiGioco;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class GamePause extends SessioneDiGioco{
	private String[] pausa={
			"PAUSE",
			"QUIT"
	};
	private Color defaultColor;
	private Color choiceColor;
	private int currentchoice = 0;
	
	public GamePause(ControllerDiSessione cds){
		this.cds = cds;
		defaultColor = Color.white;
		choiceColor = Color.red;
	}
	@Override
	public void init() {
		currentchoice = 0;		
	}

	@Override
	public void update() {
	
	}

	@Override
	public void draw(Graphics2D g) {
		for(int i = 0; i<pausa.length; i++){
			if(i == currentchoice){
				g.setColor(choiceColor);
			}else{
				g.setColor(defaultColor);
			}
			g.drawString(pausa[i], 320, 200+i*50);
		}		
	}

	@Override
	public void keyPressed(int k) {
		switch(k){
		case KeyEvent.VK_P: this.cds.setState(ControllerDiSessione.LIVELLO1); break;
		case KeyEvent.VK_ENTER: this.select(); break;	
		case KeyEvent.VK_W: {
			currentchoice --;
			if(currentchoice == -1){
				currentchoice = 1;
			}				
			break;
		}
		case KeyEvent.VK_S: {
			currentchoice ++;
			if(currentchoice == 2)
				currentchoice = 0;
			break;
		}	
		}
	}
	private void select(){
		switch(currentchoice){
			case 0 : this.cds.setState(ControllerDiSessione.LIVELLO1); break;
			case 1 : System.exit(0);break;
		}
	}

	@Override
	public void keyReleased(int k) {
				
	}

	@Override
	public void mouseClicked(int x,int y) {
				
	}

	@Override
	public void mouseReleased() {
	
	}
	
	@Override
	public void setMouse(int x, int y) {
			
	}

}
