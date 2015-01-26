package SessioniDiGioco;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sconfitta extends SessioneDiGioco{
	
	private BufferedImage background;
	
	public Sconfitta(ControllerDiSessione cds){
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
			this.cds.setState(ControllerDiSessione.MENU);		
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
