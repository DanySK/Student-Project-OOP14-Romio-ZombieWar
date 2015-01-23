package SessioniDiGioco;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Credits extends SessioneDiGioco{
	private BufferedImage background;
	public Credits(ControllerDiSessione cds){
		this.cds = cds;
		try{
			background = ImageIO.read(getClass().getResourceAsStream("/backgrounds/credits.png"));
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	@Override
	public void init() {
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);		
	}

	@Override
	public void keyPressed(int k) throws InterruptedException {
		if(k==KeyEvent.VK_ENTER){
			this.cds.setState(ControllerDiSessione.MENU);
		}
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
