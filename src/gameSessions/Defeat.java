package gameSessions;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Defeat extends GameSession{
	/**
	 * 
	 * This session comes when the player or the base has 0 hp left
	 * 
	 * @author Giovanni Romio
	 */
	
	private BufferedImage background;
	
	/**
	 * 
	 * @param cds is thse SessionController
	 */
	
	public Defeat(SessionController cds){
		this.cds = cds;
		try{
			background = ImageIO.read(getClass().getResourceAsStream("/backgrounds/defeat.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void init() {
	}

	
	public void update() {
	}

	
	public void draw(Graphics2D g) {
		g.setFont(g.getFont().deriveFont(40f));
		g.drawImage(background, 0, 0, null);
		g.drawString("DEFEAT", 280, 240);
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
