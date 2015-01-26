package SessioniDiGioco;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Opzioni extends SessioneDiGioco {
	private BufferedImage background;
	
	public Opzioni(ControllerDiSessione cds){
		
		this.cds = cds;
		try{
			background = ImageIO.read(getClass().getResourceAsStream("/backgrounds/Options.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void init() {
				
	}

	
	public void update() {
				
	}

	
	public void draw(Graphics2D g) {
		
		g.drawImage(background, 0, 0, null);		
	}

	
	public void keyPressed(int k) throws InterruptedException {
		
		if(KeyEvent.VK_ENTER == k){
			this.cds.setState(ControllerDiSessione.MENU);
		}
		
	}
	
	public void keyReleased(int k) {
				
	}

	
	public void mouseClicked(int x,int y) {
				
	}

	
	public void mouseReleased() {
				
	}
	
	public void setMouse(int x, int y) {
				
	}

}
