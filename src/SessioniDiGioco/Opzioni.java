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
		g.drawImage(background, 0, 0, null);		
	}

	@Override
	public void keyPressed(int k) throws InterruptedException {
		if(KeyEvent.VK_ENTER == k){
			this.cds.setState(ControllerDiSessione.MENU);
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased() {
		// TODO Auto-generated method stub
		
	}

}
