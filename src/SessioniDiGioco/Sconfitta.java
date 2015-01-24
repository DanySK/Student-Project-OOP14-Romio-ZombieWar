package SessioniDiGioco;

import java.awt.Graphics2D;
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
	@Override
	public void init() {
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(g.getFont().deriveFont(40f));
		g.drawImage(background, 0, 0, null);
		g.drawString("DEFEAT", 280, 240);
	}

	@Override
	public void keyPressed(int k) throws InterruptedException {
		/*Termino i thread*/
		/*Reinizializzo il gioco*/
		this.cds.reset();
		/*Ritorno al menu*/
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
