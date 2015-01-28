package gameSessions;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Credits extends GameSession{
	
	/**
	 * Draw some information about the game and the developer
	 * 
	 *  @author Giovanni Romio
	 * 
	 */
	
	private BufferedImage background;
	
	public Credits(SessionController cds){
		
		this.cds = cds;
		try{
			background = ImageIO.read(getClass().getResourceAsStream("/backgrounds/credits.png"));
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
		if(k==KeyEvent.VK_ENTER){
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
